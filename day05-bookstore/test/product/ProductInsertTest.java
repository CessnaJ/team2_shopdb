package product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.dto.Category;
import app.dto.Product;
import app.frame.ProductRepository;
import app.product.ProductDaoImpl;

public class ProductInsertTest {
	Logger log = Logger.getLogger("Category Insert Test");
	ProductRepository<Long, Product> repository;
	
	
	@BeforeEach
	void before() throws Exception {
		repository = new ProductDaoImpl();
	}
	
	@AfterEach
	void after() throws Exception {
		
		
	}
	
	@DisplayName("��ǰ ���")
	@Test
	void insert() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			
			Product newProduct = Product.builder()
										.productImgurl("dummyImg.jpg")
										.name("��¥�ѱ��Ҽ�")
										.price(25000)
										.content("��¥ �ְ��� �ѱ��Ҽ��Դϴ�.")
										.stock(500)
										.productPoint(2500)
										.discountRate(0.1)
										.salesCount(50)
										.status(1)
										.authorKey(1)
										.publishKey(1)
										.categoryKey("KOR010101")
										.build();
			
			
			int result = repository.insert(newProduct);
			assertEquals("��ǰ ��� ����", result, 1);
			log.info("��ǰ ��� ����");
		});
			
	}
}