package com.careerit.sc.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
	
	private int productId;
	private String productName;
	private ProductType type;
	private String description;
	private double price;
	private int inStock;

}