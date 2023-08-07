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
	
	@DisplayName("장바구니에 책 수량 변경")
	@Test
	void update1() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartDetail detail = CartDetail.builder().
					updateState(1).
					intInput(2). // 100 => 재고 없음
					longInput(4).
					UpdateBuild();
			int result = service.modify(detail);
			assertEquals("장바구니 담기 에러", result, 1);
		});
		
		assertEquals("수량 변경 에러", exception.getMessage(), "장바구니 에러 - 재고 없음");
		assertEquals("수량 변경 에러", exception.getMessage(), "장바구니 에러 - 판매 중단 상품");
		
	}
	@DisplayName("장바구니에 상태 변경")
	@Test
	void update2() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartDetail detail = CartDetail.builder().
					updateState(2).
					intInput(2). // 100 => 재고 없음
					longInput(4).
					UpdateBuild();
			int result = service.modify(detail);
			assertEquals("장바구니 상태 변경 에러", result, 1);
		});		
	}
	@DisplayName("상품 상태 변경")
	@Test
	void update3() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			CartDetail detail = CartDetail.builder().
					updateState(3).
					intInput(2). // 100 => 재고 없음
					longInput(3).
					UpdateBuild();
			int result = service.modify(detail);
			assertEquals("장바구니 담기 에러", result, 1);
		});
		
		assertEquals("상품 상태 변경 에러", exception.getMessage(), "장바구니 에러 - 재고 없음");
		assertEquals("상품 상태 변경 에러", exception.getMessage(), "장바구니 에러 - 판매 중단 상품");
		
	}
}
