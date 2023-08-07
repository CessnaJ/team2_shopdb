package cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cart.CartServiceImpl;
import app.dto.CartDetail;
import app.dto.CartUpdate;
import app.frame.ServiceFrame;

public class CartInsertTest {

	Logger log = Logger.getLogger("Cart Insert Test");
	ServiceFrame<CartUpdate, CartDetail> service;
	
	@BeforeEach
	void before() throws Exception {
		service = new CartServiceImpl();
	}
	
	@DisplayName("��ٱ��Ͽ� å ���")
	@Test
	void insert1() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			// 2�� ȸ���� 2��° ��ǰ 2�� ����
			CartDetail detail = CartDetail.builder().
					insertState(1).
					productOrderCount(2). // 100 => ��� ����
					key1(1).
					key2(3). // 3 => �Ǹ� �ߴ� ��ǰ
					Insert1Build();
			int result = service.register(detail);
			assertEquals("��ٱ��� ��� ����", result, 1);
		});
		
		assertEquals("��ٱ��� ��� ����", exception.getMessage(), "��ٱ��� ���� - ��� ����");
		assertEquals("��ٱ��� ��� ����", exception.getMessage(), "��ٱ��� ���� - �Ǹ� �ߴ� ��ǰ");
		
	}
	
	@DisplayName("���� - ��ٱ��� -> �ֹ�")
	@Test
	void insert2() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			// 2�� ȸ���� 2��° ��ǰ 2�� ����
			CartDetail detail = CartDetail.builder().
					insertState(2).
					memberKey(2).
					Insert2Build();
			int result = service.register(detail);
			assertEquals("�ֹ� ����", result, 1);
		});
	}
	
	@DisplayName("���� - �ֹ� -> ��ǰ �ֹ�")
	@Test
	void insert3() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			// 2�� ȸ���� 2��° ��ǰ 2�� ����
			CartDetail detail = CartDetail.builder().
					insertState(3).
					productOrderCount(100). // 100 => ��� ����
					key1(1).
					key2(2). // 3 => �Ǹ� �ߴ� ��ǰ
					Insert1Build();
			int result = service.register(detail);
			assertEquals("�ֹ� ����", result, 1);
		});
		
		assertEquals("��ǰ �ֹ� ����", exception.getMessage(), "��ǰ �ֹ� ���� - ��� ����");
		assertEquals("��ǰ �ֹ� ����", exception.getMessage(), "��ǰ �ֹ� ���� - �Ǹ� �ߴ� ��ǰ");
	}
	
	
}
