package service;

import java.util.List;

import dao.ReplyDao;
import domain.ReplyVO;

public class ReplyService {

	private ReplyDao dao;
	
	public ReplyService(ReplyDao dao) {
		this.dao = dao;
	}

	public List<ReplyVO> list(int bno) {
		return dao.replyList(bno);
	}
	
//	public ReplyVO findList(int rno) {
//		return dao.find(rno);
//	}

	public void writer(ReplyVO vo) {
		dao.insert(vo);
	}

	public void remove(int rno) {
		dao.delete(rno);
	}

}
