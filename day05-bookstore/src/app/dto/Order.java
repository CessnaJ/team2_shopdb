package app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
	private long orderKey;
	private int orderState;
	private LocalDateTime orderDate;
	private int orderTotalPoint;
	private int orderTotalPrice;
	private String streetAddr;
	private String orderPhone;
	
	private long memberKey;
}
