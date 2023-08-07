package app.cust;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Review;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.frame.ReviewSQL;

public class ReviewDaoImpl implements DaoFrame<Integer, Review>{
	Logger log = Logger.getLogger("ReviewDaoImpl");
	ConnectionPool cp;
	
	public ReviewDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(Review v) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null; 
				
		try {
			pstmt = con.prepareStatement(ReviewSQL.reviewInsert);
			pstmt.setInt(1, v.getRating());
			pstmt.setString(2, v.getComment());
			pstmt.setBoolean(3, v.isReviewIsDeleted());
			pstmt.setLong(4, v.getMemberKey());
			pstmt.setLong(5, v.getProductKey());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("���� �ߺ� ����");
		}finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		
		return result;
	}

	@Override
	public int update(Review v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer k) throws Exception {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null; 
		
		int result;
		try {
			pstmt = con.prepareStatement(ReviewSQL.reviewDelete);
			pstmt.setInt(1, k);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("���� ����.");
		}finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public Optional<Review> select(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> search(Integer K) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
