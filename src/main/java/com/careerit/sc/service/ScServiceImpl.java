package com.careerit.sc.service;

import java.util.List;

import com.careerit.sc.dao.ScDAO;
import com.careerit.sc.dao.ScDAOimpl;
import com.careerit.sc.domain.Product;

public class ScServiceImpl implements ScService{
private ScDAO obj = ScDAOimpl.getInstance();
	
	@Override
	public List<String> getProduct() {
		List<String> list = obj.getProduct();
		System.out.println("Total products are: " + list.size());
		return list;
	}

	@Override
	public List<String> getUser() {
		List<String> list = obj.getUser();
		System.out.println("Total users are: " + list.size());
		return list;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = obj.getAllProducts();
		System.out.println("Total products are: " + list.size());
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		Product p = obj.getProductById(productId);
		
		return p;
	}

	@Override
	public void removeProductById(int productId) {
		System.out.println("Size before removing product: " + obj.getAllProducts().size());
		obj.removeProductById(productId);
		System.out.println("List size after removing product " + obj.getAllProducts().size());
	}

	@Override
	public void editProduct(int productId, String name, String productType, String description, float price, int inStock) {
		obj.editProduct(productId, name, productType, description, price, inStock);
		System.out.println(obj.getProductById(productId));
	}

	@Override
	public void addProduct(int productId, String name, String productType, String description, float price, int inStock) {
		System.out.println("Size before adding product: " + obj.getAllProducts().size());
		obj.addProduct(productId, name, productType, description, price, inStock);
		System.out.println("List size after adding product " + obj.getAllProducts().size());
		
	}

}
