package com.careerit.sc.domain;

import java.util.*;
import lombok.Data;
import lombok.Builder;

@Data
@Builder


public class Product {
	
	private int productID;
	private String productName;
	private String Type;
	private String Description;
	private float Price;
	private int inStock;

}

