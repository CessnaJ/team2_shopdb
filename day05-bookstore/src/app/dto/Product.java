package app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private long productKey;
	private String productImgurl;
	private String name;
	private int price;
	private String content;
	private int stock;
	private String productRegDate;
	private String productUpdateDate;
	private double productPoint;
	private double discountRate;
	private long salesCount;
	private int status;
	
	private int authorKey;
	private int publishKey;
	private int categoryKey;
}
