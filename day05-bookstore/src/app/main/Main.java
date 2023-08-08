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
			System.out.println("         ���� �Ǹ���         ");
			System.out.println("--------------------------");
			System.out.println("�޴� ��ȣ ����: ");
			
			
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
						.address("����� ������")
						.memberPoint(0)
						.isDormant(false)
						.isAdmin(false)
						.memberPhone("01012345678")
						.build();
				
				if (cmd == 1) {
					mi.register(inputMem);
					
				} else if (cmd == 2) {
					 System.out.println("�ݰ����ϴ�.");
					 menuInfo2();
					 
					 while(true) {
						 int cmd2 = Integer.parseInt(br.readLine());
						 if (cmd2 == 1) {
							 List<Product> list = new ArrayList<>();
							 list = pro.get();
							 
							 for (int i = 0; i < list.size(); i++) {
								System.out.println("��ȣ: " + list.get(i).getProductKey());
								System.out.println("å ����: " + list.get(i).getName());
								System.out.println("����: " + list.get(i).getPrice());
								System.out.println("�Ұ�: " + list.get(i).getContent());
							}
						 } else if (cmd2 == 2) {
							 
						 } else if (cmd2 == 3) {
							 
						 } else if (cmd2 == 4) {
							 System.out.println("å ��ȣ�� ���� ������ �Է����ּ���.");
							 int productKey = 0, productCount = 0, orderCartId = 0;
							 st = new StringTokenizer(br.readLine());
							 productKey = Integer.parseInt(st.nextToken());
							 productCount = Integer.parseInt(st.nextToken());
							 
							 CartDetail item = CartDetail.builder().
										insertState(1).
										productOrderCount(productCount). // 100 => ��� ����
										key1(memberKey).
										key2(productKey). // 3 => �Ǹ� �ߴ� ��ǰ
										Insert1Build();
							 
							 System.out.println("��ٱ��Ϸ� �̵��մϴ�.");
							 System.out.println();
							 
							 System.out.println("[��ٱ��� ���]");
							 CartUpdate cartId = CartUpdate.builder().key(memberKey).build();
							 List<CartDetail> carts = cart.search(cartId);
								for (int i = 0; i < carts.size(); i++) {
									System.out.println("��ٱ��� ���� ��ȣ: " + carts.get(i).getCartId() + " " + "�ֹ� ����: " + carts.get(i).getProductOrderCount() + " " + "��ٱ��� ����: " + carts.get(i).getCartState() + " " + "�����: " + carts.get(i).getRegDate() + " ��ǰ��: " + carts.get(i).getName() + " ��ǰ ����: " + carts.get(i).getPrice() + " ��ǰ ��: " + carts.get(i).getContent() + " ��ǰ ���: " + carts.get(i).getStock() + " ��ǰ ���� ����Ʈ: " + carts.get(i).getProductPoint() + " ��ǰ ������: " + carts.get(i).getDiscountRate() + " �۰���: " + carts.get(i).getAuthorName() + " ���ǻ��: " + carts.get(i).getPublisherName() + " ī�װ���: " + carts.get(i).getCategoryName());
								}
								
							System.out.println("�ֹ��� ��ǰ�� ��ٱ��� ���� ��ȣ�� �Է����ּ���.");
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
									productOrderCount(productCount). // 100 => ��� ����
									key1(orderCartId).
									key2(productKey). // 3 => �Ǹ� �ߴ� ��ǰ
									Insert1Build();
							cnt += cart.register(item);
							
							if (cnt == 4) System.out.println("�ֹ� �Ϸ�!");
								
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
									System.out.println("���� �Ϸ�Ǿ����ϴ�");
								} else {
									System.out.println("���� �����Ͽ����ϴ�. �ٽ� �õ����ּ���.");
								}
						 } else if (cmd2 == 6) {
							 System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
						 } else {
							 System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
						 }
					 }
					
				} else if (cmd == 3) {
					System.out.println("�����մϴ�, �� �̿����ּ���.");
					break;
				} else {
					System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				}
				System.out.println();
			}
		}
		// ȸ������ �� �α���
		static void menuInfo1() {
			System.out.println("1. ȸ������");
			System.out.println("2. �α���");			
			System.out.println("3. ����");
		}
		// ���� �˻�
		static void menuInfo2() {
			System.out.println("1. ��õ ���� ���");
			System.out.println("2. ���� �˻�(����)");
			System.out.println("3. ���� �˻�(�۰�)");
			System.out.println("4. ��ٱ��� ���");
			System.out.println("5. �ٷ� ����");
			System.out.println("6. �α׾ƿ�");
		}
	}