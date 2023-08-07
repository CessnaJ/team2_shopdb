package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
	private int categoryKey;
	private String categoryName;
	private int lavel;
	private int state;
	private boolean isLeaf;
}
