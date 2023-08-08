package mem;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.MemServiceImpl;
import app.dto.Member;
import app.frame.ServiceFrame;

public class MemSelectTest {
	Logger log = Logger.getLogger("MemberTest");
	
	ServiceFrame<Member, Member> service;
	
	@BeforeEach
	void before() throws Exception{
		service = new MemServiceImpl();
	}
	
	@DisplayName("Member Select Test")
	@Test
	void select() throws Exception{
		Member mem = Member.builder()
		.email("hong@gmail.com")
		.hashed_pwd("qwer")
		.build();
		
		Member findMem = service.get(mem);
		log.info(findMem.toString());
		
		assertEquals("Test Error", findMem.getMemberKey(), (long) 2);
		
	}
}
