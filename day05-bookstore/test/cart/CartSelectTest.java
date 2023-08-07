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
	
	@DisplayName("장바구니 개별 조회")
	@Test
	void select1() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartUpdate cartId = null;
			CartDetail detail = null;
			cartId = CartUpdate.builder().key(1).build();
			
			detail = service.get(cartId);
			log.info("장바구니 개별 조회 결과: " + detail.toString());
		});
		
		assertEquals("장바구니 조회 에러", exception.getMessage(), "장바구니 개별 조회 에러 - 조회 결과 없음");
	}
	
	@DisplayName("장바구니 전체 조회")
	@Test
	void selectAll() {
		Exception exception = assertThrows(Exception.class, () -> {
			List<CartDetail> detail = null;
			log.info("장바구니 전체 조회 결과");
			
			detail = service.get();
			for (int i = 0; i < detail.size(); i++) {
				log.info(detail.get(i) + "\n");
			}
		});
		
		assertEquals("장바구니 전체 조회", exception.getMessage(), "장바구니 전체 조회 - 조회 결과 없음");

		
	}
}
