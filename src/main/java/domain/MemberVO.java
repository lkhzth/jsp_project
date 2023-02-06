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
public class MemberVO {
	private int mno;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private Date date;
	private MemberGrade grade;
	
	public enum MemberGrade{
		ROLE_ADMIN, ROLE_MEMBER
	}
}
