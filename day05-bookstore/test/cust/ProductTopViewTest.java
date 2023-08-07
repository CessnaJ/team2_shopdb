package cust;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.ProductServiceImpl;
import app.dto.Product;
import app.dto.Review;
import app.frame.ServiceFrame;



class ProductTopViewTest {
	Logger log = Logger.getLogger("Review");
	ServiceFrame<String, Product> service;
	
	@BeforeEach
	void before() {
		service = new ProductServiceImpl();

	}

	@DisplayName("Review Test")
	@Test
	void select() {
		try {
			List<Review> review = null;
			Scanner sc = new Scanner(System.in);
			String k = sc.nextLine();
			review = service.review(k);
			assertEquals("Test Error",review, "¼º°ø?");
		} catch (Exception e) {
			
			log.info(e.getMessage());
		}
	}
}

