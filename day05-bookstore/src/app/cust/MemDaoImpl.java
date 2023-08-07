package app.cust;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Member;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.frame.MemSQL;

public class MemDaoImpl implements DaoFrame<Long, Member>{
	Logger log = Logger.getLogger("CustDaoImpl");
	ConnectionPool cp;
	
	public MemDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(Member v) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null; 
				
		try {
			pstmt = con.prepareStatement(MemSQL.memInsert);
			pstmt.setString(1, v.getEmail());
			pstmt.setString(2, v.getHashed_pwd());
			pstmt.setString(3, v.getMemberName());
			pstmt.setString(4, v.getMemberImgurl());
			pstmt.setString(5, v.getAddress());
			pstmt.setInt(6, v.getMemberPoint());
			pstmt.setBoolean(7, v.isDormant());
			pstmt.setBoolean(8, v.isAdmin());
			pstmt.setString(9, v.getMemberPhone());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("아이디 중복 에러");
		}finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		
		return result;
	}

	@Override
	public int update(Member v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long k) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Member> select(Long k) throws Exception {
		Member mem = null;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(MemSQL.memSelect);
			pstmt.setLong(1, k);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mem = Member.builder().memberKey(rset.getLong("member_key"))
						.email(rset.getString("email"))
						.hashed_pwd(rset.getString("hashed_pwd"))
						.memberName(rset.getString("member_name"))
						.memberImgurl(rset.getString("member_imgurl"))
						.address(rset.getString("member_imgurl"))
						.memberPoint(rset.getInt("member_point"))
						.memberRegDate(rset.getDate("member_reg_date"))
						.isDormant(rset.getBoolean("is_dormant"))
						.isAdmin(rset.getBoolean("is_admin"))
						.memberPhone(rset.getString("member_phone"))
						.build();			
			}
		}catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("조회 에러.");
		}finally {
			DaoFrame.closeRset(rset);
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return Optional.of(mem);
	}

	@Override
	public List<Member> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
