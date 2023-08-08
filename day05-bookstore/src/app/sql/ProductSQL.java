package app.sql;

public class ProductSQL {
		// PRODUCTKEY�� AUTOINCREMENT
		public static String productInsert = 
				"INSERT INTO PRODUCT(product_imgurl, name, price, content, stock, \r\n"
				+ "	product_reg_date, product_update_date, product_point, discount_rate, sales_count,\r\n"
				+ "	`status`, author_key, publisher_key, category_key) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?, ?, ?, ?, ?, ?, ?)"; // ""�ȿ� ;�� ���� �ʴ´�. ����Ҷ� ���ϵ�ī�� ?�� �ʿ��Ѱ͵� �־���.
		
		public static String productUpdateContent = 
				"UPDATE PRODUCT SET content=?, WHERE product_key=?";
		
		public static String productDelete = 
				"DELETE FROM PRODUCT WHERE product_key=?";
		
		public static String productSelectById = 
				"SELECT * FROM PRODUCT WHERE product_key=?";
		
		public static String productSelectTop20OrderBySalescount = 
				"SELECT * FROM PRODUCT ORDER BY salescount DESC LIMIT 20";
		
		public static String productSelectSalescountById = 
				"SELECT salescount FROM PRODUCT WHERE product_key=?";
		
		public static String productUpdateSalesCountStock = 
				"UPDATE PRODUCT SET stock=?, sales_count=? WHERE product_key=?";
//		public static String custSelectAll = 
//				"DELETE * FROM cust";
}
