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
import app.frame.ServiceFrame;



class ProductSearchTest {
	Logger log = Logger.getLogger("Product");
	ServiceFrame<String, Product> service;
	
	@BeforeEach
	void before() {
		service = new ProductServiceImpl();

	}

	@DisplayName("Search Test")
	@Test
	void search() {
		try {
			List<Product> product = null;
			Scanner sc = new Scanner(System.in);
			String k = sc.nextLine();
			product = service.search(k);
			assertEquals("Test Error",product, "¼º°ø?");
		} catch (Exception e) {
			
			log.info(e.getMessage());
		}
	}
}

