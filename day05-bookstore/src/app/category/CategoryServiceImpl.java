package app.category;

import java.util.List;

import app.dto.Category;
import app.dto.Review;
import app.frame.DaoFrame;
import app.frame.ServiceFrame;

public class CategoryServiceImpl implements ServiceFrame<String, Category>{
	DaoFrame<String, Category> dao;
		
	public CategoryServiceImpl() {
		dao = new CategoryDaoImpl();
	}
	
	
	@Override
	public int register(Category v) throws Exception {
		return dao.insert(v);
	}

	@Override
	public int modify(Category v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(String k) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category get(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Category> search(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
