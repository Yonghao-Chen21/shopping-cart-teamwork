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

import com.careerit.sc.domain.Product;
import com.careerit.sc.service.ScServiceImpl;

@WebServlet("listpage*")
public class ScListPageServlet extends HttpServlet {
	private ScServiceImpl service = new ScServiceImpl();

	public ScListPageServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		List<Product> products = service.getAllProducts();
		session.setAttribute("products", products);
		if (session.getAttribute("name") != null) {
			if (uri.endsWith("listpage.remove")) {
				String productId = req.getParameter("productId");
				if (productId != null) {
					products = (List<Product>) session.getAttribute("products");
					Product product = service.getProductById(productId);
					products.remove(product);
					session.setAttribute("products", products);
				}

			}
			RequestDispatcher rd = req.getRequestDispatcher("/listpage.jsp");
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
