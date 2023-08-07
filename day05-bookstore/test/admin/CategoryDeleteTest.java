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

public class CategoryDeleteTest {
	Logger log = Logger.getLogger("Category Insert Test");
	CategoryRepository<String, Category> repository;
	
	@BeforeEach
	void before() throws Exception {
		repository = new CategoryDaoImpl();
	}
	
	@AfterEach
	void after() throws Exception {
		
		repository.delete("KOR010106");
		log.info("ī�װ� ���� ����");
	}
	

	
	@DisplayName("ī�װ� ��� �� ��Ȱ��ȭ")
	@Test
	void delete() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			Category newCategory = Category.builder()
										.categoryKey("KOR010106")
										.categoryName("��Ƽ��ŷ �Ҽ� Ư����")
										.categoryState(0)
										.isLeaf(true)
										.level(4)
										.build();
			int insertRes = repository.insert(newCategory);
			int result = repository.disableCategory("KOR010106");
			assertEquals("ī�װ� ��Ȱ��ȭ ����", result, 1);
			log.info("ī�װ� ��� �� ��Ȱ��ȭ ����");
		});
			
	}
}