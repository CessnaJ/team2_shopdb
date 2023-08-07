//package app.cust;
// 푸쉬 테스트12345
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//import java.util.logging.Logger;
//
//import app.dto.Cust;
//import app.frame.ConnectionPool;
//import app.frame.DaoFrame;
//import app.frame.SQL;
//
//public class CustDaoImpl implements DaoFrame<String, Cust>{
//	ConnectionPool cp;
//	Logger log = Logger.getLogger("Lombok Test");
//	
//	public CustDaoImpl() {
//		try {
//			cp = ConnectionPool.create();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	@Override
//	public int insert(Cust v) throws Exception {
//		int result = 0;
//		// TODO Auto-generated method stub
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = con.prepareStatement(SQL.custInsert);
//		
//		try {
//			pstmt.setString(1,  v.getId());
//			pstmt.setString(2,  v.getPwd());
//			pstmt.setString(3,  v.getName()); // ? 
//			result = pstmt.executeUpdate(); //예외는 던지지만 close를 하는게 아니라서 예외를 어떻게 처리할지 여기서 제대로 해줘야함.
//		} catch(Exception e) {
//			log.info(e.getMessage());
//			throw new Exception("아이디중복에러");
//		} finally {
//			DaoFrame.closePstmt(pstmt);
//			cp.releaseConnection(con);
//		}
//		return result;
//	}
//
//	@Override
//	public int update(Cust v) throws Exception {
//		int result = 0;
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = con.prepareStatement(SQL.custDelete);
//		try {
////		pstmt.setString(1,  v); // TODO: 고쳐라.
//		result = pstmt.executeUpdate();
//		} catch(Exception e) {
//			log.info(e.getMessage());
//			throw new Exception("뭔가의 이유로 삭제가 안되었음.");
//		} finally {
//			DaoFrame.closePstmt(pstmt);
//			cp.releaseConnection(con);
//		}
//		// TODO Auto-generated method stub
//		return result;
//	}
//
//	@Override
//	public int delete(String k) throws Exception {
//		int result = 0;
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = con.prepareStatement(SQL.custDelete);
//		try {
//		pstmt.setString(1,  k);
//		result = pstmt.executeUpdate(); // 반영이 안되어도 에러안남. 삭제할게 없어도 return값 (row affected가 0인걸로 정상반영됨)
//		} catch(Exception e) {
//			log.info(e.getMessage());
//			throw new Exception("삭제에러.");
//		} finally {
//			DaoFrame.closePstmt(pstmt);
//			cp.releaseConnection(con);
//		}
//		// TODO Auto-generated method stub
//		return result;
//	}
//
//	@Override
//	public Optional<Cust> select(String k) throws Exception {
//		Cust cust = null;
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
//			pstmt = con.prepareStatement(SQL.custSelect);
//			pstmt.setString(1, k);
//			rset = pstmt.executeQuery();
//			if (rset.next()) { // 한칸을 전진시키고 데이터를 끄집어 내야한다.
//			
//				cust = Cust.builder()
//							.id(rset.getString("id"))
//							.pwd(rset.getString("pwd"))
//							.name(rset.getString("name"))
//							.build(); 
//			}
//			
//		} catch(Exception e) {
//			log.info(e.getMessage());
//			throw new Exception("아이디중복에러");
//		} finally {
//			DaoFrame.closePstmt(pstmt);
//			cp.releaseConnection(con);
//		}
////		return result;
//		
//		
//		return cust;
//	}
//
//	@Override
//	public List<Cust> select() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
