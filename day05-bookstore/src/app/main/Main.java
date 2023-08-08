package app.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import app.cust.MemServiceImpl;
import app.product.ProductServiceImpl;
import app.dto.Member;
import app.dto.Order;
import app.dto.Product;
import app.order.OrderServiceImpl;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static MemServiceImpl mi;
	static OrderServiceImpl oi;
	static ProductServiceImpl pi;
	
		public static void main(String[] args) throws Exception {
			System.out.println("--------------------------");
			System.out.println("         �룄�꽌 �뙋留ㅼ젏         ");
			System.out.println("--------------------------");
			System.out.println("硫붾돱 踰덊샇 �꽑�깮: ");
			
			
			showMenu();
			br.close();
		}
		static void showMenu() throws Exception {
			mi = new MemServiceImpl();
			oi = new OrderServiceImpl();
			pi = new ProductServiceImpl();
			
			
			while (true) {
				menuInfo();
				
				int cmd = Integer.parseInt(br.readLine());
				Member inputMem = null;
				Order inputOrder = null;
				Product inputProduct = null;
				
				inputMem = Member.builder()
						.email("abc@gmail.com")
						.hashed_pwd("qwer")
						.memberName("abc")
						.memberImgurl("https://lotteon/user/profile/img/abc.jpg")
						.address("�꽌�슱�떆 媛뺣룞援�")
						.memberPoint(0)
						.isDormant(false)
						.isAdmin(false)
						.memberPhone("01012345678")
						.build();
				
				if (cmd == 1) {
					
					mi.register(inputMem);
					
				} else if (cmd == 2) {

				} else if (cmd == 3) {

				} else if (cmd == 4) {
					inputOrder = Order.builder()
							.memberKey(1)
							.orderPhone(inputMem.getMemberPhone())
							.streetAddr(inputMem.getAddress())
							.orderState(0)
							.build();
					oi.register(inputOrder);
					
					
					inputProduct = Product.builder()
							.productKey(1)
							.content("구매 1가 된 상품")
							.stock(9)
							.salesCount(1)
							.build();
					
					int res = pi.modify(inputProduct);
					if(res == 1) {
						System.out.println("구매 완료되었습니다");
					} else {
						System.out.println("구매 실패하였습니다. 다시 시도해주세요.");
					}
				}
				else if(cmd == 5) {
					inputOrder = Order.builder()
							.orderState(6)
							.orderKey(1)
							.build();
					
					oi.modify(inputOrder);
					
					inputProduct = Product.builder()
							.productKey(1)
							.stock(10)
							.salesCount(0)
							.build();
					pi.modify(inputProduct);
					
					inputMem = Member.builder()
							.memberPoint(100)
							.memberKey(1)
							.build();
					int res = mi.modify(inputMem);
					if(res == 1) {
						System.out.println("구매 확정되었습니다.");
					} else {
						System.out.println("구매 확정 실패하였습니다. 다시 시도해주세요.");
					}
				}
				else if(cmd == 100) {
						System.out.println("媛먯궗�빀�땲�떎, �삉 �씠�슜�빐二쇱꽭�슂.");
						break;
				}
				else {
					System.out.println("�옒紐삳맂 踰덊샇瑜� �엯�젰�븯�뀲�뒿�땲�떎. �떎�떆 �엯�젰�빐二쇱꽭�슂.");
				}
				System.out.println();
			}
		}
		static void menuInfo() {
			System.out.println("1. �쉶�썝媛��엯");
			System.out.println("2. 濡쒓렇�씤");
			System.out.println("3. 由щ럭 �깮�꽦");	
			System.out.println("4. 상품 바로 구매");
			System.out.println("4. 醫낅즺");
			System.out.println("5. 구매 확정");
		}
	}
