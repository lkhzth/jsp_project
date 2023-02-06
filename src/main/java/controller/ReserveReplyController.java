package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.ReserveReplyVO;
import com.google.gson.Gson;

import domain.ReserveReplyVO;
import service.ReserveReplyService;

@WebServlet("/reserveReply/*")
public class ReserveReplyController extends HttpServlet {
	
	private ReserveReplyService service;
	private Gson gson;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		service = (ReserveReplyService) sc.getAttribute("reserveReplyService");
		gson = new Gson();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String contextPath = request.getContextPath();
		String pathInfo = request.getPathInfo();
		
		if(pathInfo.equals("/list")) {
			String paramMno = request.getParameter("mno");
			int mno = Integer.parseInt(paramMno);
			List<ReserveReplyVO> replyList = service.list(mno);
			out.print(gson.toJson(replyList));
		
		} else if(pathInfo.equals("/write")) {
			String paramMno = request.getParameter("mno");
			
			long currentTime = System.currentTimeMillis();
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓴 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currentTime-lastWriting<10000) {
					out.print(gson.toJson("도배하지마세요"));
					return;
				}
			}
			// 마지막에 글을 쓴 시간이 없다면
			session.setAttribute("lastWriting", currentTime);
			
			ReserveReplyVO vo = ReserveReplyVO.builder()
					.mno(Integer.parseInt(paramMno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer")).build();
			
			service.writer(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		} else if(pathInfo.equals("/remove")){
			String paramMno = request.getParameter("mno");
			int mno = Integer.parseInt(paramMno);
			String paramRno = request.getParameter("rno");
			int rno = Integer.parseInt(paramRno);
			service.remove(mno, rno);
			String result = gson.toJson("댓글 삭제 성공");
			out.print(result);
		
		} else if(pathInfo.equals("/detail")) {
			String paramRno = request.getParameter("rno");
			int rno = Integer.parseInt(paramRno);
			ReserveReplyVO detail = service.findList(rno);
			out.print(gson.toJson(detail));
		}
		
		else {
			System.out.println("잘못함");
			return;
		}
	}

}
