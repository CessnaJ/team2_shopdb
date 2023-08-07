package app.main;

import java.util.Date;
import java.util.logging.Logger;

import app.dto.Member;
import app.service.MemService;

public class Main {

	public static void main(String[] args) {
		Logger log = Logger.getLogger("MemTest");
		
		MemService service = new MemService();
		
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
