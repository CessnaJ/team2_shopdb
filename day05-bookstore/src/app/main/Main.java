package app.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import app.cart.CartServiceImpl;
import app.cust.MemServiceImpl;
import app.cust.ProductServiceImpl;
import app.dto.CartDetail;
import app.dto.CartUpdate;
import app.dto.Member;
import app.dto.Order;
import app.dto.Product;
import app.order.OrderServiceImpl;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static MemServiceImpl mi;
	static ProductServiceImpl pro;
	static CartServiceImpl cart;
	static OrderServiceImpl ord;
	static Member inputMem = null;
	static final long memberKey = 1;
	
		public static void main(String[] args) throws Exception {
			System.out.println("--------------------------");
			System.out.println("         도서 판매점         ");
			System.out.println("--------------------------");
			System.out.println("메뉴 번호 선택: ");
			
			
			showMenu();
			br.close();
		}
		static void showMenu() throws Exception {
			mi = new MemServiceImpl();
			pro = new ProductServiceImpl();
			cart = new CartServiceImpl();
			ord = new OrderServiceImpl();
			
			while (true) {
				menuInfo1();
				
				int cmd = Integer.parseInt(br.readLine());
				
				inputMem = Member.builder()
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
				
				if (cmd == 1) {
					mi.register(inputMem);
					
				} else if (cmd == 2) {
					 System.out.println("반갑습니다.");
					 menuInfo2();
					 
					 while(true) {
						 int cmd2 = Integer.parseInt(br.readLine());
						 if (cmd2 == 1) {
							 List<Product> list = new ArrayList<>();
							 list = pro.get();
							 
							 for (int i = 0; i < list.size(); i++) {
								System.out.println("번호: " + list.get(i).getProductKey());
								System.out.println("책 제목: " + list.get(i).getName());
								System.out.println("가격: " + list.get(i).getPrice());
								System.out.println("소개: " + list.get(i).getContent());
							}
						 } else if (cmd2 == 2) {
							 
						 } else if (cmd2 == 3) {
							 
						 } else if (cmd2 == 4) {
							 System.out.println("책 번호와 구매 수량을 입력해주세요.");
							 int productKey = 0, productCount = 0, orderCartId = 0;
							 st = new StringTokenizer(br.readLine());
							 productKey = Integer.parseInt(st.nextToken());
							 productCount = Integer.parseInt(st.nextToken());
							 
							 CartDetail item = CartDetail.builder().
										insertState(1).
										productOrderCount(productCount). // 100 => 재고 없음
										key1(memberKey).
										key2(productKey). // 3 => 판매 중단 상품
										Insert1Build();
							 
							 System.out.println("장바구니로 이동합니다.");
							 System.out.println();
							 
							 System.out.println("[장바구니 목록]");
							 CartUpdate cartId = CartUpdate.builder().key(memberKey).build();
							 List<CartDetail> carts = cart.search(cartId);
								for (int i = 0; i < carts.size(); i++) {
									System.out.println("장바구니 고유 번호: " + carts.get(i).getCartId() + " " + "주문 개수: " + carts.get(i).getProductOrderCount() + " " + "장바구니 상태: " + carts.get(i).getCartState() + " " + "등록일: " + carts.get(i).getRegDate() + " 상품명: " + carts.get(i).getName() + " 상품 가격: " + carts.get(i).getPrice() + " 상품 상세: " + carts.get(i).getContent() + " 상품 재고: " + carts.get(i).getStock() + " 상품 적립 포인트: " + carts.get(i).getProductPoint() + " 상품 할인율: " + carts.get(i).getDiscountRate() + " 작가명: " + carts.get(i).getAuthorName() + " 출판사명: " + carts.get(i).getPublisherName() + " 카테고리명: " + carts.get(i).getCategoryName());
								}
								
							System.out.println("주문할 상품의 장바구니 고유 번호를 입력해주세요.");
							orderCartId = Integer.parseInt(br.readLine());
							
							int cnt = 0;
							
							item = CartDetail.builder().
									updateState(2).
									intInput(1).
									longInput(orderCartId).
									UpdateBuild();
							cnt += cart.modify(item);
							item = CartDetail.builder().
									insertState(2).
									memberKey(memberKey).
									Insert2Build();
							cnt += cart.register(item);
							item = CartDetail.builder().
									updateState(3).
									intInput(productCount).
									longInput(productKey).
									UpdateBuild();
							cnt += cart.modify(item);
							item = CartDetail.builder().
									insertState(3).
									productOrderCount(productCount). // 100 => 재고 없음
									key1(orderCartId).
									key2(productKey). // 3 => 판매 중단 상품
									Insert1Build();
							cnt += cart.register(item);
							
							if (cnt == 4) System.out.println("주문 완료!");
								
						 } else if (cmd2 == 5)  {
							 Order inputOrder = null;
							 inputOrder = Order.builder()
										.memberKey(memberKey)
										.orderPhone(inputMem.getMemberPhone())
										.streetAddr(inputMem.getAddress())
										.orderState(0)
										.build();
										ord.register(inputOrder);
										
							Product inputProduct = Product.builder()
										.productKey(1)
										.stock(9)
										.salesCount(1)
										.build();

								int res = pro.modify(inputProduct);
								if(res == 1) {
									System.out.println("구매 완료되었습니다");
								} else {
									System.out.println("구매 실패하였습니다. 다시 시도해주세요.");
								}
						 } else if (cmd2 == 6) {
							 System.out.println("로그아웃 되었습니다.");
						 } else {
							 System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
						 }
					 }
					
				} else if (cmd == 3) {
					System.out.println("감사합니다, 또 이용해주세요.");
					break;
				} else {
					System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
				}
				System.out.println();
			}
		}
		// 회원가입 및 로그인
		static void menuInfo1() {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");			
			System.out.println("3. 종료");
		}
		// 도서 검색
		static void menuInfo2() {
			System.out.println("1. 추천 도서 목록");
			System.out.println("2. 도서 검색(제목)");
			System.out.println("3. 도서 검색(작가)");
			System.out.println("4. 장바구니 담기");
			System.out.println("5. 바로 구매");
			System.out.println("6. 로그아웃");
		}
	}