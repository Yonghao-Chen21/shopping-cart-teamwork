package com.careerit.sc.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careerit.sc.service.ScServiceImpl;

@WebServlet("add")
public class ScAddServlet extends HttpServlet{

	private ScServiceImpl service = new ScServiceImpl();

	public ScAddServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();	
		String uri = req.getRequestURI();
		int editId = (int) session.getAttribute("editId");
		if (session.getAttribute("name") != null) {
			RequestDispatcher rd = req.getRequestDispatcher("/add.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendRedirect("login");
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
