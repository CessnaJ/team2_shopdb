package app.order;


import java.util.List;
import java.util.Optional;

import app.dto.Order;
import app.dto.Review;
import app.frame.DaoFrame;
import app.frame.ServiceFrame;

public class OrderServiceImpl implements ServiceFrame<Integer, Order>{

	DaoFrame<Integer, Order> dao;

	public OrderServiceImpl() {
		dao = new OrderDaoImpl();
	}

	@Override
	public int register(Order v) throws Exception {
		return dao.insert(v);
	}

	@Override
	public int modify(Order v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Integer k) throws Exception {
		return dao.delete(k);
	}

	@Override
	public Order get(Integer k) throws Exception {
		Optional<Order> result = dao.select(k);
		if(result.isEmpty()) {
			throw new Exception("��ȸ������ �����ϴ�");
		}
		return result.get();
	}

	@Override
	public List<Order> get() throws Exception {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public List<Order> search(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}