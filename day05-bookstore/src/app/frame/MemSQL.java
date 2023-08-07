package app.frame;

public class MemSQL {
	public static String memInsert = "INSERT INTO member(email, hashed_pwd, member_name, member_imgurl, address, member_point, member_reg_date, is_dormant, is_admin, member_phone) values(?,?,?,?,?,?,now(),?,?,?)";
	public static String memUpdate = "";
	public static String memDelete = "";
	public static String memSelect = "SELECT * FROM member WHERE member_key=?";
	
}
