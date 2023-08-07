package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
	private int authorKey;
	private String authorName;
}
