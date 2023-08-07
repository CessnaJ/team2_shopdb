package app.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.CartDetail;
import app.dto.CartUpdate;
import app.dto.Product;
import app.dto.Review;
import app.frame.CartSQL;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;

public class CartDaoImpl implements DaoFrame<CartUpdate, CartDetail> {

	Logger log = Logger.getLogger("CartDaoImpl");
	
	ConnectionPool cp;
	
	public CartDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(CartDetail v) throws Exception {
		int result = 0;
		ResultSet rset = null;
		
		int cmd = v.getInsertState();
		
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		
		switch(cmd) {
		case 1: // 장바구니에 책 담기
			try {
				// 재고보다 많은 수를 장바구니에 넣은 경우
				PreparedStatement outOfStock = con.prepareStatement(CartSQL.outOfStock);
				outOfStock.setLong(1, v.getProductKey());
				rset = outOfStock.executeQuery();
				rset.next();
				int stock = rset.getInt("stock");
				if (stock < v.getProductOrderCount()) throw new Exception("장바구니 에러 - 재고 없음");
				
				// 장바구니에 담을 상품을 판매하고 있지 않은 경우
				PreparedStatement invalidProduct = con.prepareStatement(CartSQL.invalidProduct);
				invalidProduct.setLong(1, v.getProductKey());
				rset = invalidProduct.executeQuery();
				rset.next();
				int status = rset.getInt("status");
				if (status == 2) throw new Exception("장바구니 에러 - 판매 중단 상품");
				
				pstmt = con.prepareStatement(CartSQL.cartAdd);
				pstmt.setInt(1, v.getProductOrderCount());
				pstmt.setLong(2, v.getMemberKey());
				pstmt.setLong(3, v.getProductKey());
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				log.info(e.getMessage());
			} finally {
				DaoFrame.closePstmt(pstmt);
				cp.releaseConnection(con);
			}
			break;
		case 2: // 구매 - 장바구니 -> 주문
			try {
				pstmt = con.prepareStatement(CartSQL.cartBuyStep3);
				pstmt.setLong(1, v.getMemberKey());
				result = pstmt.executeUpdate();
				
			} catch(Exception e) {
				log.info(e.getMessage());
			} finally {
				DaoFrame.closePstmt(pstmt);
				cp.releaseConnection(con);
			}
			break;
		case 3: // 구매 - 장바구니 -> 상품 주문
			try {
				// 재고보다 많은 수를 장바구니에 넣은 경우
				PreparedStatement outOfStock = con.prepareStatement(CartSQL.outOfStock);
				outOfStock.setLong(1, v.getProductKey());
				rset = outOfStock.executeQuery();
				rset.next();
				int stock = rset.getInt("stock");
				if (stock < v.getProductOrderCount()) throw new Exception("상품 주문 에러 - 재고 없음");
				
				// 장바구니에 담을 상품을 판매하고 있지 않은 경우
				PreparedStatement invalidProduct = con.prepareStatement(CartSQL.invalidProduct);
				invalidProduct.setLong(1, v.getProductKey());
				rset = invalidProduct.executeQuery();
				rset.next();
				int status = rset.getInt("status");
				if (status == 2) throw new Exception("상품 주문 에러 - 판매 중단 상품");
				
				pstmt = con.prepareStatement(CartSQL.cartBuyStep4);
				pstmt.setInt(1, v.getProductOrderCount());
				pstmt.setLong(2, v.getOrderKey());
				pstmt.setLong(3, v.getProductKey());
				
			} catch(Exception e) {
				log.info(e.getMessage());
			} finally {
				DaoFrame.closePstmt(pstmt);
				cp.releaseConnection(con);
			}
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public int update(CartDetail v) throws Exception {
		int result = 0;
		ResultSet rset = null;
		int cmd = v.getUpdateState();
		
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		
		switch(cmd) {
		case 1: // 장바구니 수량 변경
			try {
				// 재고보다 많은 수를 장바구니에 넣은 경우
				PreparedStatement outOfStock = con.prepareStatement(CartSQL.outOfStock2);
				outOfStock.setLong(1, v.getCartId());
				rset = outOfStock.executeQuery();
				rset.next();
				int stock = rset.getInt("stock");
				if (stock < v.getProductOrderCount()) throw new Exception("수량 변경 에러 - 재고 부족");
				
				// 장바구니에 담을 상품을 판매하고 있지 않은 경우
				PreparedStatement invalidProduct = con.prepareStatement(CartSQL.invalidProduct2);
				invalidProduct.setLong(1, v.getCartId());
				rset = invalidProduct.executeQuery();
				rset.next();
				int status = rset.getInt("status");
				if (status == 2) throw new Exception("수량 변경 에러 - 판매 중단 상품");
				
				pstmt = con.prepareStatement(CartSQL.cartChangeCount);
				pstmt.setInt(1, v.getProductOrderCount());
				pstmt.setLong(2, v.getCartId());
			} catch(Exception e) {
				log.info(e.getMessage());
			} finally {
				DaoFrame.closePstmt(pstmt);
				cp.releaseConnection(con);
			}
			break;
		case 2: // 구매 - 장바구니 상태 변경
			try {
				pstmt = con.prepareStatement(CartSQL.cartBuyStep1);
				pstmt.setInt(1, v.getCartState());
				pstmt.setLong(2, v.getCartId());
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				log.info(e.getMessage());
			} finally {
				DaoFrame.closePstmt(pstmt);
				cp.releaseConnection(con);
			}
			break;
		case 3: // 구매 - 상품 상태 변경
			try {
				pstmt = con.prepareStatement(CartSQL.cartBuyStep2);
				pstmt.setInt(1, v.getProductOrderCount());
				pstmt.setInt(2, v.getProductOrderCount());
				pstmt.setLong(3, v.getProductKey());
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				log.info(e.getMessage());
			} finally {
				DaoFrame.closePstmt(pstmt);
				cp.releaseConnection(con);
			}
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public int delete(CartUpdate k) throws Exception {
		int result = 0;
		
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(CartSQL.cartCancel);
			pstmt.setInt(1, k.getState());
			pstmt.setLong(2, k.getKey());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public Optional<CartDetail> select(CartUpdate k) throws Exception {
		CartDetail detail = null;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(CartSQL.cartSearch);
			pstmt.setLong(1, k.getKey());

			rset = pstmt.executeQuery();
			if(rset.next()) { // 키 값을 한칸 전진 시킨 후 빼줌
				detail = CartDetail.builder().
						cartId(rset.getLong("cart_id")).
						productOrderCount(rset.getInt("product_order_count")).
						cartState(rset.getInt("cart_state")).
						regDate(rset.getDate("reg_date")).
						productImgurl(rset.getString("product_imgurl")).
						name(rset.getString("name")).
						price(rset.getInt("price")).
						content(rset.getString("content")).
						stock(rset.getInt("stock")).
						productPoint(rset.getInt("product_point")).
						discountRate(rset.getDouble("discount_rate")).
						authorName(rset.getString("author_name")).
						publisherName(rset.getString("publisher_name")).
						categoryName(rset.getString("category_name")).
						selectBuild();
			} else {
				throw new Exception("장바구니 개별 조회 - 조회 결과 없음");
			}; 
			
		} catch(Exception e) {
			log.info(e.getMessage());
		} finally {
			// 반드시 닫아주어야함
			DaoFrame.closeRset(rset);
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return Optional.of(detail);
	}

	@Override
	public List<CartDetail> select() throws Exception {
		return null;
	}

	@Override
	public List<CartDetail> search(CartUpdate k) throws Exception {
		List<CartDetail> detail = new ArrayList<>();
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cnt = 0;
		try {
			pstmt = con.prepareStatement(CartSQL.cartSearchAll);
			pstmt.setLong(1, k.getKey());
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				detail.add(CartDetail.builder().
						cartId(rset.getLong("cart_id")).
						productOrderCount(rset.getInt("product_order_count")).
						cartState(rset.getInt("cart_state")).
						regDate(rset.getDate("reg_date")).
						productImgurl(rset.getString("product_imgurl")).
						name(rset.getString("name")).
						price(rset.getInt("price")).
						content(rset.getString("content")).
						stock(rset.getInt("stock")).
						productPoint(rset.getInt("product_point")).
						discountRate(rset.getDouble("discount_rate")).
						authorName(rset.getString("author_name")).
						publisherName(rset.getString("publisher_name")).
						categoryName(rset.getString("category_name")).
						selectBuild());
				cnt++;
			}
			if (cnt == 0) throw new Exception("장바구니 전체 조회 - 조회 결과 없음");
		} catch(Exception e) {
			log.info(e.getMessage());
		} finally {
			// 반드시 닫아주어야함
			DaoFrame.closeRset(rset);
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		
		return detail;
	}

	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
