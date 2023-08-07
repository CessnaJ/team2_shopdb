package app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cart {
	private long cartId;
	private int productOrderCount;
	private LocalDateTime regDate;
	private long memberKey;
	private long productKey;

}
