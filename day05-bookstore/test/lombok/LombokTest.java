package lombok;

import static org.junit.Assert.assertEquals;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.dto.Cust;

import java.util.logging.Logger;

class LombokTest {
	
	Logger log = Logger.getLogger("Lombok Test");

	@Test
	void test() {
//		fail("Not yet implemented");
		Cust cust = Cust.builder()
					.id("id01")
					.name(null)
					.pwd(null)
					.build();
		log.info(cust.toString());
		
		assertEquals("Error!", cust.getId(), "id01");
	}

}
