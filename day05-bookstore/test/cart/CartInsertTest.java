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
	
	@DisplayName("장바구니에 책 담기")
	@Test
	void insert1() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			// 2번 회원이 2번째 상품 2개 구매
			CartDetail detail = CartDetail.builder().
					insertState(1).
					productOrderCount(2). // 100 => 재고 없음
					key1(1).
					key2(3). // 3 => 판매 중단 상품
					Insert1Build();
			int result = service.register(detail);
			assertEquals("장바구니 담기 에러", result, 1);
		});
		
		assertEquals("장바구니 담기 에러", exception.getMessage(), "장바구니 에러 - 재고 없음");
		assertEquals("장바구니 담기 에러", exception.getMessage(), "장바구니 에러 - 판매 중단 상품");
		
	}
	
	@DisplayName("구매 - 장바구니 -> 주문")
	@Test
	void insert2() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			// 2번 회원이 2번째 상품 2개 구매
			CartDetail detail = CartDetail.builder().
					insertState(2).
					memberKey(2).
					Insert2Build();
			int result = service.register(detail);
			assertEquals("주문 에러", result, 1);
		});
	}
	
	@DisplayName("구매 - 주문 -> 상품 주문")
	@Test
	void insert3() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			// 2번 회원이 2번째 상품 2개 구매
			CartDetail detail = CartDetail.builder().
					insertState(3).
					productOrderCount(100). // 100 => 재고 없음
					key1(1).
					key2(2). // 3 => 판매 중단 상품
					Insert1Build();
			int result = service.register(detail);
			assertEquals("주문 에러", result, 1);
		});
		
		assertEquals("상품 주문 에러", exception.getMessage(), "상품 주문 에러 - 재고 없음");
		assertEquals("상품 주문 에러", exception.getMessage(), "상품 주문 에러 - 판매 중단 상품");
	}
	
	
}
