package com.careerit.sc.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careerit.sc.service.ScServiceImpl;

@WebServlet("log*")
public class ScLoginServlet extends HttpServlet {

	private ScServiceImpl service = new ScServiceImpl();

	public ScLoginServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		if (uri.endsWith("login")) {
			String name = req.getParameter("name");
			String pw = req.getParameter("password");
			boolean flag = false;
			if (name == null && pw == null) {
				flag = service.login(name, pw);
			}
			if (flag) {
				session.setAttribute("name", name);
			}

		} else if (uri.endsWith("logout")) {
			if (session.getAttribute("name") != null) {
				session.invalidate();
				resp.sendRedirect("login");
				return;
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
