package app.cust;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

import app.dto.Product;
import app.dto.Review;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.frame.SQL;

public class ProductDaoImpl implements DaoFrame<String, Product>{
	ConnectionPool cp;
	Logger log = Logger.getLogger("Lombok Test");
	
	public ProductDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<Product> search(String k) throws Exception {
		ArrayList<Product> list = new ArrayList<>();
		int result = 0;
	    ResultSet rset = null;
	    Connection con = cp.getConnection();
	    PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL.productSearch);
			pstmt.setString(1, k);
			pstmt.setString(2, k);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = Product.builder()
						.productKey(rset.getInt("product_key"))
						.productImgurl(rset.getString("product_imgurl"))
						.name(rset.getString("name"))
						.price(rset.getInt("price"))
						.content(rset.getString("content"))
						.stock(rset.getInt("stock"))
						.productRegDate(rset.getString("product_reg_date"))
						.productUpdateDate(rset.getString("product_update_date"))
						.productPoint(rset.getDouble("product_point"))
						.discountRate(rset.getDouble("discount_rate"))
						.salesCount(rset.getInt("sales_count"))
						.status(rset.getInt("status"))
						.authorKey(rset.getInt("author_key"))
						.publishKey(rset.getInt("publisher_key"))
						.categoryKey(rset.getString("category_key"))
						.build();
				list.add(product);
			}
			
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("검색 에러");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return list;
	}


	@Override
	public List<Product> select() throws Exception {
	    ArrayList<Product> list = new ArrayList<>();
	    int result = 0;
	    ResultSet rset = null;
	    Connection con = cp.getConnection();
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = con.prepareStatement(SQL.productTopView); 
	        rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = Product.builder()
						.productKey(rset.getInt("product_key"))
						.productImgurl(rset.getString("product_imgurl"))
						.name(rset.getString("name"))
						.price(rset.getInt("price"))
						.content(rset.getString("content"))
						.stock(rset.getInt("stock"))
						.productRegDate(rset.getString("product_reg_date"))
						.productUpdateDate(rset.getString("product_update_date"))
						.productPoint(rset.getDouble("product_point"))
						.discountRate(rset.getDouble("discount_rate"))
						.salesCount(rset.getInt("sales_count"))
						.status(rset.getInt("status"))
						.authorKey(rset.getInt("author_key"))
						.publishKey(rset.getInt("publisher_key"))
						.categoryKey(rset.getString("category_key"))
						.build();
				list.add(product);
			}
			
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("베스트셀러 에러");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return list;
	}


	@Override
	public List<Review> review(String k) throws Exception {
	    ArrayList<Review> list = new ArrayList<>();
	    int result = 0;
	    ResultSet rset = null;
	    Connection con = cp.getConnection();
	    PreparedStatement pstmt = null; 
	    try {
	        pstmt = con.prepareStatement(SQL.productReview);
	        pstmt.setString(1, k);
	        rset = pstmt.executeQuery();
	        
			while(rset.next()) {
				Review review = Review.builder()
						.reviewKey(rset.getInt("review_key"))
						.rating(rset.getInt("rating"))
						.comment(rset.getString("comment"))
						.reviewRegDate(rset.getString("review_reg_date"))
						.reviewUpdateDate(rset.getString("review_update_date"))
						.memberKey(rset.getInt("member_key"))
						.build();
				list.add(review);
			}
			
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("리뷰 에러");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
	    
		return list;
	}

}
