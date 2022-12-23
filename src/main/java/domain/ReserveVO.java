package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveVO {
	private int mno;
	private String name;
	private String phone;
	private String content;
	private Date writedate;
	private MemberGrade grade;
	
	public enum MemberGrade{
		ROLE_ADMIN, ROLE_MEMBER
	}
	
	
}
