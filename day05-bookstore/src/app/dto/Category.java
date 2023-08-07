package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
	private String categoryKey;
	private String categoryName;
	private int level;
	private int categoryState;
	private boolean isLeaf;
}
