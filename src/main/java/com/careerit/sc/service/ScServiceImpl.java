package com.careerit.sc.service;

import java.util.List;

import com.careerit.sc.dao.ScDAO;
import com.careerit.sc.dao.ScDAOimpl;
import com.careerit.sc.domain.Product;

public class ScServiceImpl implements ScService{
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
	public void editProduct(int productId, String productName, int typeId, String description, float price,
			int inStock) {
		obj.editProduct(productId, productName, typeId, description, price, inStock);
		System.out.println(obj.getProductById(productId));
	}

	@Override
	public void addProduct(String productName, int typeId, String description, float price, int inStock) {
		System.out.println("Size before adding product: " + obj.getAllProducts().size());
		obj.addProduct(productName, typeId, description, price, inStock);
		System.out.println("List size after adding product " + obj.getAllProducts().size());
		
	}

	@Override
	public boolean loginValidate(String username, String password) {
		return obj.loginValidate(username, password);
	}

}
