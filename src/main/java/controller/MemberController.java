package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import domain.AuthVO;
import domain.MemberVO;
import domain.MemberVO.MemberGrade;
import service.MemberService;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private MemberService service;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new MemberService(new MemberDAO());
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
		final String PREFIX = "/WEB-INF/views/member/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage = null;
	
		if(pathInfo.equals("/joinForm")) {	// 회원가입 폼
			nextPage = "joinForm";
		} else if(pathInfo.equals("/join")) { // 회원가입 처리
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			MemberVO vo = MemberVO.builder()
					.id(id)
					.pwd(pwd)
					.name(name)
					.email(email)
					.phone(phone).build();
			service.memberJoin(vo);
			response.sendRedirect(contextPath + "/board"); 
			return;
			
		} else if(pathInfo.equals("/loginForm")) { //로그인 폼
			nextPage = "loginForm";
			
		} else if(pathInfo.equals("/login")) { // 로그인 처리
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder()
					.id(id).pwd(pwd).build();
			
			if(service.loginService(vo)) {
				HttpSession session = request.getSession();
				
				// 회원권한 설정
				MemberGrade grade = service.getMemberGrade(vo.getId()); // 등급조회
				AuthVO authVO = new AuthVO();
				authVO.setId(vo.getId()); // 아이디
				authVO.setGrade(grade); // 등급
				session.setAttribute("auth", authVO); // 세션데이터 바인딩
				
				
				String userUri = (String) session.getAttribute("userUri");
				if(userUri != null) {
					session.removeAttribute("userUri"); // 세션을 비워서 로그인시 기존에 기억된 주소로 가지않게 막아줌
					response.sendRedirect(userUri);
					return;
				}
				response.sendRedirect(contextPath + "/board"); 
				return;
			} else {
				System.out.println("MemberController.login : 아이디 또는 비밀번호 틀림");
				return;
			}
		} else if(pathInfo.equals("/logout")) { // 로그아웃 처리
			HttpSession session = request.getSession(false);
			session.removeAttribute("auth");
			response.sendRedirect(contextPath + "/board"); 
			return;
		}
		
		else {
			System.out.println("페이지 찾을 수 없음");
			return;
		}
		rd = request.getRequestDispatcher(PREFIX + nextPage + SUFFIX);
		rd.forward(request, response);
	}
}
