package app.frame;

import java.util.List;

import app.dto.Review;

public interface ServiceFrame<K,V> {
	public int register(V v) throws Exception;
	public int modify(V v) throws Exception;
	public int remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
	public List<V> search(String k) throws Exception;
	public List<Review> review(String k) throws Exception;
}

