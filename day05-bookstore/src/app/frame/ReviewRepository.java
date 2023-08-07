package app.frame;

import java.util.List;

import app.dto.Review;

public interface ReviewRepository<K, V> extends DaoFrame<K, V> {
	public List<Review> selectReviewsWithKeyword(String keyword) throws Exception;
	
//	public List<Review> 

}
