package app.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Product;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.sql.ProductSQL;

public class ProductDaoImpl implements DaoFrame<Long, Product>{
	ConnectionPool cp;
	Logger log = Logger.getLogger("ProductDaoImpl Test");
	
	public ProductDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int insert(Product v) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		// TODO Auto-generated method stub
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.productInsert);
		try {
			pstmt.setString(1,  v.getProductImgurl());
			pstmt.setString(2,  v.getName());
			pstmt.setInt(3,  v.getPrice());
			pstmt.setString(4,  v.getContent());
			pstmt.setInt(5,  v.getStock());
			pstmt.setDouble(6,  v.getProductPoint());
			pstmt.setDouble(7,  v.getDiscountRate());
			pstmt.setLong(8,  v.getSalesCount());
			pstmt.setInt(9,  v.getStatus()); 
			pstmt.setInt(10,  v.getAuthorKey());
			pstmt.setInt(11,  v.getPublishKey());
			pstmt.setInt(12,  v.getCategoryKey());
			
			result = pstmt.executeUpdate(); //���ܴ� �������� close�� �ϴ°� �ƴ϶� ���ܸ� ��� ó������ ���⼭ ����� �������.
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("���δ�Ʈ �Է� �� ����");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public int update(Product v) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.productUpdateContent);
		try {
			pstmt.setString(1,  v.getContent());
			pstmt.setLong(2,  v.getProductKey());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("���δ�Ʈ ���� �� ����");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public int delete(Long k) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.productDelete);
		try {
			pstmt.setLong(1,  k);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("���δ�Ʈ ���� �� ����");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public Optional<Product> select(Long k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
