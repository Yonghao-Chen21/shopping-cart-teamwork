package com.careerit.sc.service;

import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.ProductType;

public interface ScService {
	public List<Product> getAllProducts();

	public Product getProductById(int productId);

	public void removeProductById(int productId);

	public void editProduct(int productId, String productName, String type, String description, double price,
			int inStock);

	public void addProduct(String productName, String type, String description, double price, int inStock);

	public boolean loginValidate(String username, String password);

	public List<ProductType> getAllTypes();

}
