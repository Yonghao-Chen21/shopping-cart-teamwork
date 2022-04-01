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
import com.careerit.sc.domain.ProductType;
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
				String removeId = req.getParameter("removeId");
				if (removeId != null) {
					service.removeProductById(Integer.parseInt(removeId));
					products = service.getAllProducts();
					session.setAttribute("products", products);
					session.setAttribute("removeId", null);
				}
			} else if (uri.endsWith("listpage.edit")) {
				int editId = (int) session.getAttribute("editId");
					Product product = service.getProductById(editId);
					session.setAttribute("editProduct", product);
					resp.sendRedirect("edit");

			} else if (uri.endsWith("listpage.bulkupdate")) {

			} else if (uri.endsWith("listpage.exportlist")) {

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
