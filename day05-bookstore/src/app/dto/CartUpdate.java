package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartUpdate {
	private long key;
	private int state;
}
