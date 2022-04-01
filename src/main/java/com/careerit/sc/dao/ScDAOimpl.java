package com.careerit.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.util.ConnectionUtil;

public class ScDAOimpl implements ScDAO {
	private static ScDAOimpl scDaoImpl;
	private static final String PRODUCT = "select Product_ID from product;";
	private static final String USER = "select username from user;";

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
			while (resultSet.next()) {
				products.add(resultSet.getString("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(resultSet, statement, con);
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
			while (resultSet.next()) {
				users.add(resultSet.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(resultSet, statement, con);
		}
		return users;

	}

	public List<Product> getAllProducts() {
		// add the rest part of sql query
		String sql = "SELECT * from product;";

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Product> plist = new ArrayList<>();
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int product_id = rs.getInt("Product_ID");
				String product_name = rs.getString("Product_Name");
				String type = rs.getString("Type");
				String description = rs.getString("Description");
				float price = rs.getFloat("Price");
				int inStock = rs.getInt("In_stock");
				Product p = Product.builder().Product_ID(product_id).Product_Name(product_name).Type(type)
						.Description(description).Price(price).In_stock(inStock).build();
				plist.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(rs, pst, con);
		}
		return plist;
	}



	@Override
	public Product getProductById(int productId) {
		String sql = "SELECT Product_ID, Product_Name, Type, Description, Price, In_stock from product WHERE Product_ID = ?;";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Product p = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, productId);
			rs = pst.executeQuery();
			int product_id = rs.getInt("Product_ID");
			String product_name = rs.getString("Product_Name");
			String type = rs.getString("Type");
			String description = rs.getString("Description");
			float price = rs.getFloat("Price");
			int inStock = rs.getInt("In_stock");
			p = Product.builder().Product_ID(product_id).Product_Name(product_name).Type(type).Description(description)
					.Price(price).In_stock(inStock).build();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(rs, pst, con);
		}
		return p;
	}

	@Override
	public void removeProductById(int productId) {
		String sql = "DELETE FROM product where Product_ID=?;";
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, productId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(pst, con);
		}

	}

	@Override
	public void editProduct(int productId, String productName, String productType, String description, float price, int inStock) {
		String sql = "UPDATE product SET Product_ID = ?, Product_Name = ?, Type = ?, Description = ?, Price = ?, In_stock = ?;";
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, productId);
			pst.setString(2, productName);
			pst.setString(3, productType);
			pst.setString(4, description);
			pst.setFloat(5, price);
			pst.setInt(6, inStock);
			pst.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(pst, con);
		}

	}

	@Override
	public void addProduct(int productid, String name, String productType, String description, float price, int inStock) {
		String sql = "INSERT INTO product(Product_ID,Product_Name,Type,Description,Price,In_stock) values(?,?,?,?,?,?);";
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, productid);
			pst.setString(2, name);
			pst.setString(3, productType);
			pst.setString(4, description);
			pst.setFloat(5, price);
			pst.setInt(6, inStock);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(pst, con);
		}

	}

	public static ScDAOimpl getInstance() {

		if (scDaoImpl == null) {
			synchronized (ScDAOimpl.class) {
				if (scDaoImpl == null) {
					scDaoImpl = new ScDAOimpl();
				}
			}
		}
		return scDaoImpl;
	}

	@Override
	public boolean loginValidate(String username, String password) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE email = ? and password = ?";
		 try{
			 	pst = con.prepareStatement(sql);
		       
		        pst.setString(1, username);
		        pst.setString(2, password);
		        rs = pst.executeQuery();
		        return rs.next(); 
		    } catch(SQLException ex){
		        ex.printStackTrace(); 
		    }
		    return false;
	}

}
