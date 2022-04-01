package com.careerit.sc.dto;

import java.util.List;

import com.careerit.sc.domain.Product;

public interface ScDTO {
	public List<String> getProduct();
	public List<String> getUser();
	public List<Product> getAllProducts();
	public Product getProductById(int productId);
	public void removeProductById(int productId);
	public void editProduct(int editId, String name, String productType, String description, float price, int inStock);	
	public void addProduct(int editId, String name, String productType, String description, float price, int inStock);

}
