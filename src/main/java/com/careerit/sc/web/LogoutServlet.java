package com.careerit.sc.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careerit.sc.domain.Product;
import com.careerit.sc.service.ScServiceImpl;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private ScServiceImpl service = new ScServiceImpl();

	public LogoutServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}