package app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	private long memberKey;
	private String email;
	private String hashed_pwd;
	private String memberName;
	private String memberImgurl;
	private String address;
	private int memberPoint;
	private LocalDateTime memberRegDate; // Ȯ�� �ʿ�.
	private boolean isDormant;
	private boolean isAdmin;
	private String memberPhone;

}
