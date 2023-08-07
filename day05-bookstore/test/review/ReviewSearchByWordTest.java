package review;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.dto.Review;
import app.frame.ReviewRepository;
import app.review.ReviewDaoImpl;

public class ReviewSearchByWordTest {
	Logger log = Logger.getLogger("Review Search By Word Test");
	ReviewRepository<Integer, Review> repository;
	
	@BeforeEach
	void before() throws Exception {
		repository = new ReviewDaoImpl();
	}
	
	@AfterEach
	void after() throws Exception {
		
		
	}
	
	@DisplayName("카테고리 등록")
	@Test
	void delete() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			String keyword = "최악";
			List<Review> list = repository.selectReviewsWithKeyword(keyword);
			log.info(keyword + " 키워드로 " + list.size() + "건의 리뷰가 있습니다.");
			log.info("키워드로 리뷰 찾기 성공");
		});
			
	}
}