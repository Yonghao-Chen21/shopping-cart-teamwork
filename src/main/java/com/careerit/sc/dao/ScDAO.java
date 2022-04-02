package com.careerit.sc.dao;

import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.ProductType;

public interface ScDAO {

	public List<Product> getAllProducts();

	public Product getProductById(int productId);

	public void removeProductById(int productId);

	public void editProduct(int productId, String productName, int typeId, String description, double price,
			int inStock);

	public void addProduct(String productName, int typeId, String description, double price, int inStock);

	public boolean loginValidate(String username, String password);
	
	public List<ProductType> getAllTypes();
}
