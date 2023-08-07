package app.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Category;
import app.dto.Review;
import app.frame.CategoryRepository;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.sql.CategorySQL;

public class CategoryDaoImpl implements CategoryRepository<String, Category>{
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
//			pstmt.setString(1,  v.);
//			pstmt.setString(2,  v.getContent());
//			pstmt.setLong(3,  v.getProductKey());
//			pstmt.setLong(4,  v.getProductKey());
//			pstmt.setLong(5,  v.getProductKey());
//			pstmt.setString(6,  v.getProductKey());
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
	public List<Category> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int disableCategory(String category_key) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
//		"UPDATE CATEGORY SET category_key=?, category_name=?, level=?, state=?, is_leaf=? WHERE category_key=?";
		PreparedStatement pstmt = con.prepareStatement(CategorySQL.disableCategory);
		try {
			pstmt.setString(1,  category_key);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("카테고리 비활성화 중 에러");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
		
	}


	@Override
	public int updateCategory(String category_key, Category v) throws Exception {
		// 받아온 category_key로 검색하고, 있으면 CategoryDTO의 값으로 update
		// TODO: overloading으로 refactoring
		int result = 0;
		ResultSet rset = null;
		Connection con = cp.getConnection();
//		"UPDATE CATEGORY SET category_key=?, category_name=?, level=?, state=?, is_leaf=? WHERE category_key=?";
		PreparedStatement getPstmt = con.prepareStatement(CategorySQL.selectCategory);
		PreparedStatement setPstmt = con.prepareStatement(CategorySQL.updateCategory);
		try {
			getPstmt.setString(1, category_key);
			rset = getPstmt.executeQuery();
			
			if(rset.next()) {
				setPstmt.setString(6, category_key);
				setPstmt.setString(1, v.getCategoryKey());
				setPstmt.setString(2, v.getCategoryName());
				setPstmt.setInt(3, v.getLevel());
				setPstmt.setInt(4, v.getCategoryState());
				setPstmt.setBoolean(5, v.isLeaf());
				
				result = setPstmt.executeUpdate();
			}
			
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("프로덕트 수정 중 에러");
		} finally {
			DaoFrame.closePstmt(getPstmt);
			DaoFrame.closePstmt(setPstmt);
			cp.releaseConnection(con);
		}
		return result;
	}


	@Override
	public int delete(String k) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		// "SELECT * FROM CATEGORY WHERE category_key=?";
		PreparedStatement pstmt = con.prepareStatement(CategorySQL.deleteCategory);
		
		try {
			pstmt.setString(1, k);
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
	public Optional<Category> select(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Category> search(String K) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


	

}
