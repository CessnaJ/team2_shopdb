package app.frame;

import java.sql.PreparedStatement;

public class CartSQL {
	// 장바구니 담기
	public static String cartAdd =  
			"INSERT INTO `CART` (product_order_count, member_key, product_key) values(?, ?, ?)";
	// 장바구니 담기 유효성 검사
	// 재고보다 많은 수를 장바구니에 넣은 경우
	public static String outOfStock = 
			"SELECT stock FROM `PRODUCT` WHERE product_key = ?";	
	// 장바구니에 담긴 상품 정보 조회
	public static String outOfStock2 = 
			"SELECT stock FROM `PRODUCT` WHERE product_key = (SELECT product_key FROM `CART` WHERE cart_id = ?)";
	// 장바구니에 담을 상품을 판매하고 있지 않은 경우
	public static String invalidProduct = 
			"SELECT `status` FROM `PRODUCT` WHERE product_key = ?";
	public static String invalidProduct2 = 
			"SELECT `status` FROM `PRODUCT` WHERE product_key = (SELECT product_key FROM `CART` WHERE cart_id = ?)";
	// 장바구니 개별 조회
	public static String cartSearch = 
			"SELECT c.cart_id, c.product_order_count, c.cart_state, c.reg_date, p.product_imgurl, p.name, p.price, p.content, p.stock, p.product_point, p.discount_rate, a.author_name, pub.publisher_name, cat.category_name FROM `CART` c, `PRODUCT` p, `CATEGORY` cat, `AUTHOR` a, `PUBLISHER` pub WHERE c.cart_id= ? AND c.product_key = p.product_key AND cat.category_key = p.category_key AND a.author_key = p.author_key AND pub.publisher_key = p.publisher_key;";
	// 장바구니 전체 조회
	public static String cartSearchAll = 
			"SELECT c.cart_id, c.product_order_count, c.cart_state, c.reg_date, p.product_imgurl, p.name, p.price, p.content, p.stock, p.product_point, p.discount_rate, a.author_name, pub.publisher_name, cat.category_name FROM `CART` c, `PRODUCT` p, `CATEGORY` cat, `AUTHOR` a, `PUBLISHER` pub WHERE c.member_key = ? AND c.product_key = p.product_key AND cat.category_key = p.category_key AND a.author_key = p.author_key AND pub.publisher_key = p.publisher_key";
	// 장바구니 상품 수량 변경
	public static String cartChangeCount = 
			"UPDATE `CART` SET product_order_count = ? WHERE cart_id = ?";
	
	// 구매
	// 장바구니 상태 변경
	public static String cartBuyStep1 = 
			"UPDATE `CART` SET cart_state = ? WHERE cart_id = ?";
	// 장바구니 상품 상태 변경
	public static String cartBuyStep2 = 
			"UPDATE `PRODUCT` SET sales_count = sales_count + ?, stock = stock - ? WHERE product_key = ?";
	// 장바구니 -> 주문
	public static String cartBuyStep3 = 
			"INSERT INTO `ORDER` (street_addr, order_phone, member_key)	SELECT `MEMBER`.address, `MEMBER`.member_phone, `MEMBER`.member_key	FROM `MEMBER` WHERE `MEMBER`.member_key = ?";
	// 주문 -> 상품 주문
	public static String cartBuyStep4 = 
			"INSERT INTO `PRODUCTORDER` (product_order_count, product_price, product_point, order_key, product_key) SELECT ?, p.price, p.product_point, o.order_key, p.product_key FROM `ORDER` o, `PRODUCT` p WHERE o.order_key = ? AND p.product_key = ?";

	
	// 삭제
	// 장바구니 상품 취소
	public static String cartCancel = 
			"UPDATE `ORDER` SET order_state = ? 	WHERE order_key = ?";
	
}
