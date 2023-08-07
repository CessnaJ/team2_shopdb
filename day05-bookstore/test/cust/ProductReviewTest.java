package cust;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.ProductServiceImpl;
import app.dto.Product;
import app.frame.ServiceFrame;



class ProductReviewTest {
	Logger log = Logger.getLogger("Product");
	ServiceFrame<String, Product> service;
	
	@BeforeEach
	void before() {
		service = new ProductServiceImpl();

	}

	@DisplayName("topView Test")
	@Test
	void select() {
		try {
			List<Product> product = null;
			product = service.get();
			assertEquals("Test Error",product, "¼º°ø?");
		} catch (Exception e) {
			
			log.info(e.getMessage());
		}
	}
}

