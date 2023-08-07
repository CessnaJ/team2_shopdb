package app.frame;

import java.util.List;

import app.dto.Review;

public interface ReviewRepository<K, V> extends DaoFrame<K, V> {
	public List<Review> selectBadReviews() throws Exception;

}
