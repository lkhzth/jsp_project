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
	private String title;
	private String content;
	private String phone;
	private String writer;
	private Date writeDate;
	private int reserveReplyCount;
	
}
