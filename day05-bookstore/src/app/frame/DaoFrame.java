package app.frame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import app.dto.Product;
import app.dto.Review;

// Generic Type(Key, Value)
public interface DaoFrame<K,V> {
	// 사항 없으면 0, 있으면 1
	public int insert(V v) throws Exception;
	public int update(V v) throws Exception;
	public int delete(K k) throws Exception;
	public Optional<V> select(K k) throws Exception;
	public List<V> select() throws Exception;
	public List<V> search(K K) throws Exception;
	public List<Review> review(String k) throws Exception;
	
	public static void closePstmt(PreparedStatement pstmt) throws Exception {
		if (pstmt != null) {
			pstmt.close();
		}
	}
	
	public static void closeRset(ResultSet rset) throws Exception {
		if (rset != null) {
			rset.close();
		}
	}
}

