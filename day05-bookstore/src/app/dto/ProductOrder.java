package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOrder {
	private long productOrderKey;
	private int productOrderCount;
	private int productPrice;
	private int productPoint;
	
	//	FK
	private int orderKey;
	private long productKey;
}
