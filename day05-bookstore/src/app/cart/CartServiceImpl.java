package app.cart;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import app.dto.CartDetail;
import app.dto.CartUpdate;
import app.dto.Review;
import app.frame.DaoFrame;
import app.frame.ServiceFrame;

public class CartServiceImpl implements ServiceFrame<CartUpdate, CartDetail>{

	Logger log = Logger.getLogger("Cart Service");
	
	DaoFrame<CartUpdate, CartDetail> dao;
	
	public CartServiceImpl() {
		dao = new CartDaoImpl();
	}
	
	@Override
	public int register(CartDetail v) throws Exception {
		return dao.insert(v);
	}

	@Override
	public int modify(CartDetail v) throws Exception {
		return dao.update(v);
	}

	@Override
	public int remove(CartUpdate k) throws Exception {
		int result = 0;
		result = dao.delete(k);
		
		if(result != 1) {
			throw new Exception("Nothing Deleted");
		}
		
		return result;
	}

	@Override
	public CartDetail get(CartUpdate k) throws Exception {
		Optional<CartDetail> result = dao.select(k);
		if (result.isEmpty()) {
			throw new Exception("장바구니 개별 조회 - 조회 결과 없음");
		}
		return result.get();
	}

	@Override
	public List<CartDetail> get() throws Exception {
		List<CartDetail> result = dao.select();
		if (result.size() == 0) {
			throw new Exception("장바구니 전체 조회 - 조회 결과 없음");
		}
		return result;
	}

	@Override
	public List<CartDetail> search(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
