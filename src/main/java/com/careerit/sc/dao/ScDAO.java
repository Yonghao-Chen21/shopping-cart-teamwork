package com.careerit.sc.dao;

import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.User;

public interface ScDAO {
	
	public List<String> getProduct();
	public List<String> getUser();
	public List<Product> getAllProducts();
	public Product getProductById(int productId);
	public void removeProductById(int productId);
	public void editProduct(int productId, String name, String productType, String description, float price, int inStock);

	
	public void addProduct(int productId, String name, String productType, String description, float price, int inStock);


	public boolean loginValidate(String username, String password);
}
