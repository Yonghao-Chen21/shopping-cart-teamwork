package com.careerit.sc.dao;

import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.User;

public interface ScDAO {
	public List<String> getProduct();
	public List<String> getUser();
}
