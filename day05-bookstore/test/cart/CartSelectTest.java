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
			log.info("장바구니 고유 아이디: " + detail.getCartId() + " " + "주문 개수: " + detail.getProductOrderCount() + " " + "장바구니 상태: " + detail.getCartState() + " " + "등록일: " + detail.getRegDate() + " 상품명: " + detail.getName() + " 상품 가격: " + detail.getPrice() + " 상품 상세: " + detail.getContent() + " 상품 재고: " + detail.getStock() + " 상품 적립 포인트: " + detail.getProductPoint() + " 상품 할인율: " + detail.getDiscountRate() + " 작가명: " + detail.getAuthorName() + " 출판사명: " + detail.getPublisherName() + " 카테고리명: " + detail.getCategoryName());
		});
		
		assertEquals("장바구니 조회 에러", exception.getMessage(), "장바구니 개별 조회 에러 - 조회 결과 없음");
	}
	
	@DisplayName("장바구니 전체 조회")
	@Test
	void selectAll() {
		Exception exception = assertThrows(Exception.class, () -> {
			CartUpdate cartId = null;
			List<CartDetail> detail = null;
			
			cartId = CartUpdate.builder().key(1).build();
			
			detail = service.search(cartId);
			log.info("장바구니 전체 조회 결과");
			for (int i = 0; i < detail.size(); i++) {
				log.info("장바구니 고유 아이디: " + detail.get(i).getCartId() + " " + "주문 개수: " + detail.get(i).getProductOrderCount() + " " + "장바구니 상태: " + detail.get(i).getCartState() + " " + "등록일: " + detail.get(i).getRegDate() + " 상품명: " + detail.get(i).getName() + " 상품 가격: " + detail.get(i).getPrice() + " 상품 상세: " + detail.get(i).getContent() + " 상품 재고: " + detail.get(i).getStock() + " 상품 적립 포인트: " + detail.get(i).getProductPoint() + " 상품 할인율: " + detail.get(i).getDiscountRate() + " 작가명: " + detail.get(i).getAuthorName() + " 출판사명: " + detail.get(i).getPublisherName() + " 카테고리명: " + detail.get(i).getCategoryName());
			}
		});
		
		assertEquals("장바구니 전체 조회", exception.getMessage(), "장바구니 전체 조회 - 조회 결과 없음");

		
	}
}
