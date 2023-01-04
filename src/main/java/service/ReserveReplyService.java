package service;

import java.util.List;

import dao.ReserveReplyDao;
import domain.ReserveReplyVO;

public class ReserveReplyService {

private ReserveReplyDao dao;
	
	public ReserveReplyService(ReserveReplyDao dao) {
		this.dao = dao;
	}

	public List<ReserveReplyVO> list(int mno) {
		return dao.replyList(mno);
	}
	
	public ReserveReplyVO findList(int rno) {
		return dao.find(rno);
	}

	public void writer(ReserveReplyVO vo) {
		dao.insert(vo);
	}

	public void remove(int rno) {
		dao.delete(rno);
	}

}
