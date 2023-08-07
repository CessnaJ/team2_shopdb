package app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
	private int reviewKey;
	private int rating;
	private String comment;
	private Date reviewRegDate; 
	private Date reviewUpdateDate;
	private boolean reviewIsDeleted;
	private long memberKey;
	private long productKey;
}
