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

@WebServlet("view")
public class ScViewServlet extends HttpServlet {
	private ScServiceImpl service = new ScServiceImpl();

	public ScViewServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();	
		int viewId = (int) session.getAttribute("viewId");
		if (session.getAttribute("name") != null) {
			Product product = service.getProductById(viewId);
			RequestDispatcher rd = req.getRequestDispatcher("/view.jsp");
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
