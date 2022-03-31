package com.careerit.sc.domain;

import java.util.*;
import lombok.Data;
import lombok.Builder;

@Data
@Builder


public class Product {
	
	private int Product_ID;
	private String Product_Name;
	private String Type;
	private String Description;
	private float Price;
	private int In_stock;

}

