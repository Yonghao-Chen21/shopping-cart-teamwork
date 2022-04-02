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
import com.careerit.sc.domain.ProductType;
import com.careerit.sc.service.ScServiceImpl;

@WebServlet("*.listpage")
public class ListPageServlet extends HttpServlet {
	private ScServiceImpl service = new ScServiceImpl();

	public ListPageServlet() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		RequestDispatcher rd = null;
		List<Product> plist = null;
		if (uri.endsWith("login.listpage")) {
			String name = req.getParameter("loginID");
			String pw = req.getParameter("Password");
			boolean flag = service.loginValidate(name, pw);
			if (flag) {
				session.setAttribute("username", name);
				plist = service.getAllProducts();
				session.setAttribute("plist", plist);
				List<ProductType> tlist = service.getAllTypes();
				session.setAttribute("tlist", tlist);
				rd = req.getRequestDispatcher("/listpage.jsp");
			} else {
				rd = req.getRequestDispatcher("/index.jsp");
			}

		} else {
			if (session.getAttribute("username") == null) {
				rd = req.getRequestDispatcher("/index.jsp");
			} else {
				if (uri.endsWith("removeitem.listpage")) {
					int removeId = Integer.parseInt(req.getParameter("removeId"));
					service.removeProductById(removeId);
				} else if (uri.endsWith("edit.listpage")) {
					int id = Integer.parseInt(req.getParameter("productID"));
					String name = req.getParameter("productName");
					String type = req.getParameter("productType");
					String desc = req.getParameter("productDescription");
					double price = Double.parseDouble(req.getParameter("productPrice"));
					int inStock = Integer.parseInt(req.getParameter("inStock"));
					service.editProduct(id, name, type, desc, price, inStock);
				} else if (uri.endsWith("add.listpage")) {
					String name = req.getParameter("productName");
					String type = req.getParameter("productType");
					String desc = req.getParameter("productDescription");
					double price = Double.parseDouble(req.getParameter("productPrice"));
					int inStock = Integer.parseInt(req.getParameter("inStock"));
					service.addProduct(name, type, desc, price, inStock);
				}
				plist = service.getAllProducts();
				session.setAttribute("plist", plist);
				rd = req.getRequestDispatcher("/listpage.jsp");

			}
		}
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
