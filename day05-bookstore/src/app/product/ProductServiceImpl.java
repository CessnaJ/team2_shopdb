package app.product;

import java.util.List;

import app.dto.Product;
import app.dto.Review;
import app.frame.DaoFrame;
import app.frame.ServiceFrame;

public class ProductServiceImpl implements ServiceFrame<Long, Product>{
	DaoFrame<Long, Product> dao;
	
	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
	}
	
	
	@Override
	public int register(Product v) throws Exception {
		return dao.insert(v);
	}

	@Override
	public int modify(Product v) throws Exception {
		// TODO Auto-generated method stub
		return dao.update(v);
	}

	@Override
	public int remove(Long k) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(k);
	}

	@Override
	public Product get(Long k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Product> search(Long k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
