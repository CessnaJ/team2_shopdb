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

public class CartUpdateTest {
	Logger log = Logger.getLogger("Cart Update Test");
	ServiceFrame<CartUpdate, CartDetail> service;
	
	@BeforeEach
	void before() throws Exception {
		service = new CartServiceImpl();
	}
	
	@DisplayName("��ٱ��Ͽ� å ���� ����")
	@Test
	void update1() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartDetail detail = CartDetail.builder().
					updateState(1).
					intInput(2). // 100 => ��� ����
					longInput(4).
					UpdateBuild();
			int result = service.modify(detail);
			assertEquals("��ٱ��� ��� ����", result, 1);
		});
		
		assertEquals("���� ���� ����", exception.getMessage(), "��ٱ��� ���� - ��� ����");
		assertEquals("���� ���� ����", exception.getMessage(), "��ٱ��� ���� - �Ǹ� �ߴ� ��ǰ");
		
	}
	@DisplayName("��ٱ��Ͽ� ���� ����")
	@Test
	void update2() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartDetail detail = CartDetail.builder().
					updateState(2).
					intInput(2). // 100 => ��� ����
					longInput(4).
					UpdateBuild();
			int result = service.modify(detail);
			assertEquals("��ٱ��� ���� ���� ����", result, 1);
		});		
	}
	@DisplayName("��ǰ ���� ����")
	@Test
	void update3() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartDetail detail = CartDetail.builder().
					updateState(3).
					intInput(2). // 100 => ��� ����
					longInput(3).
					UpdateBuild();
			int result = service.modify(detail);
			assertEquals("��ٱ��� ��� ����", result, 1);
		});
		
		assertEquals("��ǰ ���� ���� ����", exception.getMessage(), "��ٱ��� ���� - ��� ����");
		assertEquals("��ǰ ���� ���� ����", exception.getMessage(), "��ٱ��� ���� - �Ǹ� �ߴ� ��ǰ");
		
	}
}
