package cust;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.CustServiceImpl;
import app.dto.Cust;
import app.frame.ServiceFrame;

public class CustSelectTest {
Logger log = Logger.getLogger("CustTest");
	
	ServiceFrame<String, Cust> service;
	
	@BeforeEach
	void before() {
		service = new CustServiceImpl();
		Cust inputCust = Cust.builder()
				.id("id55")
				.pwd("pwd55")
				.name("james55")
				.build();
				
		try {
			service.register(inputCust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			
			log.info(e.getMessage());
		}
	}
	
	
	@AfterEach
	void after() {
		try {
			service.remove("id55");
//			Cust cust = service.remove("id88");
//			log.info(cust.toString());
		} catch(Exception e) {
			log.info(e.getMessage());
		}
	}
	
	
	@DisplayName("Cust Select Test")
	@Test
	void select() {
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
}
