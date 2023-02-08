package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.AuthVO;
import domain.MemberVO.MemberGrade;

@WebFilter(urlPatterns = {
		"/board/writeForm",
		"/board/detail",
		"/board/adminDetail",
})
public class BoardFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse reps = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);
		AuthVO auth = (AuthVO) session.getAttribute("auth");
		if(auth == null) {
			String userUri = req.getRequestURI();
			String queryString = req.getQueryString();
			userUri += "?" + queryString;
			session.setAttribute("userUri", userUri);
			reps.sendRedirect(req.getContextPath() + "/member/loginForm");
			return;
		} else {
			if(auth.getGrade()==MemberGrade.ROLE_ADMIN) {
			}
		}
		chain.doFilter(request, response);
	}
}
