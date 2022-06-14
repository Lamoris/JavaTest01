package com.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.JdbcEmpService;

@WebServlet("/JdbcEmp")
public class JdbcEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		JdbcEmpService svc = new JdbcEmpService(request, response);
		String view = svc.process();
		if(view != null) {
			request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
