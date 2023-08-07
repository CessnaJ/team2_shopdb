package mem;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cust.MemServiceImpl;
import app.dto.Member;
import app.frame.ServiceFrame;

public class MemInsertTest {
	Logger log = Logger.getLogger("MemTest");
	
	ServiceFrame<Long, Member> service;
	
	@BeforeEach
	void before() throws Exception{
		service = new MemServiceImpl();
	}
	
	@DisplayName("Mem Insert Test")
	@Test
	void insert() throws Exception{
		Member inputMem = Member.builder()
				.email("abc@gmail.com")
				.hashed_pwd("qwer")
				.memberName("abc")
				.memberImgurl("https://lotteon/user/profile/img/abc.jpg")
				.address("서울시 강동구")
				.memberPoint(0)
				.isDormant(false)
				.isAdmin(false)
				.memberPhone("01012345678")
				.build();
		service.register(inputMem);
	}
}
