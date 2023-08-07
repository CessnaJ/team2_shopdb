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
		case 1: // ��ٱ��Ͽ� å ���
			try {
				// ����� ���� ���� ��ٱ��Ͽ� ���� ���
				PreparedStatement outOfStock = con.prepareStatement(CartSQL.outOfStock);
				outOfStock.setLong(1, v.getProductKey());
				rset = outOfStock.executeQuery();
				rset.next();
				int stock = rset.getInt("stock");
				if (stock < v.getProductOrderCount()) throw new Exception("��ٱ��� ���� - ��� ����");
				
				// ��ٱ��Ͽ� ���� ��ǰ�� �Ǹ��ϰ� ���� ���� ���
				PreparedStatement invalidProduct = con.prepareStatement(CartSQL.invalidProduct);
				invalidProduct.setLong(1, v.getProductKey());
				rset = invalidProduct.executeQuery();
				rset.next();
				int status = rset.getInt("status");
				if (status == 2) throw new Exception("��ٱ��� ���� - �Ǹ� �ߴ� ��ǰ");
				
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
		case 2: // ���� - ��ٱ��� -> �ֹ�
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
		case 3: // ���� - ��ٱ��� -> ��ǰ �ֹ�
			try {
				// ����� ���� ���� ��ٱ��Ͽ� ���� ���
				PreparedStatement outOfStock = con.prepareStatement(CartSQL.outOfStock);
				outOfStock.setLong(1, v.getProductKey());
				rset = outOfStock.executeQuery();
				rset.next();
				int stock = rset.getInt("stock");
				if (stock < v.getProductOrderCount()) throw new Exception("��ǰ �ֹ� ���� - ��� ����");
				
				// ��ٱ��Ͽ� ���� ��ǰ�� �Ǹ��ϰ� ���� ���� ���
				PreparedStatement invalidProduct = con.prepareStatement(CartSQL.invalidProduct);
				invalidProduct.setLong(1, v.getProductKey());
				rset = invalidProduct.executeQuery();
				rset.next();
				int status = rset.getInt("status");
				if (status == 2) throw new Exception("��ǰ �ֹ� ���� - �Ǹ� �ߴ� ��ǰ");
				
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
		case 1: // ��ٱ��� ���� ����
			try {
				// ����� ���� ���� ��ٱ��Ͽ� ���� ���
				PreparedStatement outOfStock = con.prepareStatement(CartSQL.outOfStock2);
				outOfStock.setLong(1, v.getCartId());
				rset = outOfStock.executeQuery();
				rset.next();
				int stock = rset.getInt("stock");
				if (stock < v.getProductOrderCount()) throw new Exception("���� ���� ���� - ��� ����");
				
				// ��ٱ��Ͽ� ���� ��ǰ�� �Ǹ��ϰ� ���� ���� ���
				PreparedStatement invalidProduct = con.prepareStatement(CartSQL.invalidProduct2);
				invalidProduct.setLong(1, v.getCartId());
				rset = invalidProduct.executeQuery();
				rset.next();
				int status = rset.getInt("status");
				if (status == 2) throw new Exception("���� ���� ���� - �Ǹ� �ߴ� ��ǰ");
				
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
		case 2: // ���� - ��ٱ��� ���� ����
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
		case 3: // ���� - ��ǰ ���� ����
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
			if(rset.next()) { // Ű ���� ��ĭ ���� ��Ų �� ����
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
				throw new Exception("��ٱ��� ���� ��ȸ - ��ȸ ��� ����");
			}; 
			
		} catch(Exception e) {
			log.info(e.getMessage());
		} finally {
			// �ݵ�� �ݾ��־����
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
			if (cnt == 0) throw new Exception("��ٱ��� ��ü ��ȸ - ��ȸ ��� ����");
		} catch(Exception e) {
			log.info(e.getMessage());
		} finally {
			// �ݵ�� �ݾ��־����
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
