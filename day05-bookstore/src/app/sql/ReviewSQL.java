package app.sql;

public class ReviewSQL {

	public static String ReviewSelect = 
			"SELECT * FROM REVIEW WHERE review_key=?";
	
	// PRODUCTKEY�� AUTOINCREMENT
	
	public static String ReviewDelete = 
			"DELETE FROM REVIEW WHERE review_key=?";
	
	// ? ����ִ°͵� ��� ��ȸ
	public static String ReviewSelectWithBadWords = 
			"SELECT * FROM REVIEW WHERE comment LIKE ?";
	
	//			public static String custSelectAll = 
	//					"DELETE * FROM cust";


}
