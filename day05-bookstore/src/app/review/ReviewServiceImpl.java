package app.review;

import java.util.List;

import app.dto.Review;
import app.frame.ReviewRepository;
import app.frame.ServiceFrame;

public class ReviewServiceImpl implements ServiceFrame<Integer, Review> {
	ReviewRepository<Integer, Review> dao;
	
	public ReviewServiceImpl() {
		dao = new ReviewDaoImpl();
	}

	@Override
	public int register(Review v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Review v) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(k);
	}

	@Override
	public Review get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Review> selectBadReviews() throws Exception {
		
		
		return null;
	}

	@Override
	public List<Review> search(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> review(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
