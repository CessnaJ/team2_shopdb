package app.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import app.cust.MemServiceImpl;
import app.dto.Member;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static MemServiceImpl mi;
	
//		Logger log = Logger.getLogger("MemTest");
//		
//		MemService service = new MemService();
//		
//		Member inputMem = Member.builder()
//				.email("abc@gmail.com")
//				.hashed_pwd("qwer")
//				.memberName("abc")
//				.memberImgurl("https://lotteon/user/profile/img/abc.jpg")
//				.address("����� ������")
//				.memberPoint(0)
//				.isDormant(false)
//				.isAdmin(false)
//				.memberPhone("01012345678")
//				.build();
//		service.register(inputMem);

		public static void main(String[] args) throws Exception {
			System.out.println("--------------------------");
			System.out.println("         도서 판매점         ");
			System.out.println("--------------------------");
			System.out.print("메뉴 번호 선택: ");
			
			
			showMenu();
//			br.close();
		}
		static void showMenu() throws Exception {
			mi = new MemServiceImpl();
			
			while (true) {
				menuInfo();
				
				int cmd = Integer.parseInt(br.readLine());
				
				if (cmd == 1) {
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
					mi.register(inputMem);
					
				} else if (cmd == 2) {
					
				} else if (cmd == 3) {
					System.out.println("감사합니다, 또 이용해주세요.");
					break;
					
				} else {
					System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
				}
				System.out.println();
			}
		}
		static void menuInfo() {
			System.out.println("--------------------------");
			System.out.println("            메뉴           ");
			System.out.println("--------------------------");
			System.out.println("1. 회원가입");
			System.out.println("2. 전체 당첨 결과 확인하기");
			System.out.println("3. 종료");
		}
		static void showMyResult(int rank) {
			System.out.printf("%d등 당첨 축하합니다!\n", rank);
		}
	}
