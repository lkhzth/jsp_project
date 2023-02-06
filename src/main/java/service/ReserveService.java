package service;

import java.util.List;

import dao.BoardDAO;
import dao.ReserveDAO;
import domain.BoardVO;
import domain.ReserveVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReserveService {
	
	private ReserveDAO dao;
	
	public List<ReserveVO> listReserve() {
		return dao.reserveList();
	}
	
	public ReserveVO findReserve(int mno) {
		return dao.reserveAll(mno);
	}
	
	public void addReserve(ReserveVO vo) {
		dao.reserveWrite(vo);
	}

	public boolean checkPhone(String phone) {
		return dao.phoneCheck(phone);
	}

	public void removeBoard(int mno) {
		dao.deleteBoard(mno);
	}
	
}
