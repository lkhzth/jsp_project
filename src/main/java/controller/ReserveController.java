package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReserveDAO;
import domain.AuthVO;
import domain.BoardVO;
import domain.MemberVO;
import domain.ReserveVO;
import domain.MemberVO.MemberGrade;
import service.ReserveService;

@WebServlet("/reserve/*")
public class ReserveController extends HttpServlet {
	
	private ReserveService service;

	@Override
	public void init() throws ServletException {
		ReserveDAO dao = new ReserveDAO();
		service = new ReserveService(dao);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String pathInfo = request.getPathInfo();
		final String PREFIX = "/WEB-INF/views/reserve/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage = null;
	
		if(pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/list")) {
			List<ReserveVO> reserveList = service.listReserve();
			request.setAttribute("list", reserveList);
			nextPage = "list";
		
		} else if(pathInfo.equals("/phChecked")){
			String paramMno = request.getParameter("mno");
			int mno = Integer.parseInt(paramMno);
			ReserveVO findBoard = service.findReserve(mno);
			request.setAttribute("board", findBoard);
			nextPage = "phoneCheck";

		} else if(pathInfo.equals("/checking")){
			String phone = request.getParameter("phone");
			System.out.println(phone);
			nextPage = "detail";
			
		} else if(pathInfo.equals("/detail")) {
			String paramMno = request.getParameter("mno");
			int mno = Integer.parseInt(paramMno);
			ReserveVO findBoard = service.findReserve(mno);
			request.setAttribute("board", findBoard);
			nextPage = "detail";
			
		}
		
		else if(pathInfo.equals("/reservationForm")) {
			nextPage = "reservationForm";
	
		} else if(pathInfo.equals("/write")) {
			ReserveVO vo = ReserveVO.builder()
					.title(request.getParameter("title"))
					.content(request.getParameter("content"))
					.phone(request.getParameter("phone"))
					.writer(request.getParameter("writer"))
					.build();
			service.addReserve(vo);
			response.sendRedirect(contextPath + "/reserve");
			return;
		
		}
		
		else {
			System.out.println("페이지없음");
			return;
		}
	
		rd = request.getRequestDispatcher(PREFIX + nextPage + SUFFIX);
		rd.forward(request, response);
	}
		
	
}
