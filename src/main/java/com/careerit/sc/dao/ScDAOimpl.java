package com.careerit.sc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.User;
import com.careerit.sc.util.ConnectionUtil;

public class ScDAOimpl implements ScDAO{
	private static ScDAOimpl scDaoImpl;
	private static final String PRODUCT = "select product_id from product";
	private static final String USER = "select username from user";

	private ScDAOimpl() {
		
	}
	
	ConnectionUtil conUtil = ConnectionUtil.obj;
	Connection con = null;
	Statement statement = null;
	ResultSet resultSet = null;
	List<String> products = new ArrayList<String>();
	@Override
	public List<String> getProduct() {
		try {
			Connection con = conUtil.getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(PRODUCT);
			while(resultSet.next()) {
				products.add(resultSet.getString("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conUtil.close(resultSet,statement, con);
		}
		return products;
	}

	@Override
	public List<String> getUser() {
		List<String> users = new ArrayList<String>();
		try {
			Connection con = conUtil.getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(USER);
			while(resultSet.next()) {
				users.add(resultSet.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conUtil.close(resultSet,statement, con);
		}
		return users;
		
	}

}
