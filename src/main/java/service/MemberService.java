package service;

import dao.MemberDAO;
import domain.MemberVO;
import domain.MemberVO.MemberGrade;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService {
	
	private MemberDAO dao;
	
	// 회원가입
	public void memberJoin(MemberVO vo) {
		dao.insertMember(vo);
	}
	
	// 로그인체크
	public boolean loginService(MemberVO vo) {
		return dao.loingCheck(vo);
	}

	// 회원등급
	public MemberGrade getMemberGrade(String id) {
		return dao.findMemberGradeById(id);
	}
}
