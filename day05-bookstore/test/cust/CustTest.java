package cust;


//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.CustServiceImpl;
import app.dto.Cust;
import app.frame.ServiceFrame;

class CustTest {
	Logger log = Logger.getLogger("CustTest");
	
	ServiceFrame<String, Cust> service;
	
	@BeforeEach
	void before() {
		service = new CustServiceImpl();
	}
	
	
	@DisplayName("Cust Insert Test")
	@Test
	void insert() {
		Cust inputCust = Cust.builder()
		.id("id01")
		.pwd("pwd05")
		.name("james5")
		.build();
		
		try {
			service.register(inputCust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace(); // ���⼭ ��� �ڵ鸵 �ϰ� ������ ���������� �ľ��� �Ұ���. �ѹ� �� ���� ��������.
			log.info(e.getMessage());
		}
	}
	
	@DisplayName("Cust Select Test")
	@Test
	void select() {
		Cust cust = null;
				
		try {
			cust = service.get("id01");
			log.info("�ߵǰ��ִ�"+cust.toString());
			assertEquals("Select Error", cust.getId(), "id01");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace(); // ���⼭ ��� �ڵ鸵 �ϰ� ������ ���������� �ľ��� �Ұ���. �ѹ� �� ���� ��������.
			log.info(e.getMessage());
			log.info("�� �ȵǾ���"+cust.toString());
		}
	}
	
	
//	@DisplayName("Cust Update Test")
//	@Test
//	void update() {
//		Cust cust = null;
//				
//		try {
//			cust = service.get("id01");
//			log.info("�ߵǰ��ִ�"+cust.toString());
//			assertEquals("Select Error", cust.getId(), "id01");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace(); // ���⼭ ��� �ڵ鸵 �ϰ� ������ ���������� �ľ��� �Ұ���. �ѹ� �� ���� ��������.
//			log.info(e.getMessage());
//			log.info("�� �ȵǾ���"+cust.toString());
//		}
//	}
	
	
	@DisplayName("Cust Delete Test")
	@Test
	void delete() {
		try {
			service.remove("id05");
			log.info("�����Ǿ���?");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace(); // ���⼭ ��� �ڵ鸵 �ϰ� ������ ���������� �ľ��� �Ұ���. �ѹ� �� ���� ��������.
			log.info(e.getMessage());
			log.info("�� �ȵǾ���");
		}
		log.info("�����¾���");
	}
	

}
