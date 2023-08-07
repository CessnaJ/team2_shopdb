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
	
	@DisplayName("상품 등록")
	@Test
	void insert() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			
			Product newProduct = Product.builder()
										.productImgurl("dummyImg.jpg")
										.name("진짜한국소설")
										.price(25000)
										.content("진짜 최고의 한국소설입니다.")
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
			assertEquals("상품 등록 에러", result, 1);
			log.info("상품 등록 성공");
		});
			
	}
}