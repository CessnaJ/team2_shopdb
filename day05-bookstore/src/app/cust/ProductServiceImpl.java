package app.cust;

import java.util.List;

import app.dto.Product;
import app.dto.Review;
import app.frame.DaoFrame;
import app.frame.ServiceFrame;

public class ProductServiceImpl implements ServiceFrame<String, Product> {

	DaoFrame<String, Product> dao;
	
	public ProductServiceImpl() {
		dao = new ProductDaoImpl();
	}

	@Override
	public List<Product> get() throws Exception {
		return dao.select();
	}

	@Override
	public List<Product> search(String k) throws Exception {
		return dao.search(k);
	}

	@Override
	public List<Review> review(String k) throws Exception {
		return dao.review(k);
	}

	@Override
	public int register(Product v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Product v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(String k) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product get(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
