package app.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.Order;
import app.dto.Review;
import app.frame.ConnectionPool;
import app.frame.DaoFrame;
import app.frame.OrderSQL;

public class OrderDaoImpl implements DaoFrame<Integer, Order> {
	ConnectionPool cp;
	Logger log = Logger.getLogger("Lombok Test");

	public OrderDaoImpl() {
		try {
			cp = ConnectionPool.create();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Order v) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(OrderSQL.OrderInsert);

		try {
//			pstmt.setInt(1,  v.getOrderState());
			pstmt.setString(1,  v.getStreetAddr());
			pstmt.setString(2,  v.getOrderPhone()); 
			pstmt.setLong(3, v.getMemberKey());
			result = pstmt.executeUpdate(); 
		} catch(Exception e) {
			log.info(e.getMessage());
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return result;
	}

	@Override
	public int update(Order v) throws Exception {
		int result = 0;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = con.prepareStatement(OrderSQL.OrderUpdate);
		try {
			pstmt.setInt(1,  v.getOrderState()); // TODO: ���Ķ�.
			pstmt.setLong(2, v.getOrderKey());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("������ ������ UPDATE �ȵǾ���.");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		// TODO Auto-generated method stub
		return result;	
		}

	@Override
	public int delete(Integer k) throws Exception {
		int result = 0;
		// 현재는 삭제 기능 없음
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = con.prepareStatement(OrderSQL.OrderDelete);
//
//		try {
//			pstmt.setLong(1, k);
//			result = pstmt.executeUpdate(); 
//		} catch(Exception e) {
//			log.info(e.getMessage());
//		} finally {
//			DaoFrame.closePstmt(pstmt);
//			cp.releaseConnection(con);
//		}
		return result;
	}

	@Override
	public Optional<Order> select(Integer k) throws Exception {
		Order order = null;
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(OrderSQL.OrderSelect);
			pstmt.setInt(1, k);
			rset = pstmt.executeQuery();
			if (rset.next()) { // ��ĭ�� ������Ű�� �����͸� ������ �����Ѵ�.

				order = Order.builder()
						.orderKey(rset.getLong("orderKey"))
						.orderState(rset.getInt("orderState"))
						.streetAddr(rset.getString("streetAddr"))
						.orderPhone(rset.getString("orderPhone"))
						.orderDate(rset.getDate("orderDate"))
						.build(); 
			}

		} catch(Exception e) {
			log.info(e.getMessage());
			throw new Exception("���̵��ߺ�����");
		} finally {
			DaoFrame.closePstmt(pstmt);
			cp.releaseConnection(con);
		}
		return Optional.of(order);

	}

	@Override
	public List<Order> select() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> search(Integer K) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}