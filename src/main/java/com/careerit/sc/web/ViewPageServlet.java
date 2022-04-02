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

@WebServlet("*.view")
public class ViewPageServlet extends HttpServlet {
	private ScServiceImpl service = new ScServiceImpl();

	public ViewPageServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		int viewId = Integer.parseInt(req.getParameter("viewId"));
		RequestDispatcher rd = null;
		if (session.getAttribute("username") != null) {
			Product product = service.getProductById(viewId);
			session.setAttribute("product", product);
			rd = req.getRequestDispatcher("/view.jsp");
		} else {
			rd = req.getRequestDispatcher("/index.jsp");
		}
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
