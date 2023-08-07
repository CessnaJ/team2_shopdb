package app.frame;

public class SQL {
	public static String custInsert = 
			"INSERT INTO cust VALUES (?, ?, ?)"; // ""안에 ;를 넣지 않는다. 사용할때 와일드카드 ?에 필요한것들 넣어줌.
	
	public static String custUpdate = 
			"UPDATE cust SET pwd=?, name=?, WHERE id=?";
	
	
	public static String productTopView =
			"SELECT * FROM product ORDER BY `sales_count` DESC LIMIT 20";
	
	public static String productSearch =
			"SELECT * FROM product WHERE author_key  = ? OR name LIKE CONCAT('%', ?, '%');";
	
	public static String productReview =
			"SELECT review.* FROM product JOIN review ON product.product_key = review.product_key WHERE product.product_key = ?;";
	
	public static String productCategorySearch = 
			"SELECT category_name FROM category WHERE level = ?;";
}
