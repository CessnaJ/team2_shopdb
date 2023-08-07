package app.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartDetail {
	private int updateState;
	private int insertState;
	private int productOrderCount;
	private int price;
	private int stock;
	private int productPoint;
	private int orderState;
	private int cartState;
	private long cartId;
	private long salesCount;
	private long memberKey;
	private long productKey;
	private long orderKey;
	private double discountRate;
	private String productImgurl;
	private String name;
	private String content;
	private String authorName;
	private String publisherName;
	private String categoryName;
	private String streetAddr;
	private String orderPhone;
	private Date regDate;
	
	@Builder(buildMethodName = "Insert1Build")
	public CartDetail(int insertState, int productOrderCount, long key1, long key2) {
		this.insertState = insertState;
		if (insertState == 1) {
			this.productOrderCount = productOrderCount;
			this.memberKey = key1;
			this.productKey = key2;
		} else if (insertState == 3) {
			this.productOrderCount = productOrderCount;
			this.orderKey = key1;
			this.productKey = key2;
		}
	}
	@Builder(buildMethodName = "Insert2Build")
	public CartDetail(int insertState, long memberKey) {
		this.insertState = insertState;
		this.memberKey = memberKey;
	}
	@Builder(buildMethodName = "DeleteBuild")
	public CartDetail(long orderKey, int orderState) {
		this.orderKey = orderKey;
		this.orderState = orderState;
	}
	@Builder(buildMethodName = "UpdateBuild")
	public CartDetail(int updateState, int intInput, long longInput) {
		this.updateState = updateState;
		
		if (updateState == 1) {
			this.productOrderCount = intInput;
			this.cartId = longInput;
		} else if (updateState == 2) {
			this.cartState = intInput;
			this.cartId = longInput;
		} else if (updateState == 3) {
			this.productOrderCount = intInput;
			this.productKey = longInput;
		}
		
	}
	@Builder(buildMethodName = "selectBuild")
	public CartDetail(long cartId, int productOrderCount, int cartState, Date regDate, String productImgurl, String name, int price, String content, int stock, int productPoint, double discountRate, String authorName, String publisherName, String categoryName) {
		this.cartId = cartId;
		this.productOrderCount = productOrderCount;
		this.cartState = cartState;
		this.regDate = regDate;
		this.productImgurl = productImgurl;
		this.name = name;
		this.price = price;
		this.content = content;
		this.stock = stock;
		this.productPoint = productPoint;
		this.discountRate = discountRate;
		this.authorName = authorName;
		this.publisherName = publisherName;
		this.categoryName = categoryName;
	}
}
