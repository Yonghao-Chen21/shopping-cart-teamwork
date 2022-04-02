package com.careerit.sc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careerit.sc.dao.ScDAO;
import com.careerit.sc.dao.ScDAOimpl;
import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.ProductType;

public class ScServiceImpl implements ScService {
	private ScDAO obj = ScDAOimpl.getInstance();

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = obj.getAllProducts();
		System.out.println("Total products are: " + list.size());
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		return obj.getProductById(productId);
	}

	@Override
	public void removeProductById(int productId) {
		System.out.println("Size before removing product: " + obj.getAllProducts().size());
		obj.removeProductById(productId);
		System.out.println("List size after removing product " + obj.getAllProducts().size());
	}

	@Override
	public void editProduct(int productId, String productName, String type, String description, double price,
			int inStock) {
		List<ProductType> tlist = getAllTypes();
		Map<String,Integer> map = new HashMap<>();
		tlist.stream().forEach(t->map.put(t.getName(), t.getId()));
		obj.editProduct(productId, productName, map.get(type), description, price, inStock);
		System.out.println(obj.getProductById(productId));
	}

	@Override
	public void addProduct(String productName, String type, String description, double price, int inStock) {
		List<ProductType> tlist = getAllTypes();
		Map<String,Integer> map = new HashMap<>();
		tlist.stream().forEach(t->map.put(t.getName(), t.getId()));
		System.out.println("Size before adding product: " + obj.getAllProducts().size());
		obj.addProduct(productName, map.get(type), description, price, inStock);
		System.out.println("List size after adding product " + obj.getAllProducts().size());

	}

	@Override
	public boolean loginValidate(String username, String password) {
		boolean flag = obj.loginValidate(username, password);
		System.out.println(flag);
		return flag;
	}

	@Override
	public List<ProductType> getAllTypes() {
		return obj.getAllTypes();
	}
	
	public static void main(String[] args) {
		ScServiceImpl obj = new ScServiceImpl();
		obj.editProduct(4, "LG OLED C1 Series 65 TV", "books", "Alexa Built-in 4k Smart TV", 1799.99, 10);
	}

}
