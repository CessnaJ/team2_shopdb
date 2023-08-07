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

public class CartDeleteTest {
	Logger log = Logger.getLogger("Cart Delete Test");
	ServiceFrame<CartUpdate, CartDetail> service;
	
	@BeforeEach
	void before() throws Exception {
		service = new CartServiceImpl();
	}
	
	@DisplayName("장바구니에 삭제")
	@Test
	void delete() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartUpdate detail = CartUpdate.builder().
					state(2).
					key(6).
					build();
			int result = service.remove(detail);
			assertEquals("장바구니 삭제 에러", result, 1);
		});
			
	}
}
