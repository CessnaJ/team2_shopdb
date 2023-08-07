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

public class CategoryInsertTest {
	Logger log = Logger.getLogger("Category Insert Test");
	CategoryRepository<String, Category> repository;
	
	@BeforeEach
	void before() throws Exception {
		repository = new CategoryDaoImpl();
	}
	
	@AfterEach
	void after() throws Exception {
		repository.delete("KOR010105");
		log.info("ī�װ� ��� �� ���� ����");
	}
	
	@DisplayName("ī�װ� ���")
	@Test
	void delete() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			Category newCategory = Category.builder()
										.categoryKey("KOR010105")
										.categoryName("����/ȣ���Ҽ�")
										.categoryState(0)
										.isLeaf(true)
										.level(4)
										.build();
			
			int result = repository.insert(newCategory);
			assertEquals("ī�װ� ��� ����", result, 1);
		});
			
	}
}