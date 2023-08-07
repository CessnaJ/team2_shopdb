package app.sql;

public class ReviewSQL {

	public static String ReviewSelect = 
			"SELECT * FROM REVIEW WHERE review_key=?";
	
	// PRODUCTKEY는 AUTOINCREMENT
	
	public static String ReviewDelete = 
			"DELETE FROM REVIEW WHERE review_key=?";
	
	// ? 들어있는것들 모두 조회
	public static String ReviewSelectWithBadWords = 
			"SELECT * FROM REVIEW WHERE comment LIKE ?";
	
	//			public static String custSelectAll = 
	//					"DELETE * FROM cust";


}
