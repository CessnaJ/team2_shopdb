package cust;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.CustServiceImpl;
import app.dto.Cust;
import app.frame.ServiceFrame;

public class CustInsertTest {
	Logger log = Logger.getLogger("CustTest");
	
	ServiceFrame<String, Cust> service;
	
	@BeforeEach
	void before() {
		service = new CustServiceImpl();
		try {
			service.remove("id88");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@AfterEach
	void after() {
		try {
			service.remove("id88");
//			Cust cust = service.remove("id88");
//			log.info(cust.toString());
		} catch(Exception e) {
			log.info(e.getMessage());
		}
	}
	
	
	@DisplayName("Cust Insert Test")
	@Test
	void insert() {
		Cust inputCust = Cust.builder()
		.id("id88")
		.pwd("pwd88")
		.name("james88")
		.build();
		
		try {
			service.register(inputCust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace(); // 여기서 찍고 핸들링 하고 끝내면 상위에서는 파악이 불가능. 한번 더 위로 던져보자.
			log.info(e.getMessage());
		}
	}
	
	
	@DisplayName("Cust Insert Test2")
	@Test
	void insert2() {
		
		Exception exception = assertThrows(Exception.class, () -> {});
		assertEquals("Test Error", exception.getMessage(), "");
		
		Cust inputCust = Cust.builder()
		.id("id77")
		.pwd("pwd77")
		.name("james77")
		.build();
		
		try {
			service.register(inputCust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace(); // 여기서 찍고 핸들링 하고 끝내면 상위에서는 파악이 불가능. 한번 더 위로 던져보자.
			log.info(e.getMessage());
		}
	}
}
