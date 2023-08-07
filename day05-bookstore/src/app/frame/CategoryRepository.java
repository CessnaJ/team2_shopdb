package app.frame;

public interface CategoryRepository<K, V> extends DaoFrame<K, V> {
	public int disableCategory(String category_key) throws Exception;
	public int updateCategory(String category_key, V v) throws Exception;
}
