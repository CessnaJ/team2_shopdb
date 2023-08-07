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
			log.info("��ٱ��� ���� ���̵�: " + detail.getCartId() + " " + "�ֹ� ����: " + detail.getProductOrderCount() + " " + "��ٱ��� ����: " + detail.getCartState() + " " + "�����: " + detail.getRegDate() + " ��ǰ��: " + detail.getName() + " ��ǰ ����: " + detail.getPrice() + " ��ǰ ��: " + detail.getContent() + " ��ǰ ���: " + detail.getStock() + " ��ǰ ���� ����Ʈ: " + detail.getProductPoint() + " ��ǰ ������: " + detail.getDiscountRate() + " �۰���: " + detail.getAuthorName() + " ���ǻ��: " + detail.getPublisherName() + " ī�װ���: " + detail.getCategoryName());
		});
		
		assertEquals("��ٱ��� ��ȸ ����", exception.getMessage(), "��ٱ��� ���� ��ȸ ���� - ��ȸ ��� ����");
	}
	
	@DisplayName("��ٱ��� ��ü ��ȸ")
	@Test
	void selectAll() {
		Exception exception = assertThrows(Exception.class, () -> {
			CartUpdate cartId = null;
			List<CartDetail> detail = null;
			
			cartId = CartUpdate.builder().key(1).build();
			
			detail = service.search(cartId);
			log.info("��ٱ��� ��ü ��ȸ ���");
			for (int i = 0; i < detail.size(); i++) {
				log.info("��ٱ��� ���� ���̵�: " + detail.get(i).getCartId() + " " + "�ֹ� ����: " + detail.get(i).getProductOrderCount() + " " + "��ٱ��� ����: " + detail.get(i).getCartState() + " " + "�����: " + detail.get(i).getRegDate() + " ��ǰ��: " + detail.get(i).getName() + " ��ǰ ����: " + detail.get(i).getPrice() + " ��ǰ ��: " + detail.get(i).getContent() + " ��ǰ ���: " + detail.get(i).getStock() + " ��ǰ ���� ����Ʈ: " + detail.get(i).getProductPoint() + " ��ǰ ������: " + detail.get(i).getDiscountRate() + " �۰���: " + detail.get(i).getAuthorName() + " ���ǻ��: " + detail.get(i).getPublisherName() + " ī�װ���: " + detail.get(i).getCategoryName());
			}
		});
		
		assertEquals("��ٱ��� ��ü ��ȸ", exception.getMessage(), "��ٱ��� ��ü ��ȸ - ��ȸ ��� ����");

		
	}
}
