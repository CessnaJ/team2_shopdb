package app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
	private int reviewKey;
	private int rating;
	private String comment;
	private String reviewRegDate; 
	private String reviewUpdateDate;
	private boolean reviewIsDeleted;
	
	private long memberKey;
	private long productKey;
}
