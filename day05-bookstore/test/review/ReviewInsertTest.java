package review;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.ReviewServiceImpl;
import app.dto.Review;
import app.frame.ServiceFrame;

public class ReviewInsertTest {
	Logger log = Logger.getLogger("ReviewTest");
	
	ServiceFrame<Integer, Review> service;
	
	@BeforeEach
	void before() throws Exception{
		service = new ReviewServiceImpl();
	}
	
	@AfterEach
	void after() {
		try {
			service.remove(14);
		} catch(Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@DisplayName("Review Insert Test")
	@Test
	void insert() throws Exception{
		Review inputReview = Review.builder()
				.reviewKey(14)
				.rating(4)
				.comment("좋아요 만족합니다")
				.reviewIsDeleted(false)
				.memberKey(1)
				.productKey(2)
				.build();
		service.register(inputReview);
	}
}
