package app.frame;

public class ReviewSQL {
	public static String reviewInsert = "INSERT INTO review(rating, comment, review_reg_date, review_is_deleted, member_key, product_key) values(?,?,now(),?,?,?)";
	public static String reviewUpdate = "";
	public static String reviewDelete = "DELETE FROM review WHERE review_key=?";
	public static String reviewSelect = "";
	
}
