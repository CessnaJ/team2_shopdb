package cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.cart.CartServiceImpl;
import app.dto.CartDetail;
import app.dto.CartUpdate;
import app.frame.ServiceFrame;

public class CartSelectTest {
	Logger log = Logger.getLogger("Cart Select Test");
	ServiceFrame<CartUpdate, CartDetail> service;
	
	@BeforeEach
	void before() throws Exception {
		service = new CartServiceImpl();
	}
	
	@DisplayName("��ٱ��� ���� ��ȸ")
	@Test
	void select1() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartUpdate cartId = null;
			CartDetail detail = null;
			cartId = CartUpdate.builder().key(1).build();
			
			detail = service.get(cartId);
			log.info("��ٱ��� ���� ��ȸ ���: " + detail.toString());
		});
		
		assertEquals("��ٱ��� ��ȸ ����", exception.getMessage(), "��ٱ��� ���� ��ȸ ���� - ��ȸ ��� ����");
	}
	
	@DisplayName("��ٱ��� ��ü ��ȸ")
	@Test
	void selectAll() {
		Exception exception = assertThrows(Exception.class, () -> {
			List<CartDetail> detail = null;
			log.info("��ٱ��� ��ü ��ȸ ���");
			
			detail = service.get();
			for (int i = 0; i < detail.size(); i++) {
				log.info(detail.get(i) + "\n");
			}
		});
		
		assertEquals("��ٱ��� ��ü ��ȸ", exception.getMessage(), "��ٱ��� ��ü ��ȸ - ��ȸ ��� ����");

		
	}
}
