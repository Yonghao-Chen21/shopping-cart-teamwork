package com.careerit.sc.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
	
	private int Product_ID;
	private String Product_Name;
	private String Type;
	private String Description;
	private float Price;
	private int In_stock;

}