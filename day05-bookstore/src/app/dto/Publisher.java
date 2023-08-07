package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Publisher {
	private int publisherKey;
	private String publisherName;
}
