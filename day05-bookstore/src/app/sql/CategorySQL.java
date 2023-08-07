package app.sql;

public class CategorySQL {
	
	public static String categoryInsert = 
			"INSERT INTO CATEGORY(category_key, category_name, state, level, is_leaf) VALUES(?, ?, ?, ?, ?)";
	
	public static String disableCategory = 
			"UPDATE CATEGORY SET state = 1 WHERE category_key=?";
	
	public static String updateCategory = 
			"UPDATE CATEGORY SET category_key=?, category_name=?, level=?, state=?, is_leaf=? WHERE category_key=?";
	
}
