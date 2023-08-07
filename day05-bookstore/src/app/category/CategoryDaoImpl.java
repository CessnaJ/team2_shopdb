package app.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Category;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.sql.CategorySQL;
import app.sql.ProductSQL;

public class CategoryDaoImpl implements DaoFrame<Integer, Category>{
	ConnectionPool cp;
	Logger log = Logger.getLogger("Lombok Test");
	
	public CategoryDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public int insert(Category v) throws Exception {
		int result = 0;
		// TODO Auto-generated method stub
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CategorySQL.categoryInsert);
		try {
			pstmt.setString(1,  v.getCategoryKey());
			pstmt.setString(2,  v.getCategoryName());
			pstmt.setInt(3,  v.getCategoryState());
			pstmt.setInt(4,  v.getLevel());
			pstmt.setBoolean(5,  v.isLeaf());
			
			result = pstmt.executeUpdate(); //예외는 던지지만 close를 하는게 아니라서 예외를 어떻게 처리할지 여기서 제대로 해줘야함.
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("카테고리 입력 중 에러");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;

	}

	@Override
	public int update(Category v) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
//		"UPDATE CATEGORY SET category_key=?, category_name=?, level=?, state=?, is_leaf=? WHERE category_key=?";
		PreparedStatement pstmt = con.prepareStatement(CategorySQL.updateCategory);
		try {
			pstmt.setString(1,  v.);
			pstmt.setString(2,  v.getContent());
			pstmt.setLong(3,  v.getProductKey());
			pstmt.setLong(4,  v.getProductKey());
			pstmt.setLong(5,  v.getProductKey());
			pstmt.setString(6,  v.getProductKey());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("프로덕트 수정 중 에러");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public int delete(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Category> select(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
