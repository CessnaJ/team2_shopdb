package app.frame;

import java.util.List;
import java.util.Optional;

import app.dto.Review;

public interface ServiceFrame<K, V> {

	public List<V> get() throws Exception;
	public List<V> search(String k) throws Exception;
	public List<Review> review(String k) throws Exception;
}
