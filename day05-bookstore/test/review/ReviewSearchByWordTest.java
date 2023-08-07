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
	
	@DisplayName("ī�װ� ���")
	@Test
	void delete() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			String keyword = "�־�";
			List<Review> list = repository.selectReviewsWithKeyword(keyword);
			log.info(keyword + " Ű����� " + list.size() + "���� ���䰡 �ֽ��ϴ�.");
			log.info("Ű����� ���� ã�� ����");
		});
			
	}
}