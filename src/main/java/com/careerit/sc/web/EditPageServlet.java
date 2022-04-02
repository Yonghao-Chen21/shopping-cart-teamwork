package com.careerit.sc.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careerit.sc.domain.Product;
import com.careerit.sc.service.ScServiceImpl;

@WebServlet("*.editpage")
public class EditPageServlet extends HttpServlet {

	private ScServiceImpl service = new ScServiceImpl();

	public EditPageServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int editId = Integer.parseInt(req.getParameter("editId"));
		RequestDispatcher rd = null;
		if (session.getAttribute("username") != null) {
			Product product = service.getProductById(editId);
			session.setAttribute("product", product);
			rd = req.getRequestDispatcher("/editproduct.jsp");
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
