package admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.category.CategoryDaoImpl;
import app.dto.Category;
import app.frame.CategoryRepository;

public class CategoryUpdateTest {
	Logger log = Logger.getLogger("Category Insert Test");
	CategoryRepository<String, Category> repository;
	
	@BeforeEach
	void before() throws Exception {
		repository = new CategoryDaoImpl();
	}
	
	@AfterEach
	void after() throws Exception {
		Category givenCategory = Category.builder()
				.categoryKey("KOR010104")
				.categoryName("한국의 위대한 소설가들")
				.categoryState(0)
				.isLeaf(true)
				.level(4)
				.build();
		int result = repository.updateCategory("KOR010104", givenCategory);
		log.info("카테고리 수정 성공");
	}
	
	@DisplayName("카테고리 수정")
	@Test
	void update() throws Exception {
		
		Exception exception = assertThrows(Exception.class, () -> {			
			Category updatedCategory = Category.builder()
										.categoryKey("KOR010104")
										.categoryName("한국의 위대한 소설가 특집")
										.categoryState(0)
										.isLeaf(true)
										.level(4)
										.build();
			
			int result = repository.updateCategory("KOR010104", updatedCategory);
			assertEquals("카테고리 수정 에러", result, 1);
		});
			
	}
	
	
	
}