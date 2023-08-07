package app.frame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import app.dto.Product;
import app.dto.Review;

public interface DaoFrame<K, V> {
	
	public List<Product> select() throws Exception;
	public List<Product> search(String K) throws Exception;
	public List<Review> review(String k) throws Exception;
	
	public static void closePstmt(PreparedStatement pstmt) throws Exception {
		if(pstmt != null) {
			pstmt.close();
		}
	}
	
	public static void closeRset(ResultSet rset) throws Exception {
		if(rset != null) {
			rset.close();
		}
	}
	
}
