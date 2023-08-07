package app.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Review;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.frame.ReviewRepository;
import app.sql.ReviewSQL;

public class ReviewDaoImpl implements ReviewRepository<Integer, Review> {
	ConnectionPool cp;
	Logger log = Logger.getLogger("ReviewDao Test");
	
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Review v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer k) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ReviewSQL.ReviewDelete);
		try {
			pstmt.setLong(1,  k);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("프로덕트 삭제 중 에러");
		} finally {
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
	
	public List<Review> selectBadReviews() throws Exception {
		
		
		return null;
	}
	
	
}
