package com.careerit.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.careerit.sc.domain.Product;
import com.careerit.sc.domain.ProductType;
import com.careerit.sc.util.ConnectionUtil;

public class ScDAOimpl implements ScDAO {
	private static ScDAOimpl scDaoImpl;
	private static final String ALL_PRODUCT = "select products.*,product_types_master.product_type_name from products inner join product_types_master on products.product_type_id = product_types_master.product_type_id ";
	private static final String PRODUCT_BY_ID = ALL_PRODUCT + "where product_id = ?";
	private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM products where product_id=?;";
	private static final String EDIT_PRODUCT = "UPDATE products SET product_name = ?, product_type_id = ?, product_description = ?, price = ?, in_stock = ? where product_id = ?";
	private static final String ADD_PRODUCT = "INSERT INTO products(product_name,product_type_id,product_description,price,in_stock) values(?,?,?,?,?)";
	private static final String LOGIN = "SELECT * FROM users WHERE username = ? and password = ?";
	private static final String ALL_TYPES = "SELECT * FROM product_types_master";
	private ConnectionUtil conUtil = ConnectionUtil.obj;

	private ScDAOimpl() {

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

	public List<Product> getAllProducts() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Product> plist = new ArrayList<>();
		try {
			con = conUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(ALL_PRODUCT);
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				String type = rs.getString("product_type_name");
				int typeId = rs.getInt("product_type_id");
				String description = rs.getString("product_description");
				double price = rs.getDouble("price");
				int inStock = rs.getInt("in_stock");
				Product p = Product.builder().productId(productId).productName(productName)
						.type(new ProductType(typeId, type)).description(description).price(price).inStock(inStock)
						.build();
				plist.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(rs, st, con);
		}
		return plist;
	}

	@Override
	public Product getProductById(int productId) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Product p = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(PRODUCT_BY_ID);
			pst.setInt(1, productId);
			rs = pst.executeQuery();
			while (rs.next()) {
				String productName = rs.getString("product_name");
				String type = rs.getString("product_type_name");
				int typeId = rs.getInt("product_type_id");
				String description = rs.getString("product_description");
				double price = rs.getDouble("price");
				int inStock = rs.getInt("in_stock");
				p = Product.builder().productId(productId).productName(productName).type(new ProductType(typeId, type))
						.description(description).price(price).inStock(inStock).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(rs, pst, con);
		}
		return p;
	}

	@Override
	public void removeProductById(int productId) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(DELETE_PRODUCT_BY_ID);
			pst.setInt(1, productId);
			pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(pst, con);
		}

	}

	@Override
	public void editProduct(int productId, String productName, int typeId, String description, double price,
			int inStock) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(EDIT_PRODUCT);
			pst.setString(1, productName);
			pst.setInt(2, typeId);
			pst.setString(3, description);
			pst.setDouble(4, price);
			pst.setInt(5, inStock);
			pst.setInt(6, productId);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(pst, con);
		}

	}

	@Override
	public void addProduct(String productName, int typeId, String description, double price, int inStock) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(ADD_PRODUCT);
			pst.setString(1, productName);
			pst.setInt(2, typeId);
			pst.setString(3, description);
			pst.setDouble(4, price);
			pst.setInt(5, inStock);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(pst, con);
		}

	}

	@Override
	public boolean loginValidate(String username, String password) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = conUtil.getConnection();
			pst = con.prepareStatement(LOGIN);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			return rs.next();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ProductType> getAllTypes() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<ProductType> tlist = new ArrayList<>();
		try {
			con = conUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(ALL_TYPES);
			while (rs.next()) {
				int typeId = rs.getInt("product_type_id");
				String type = rs.getString("product_type_name");
				tlist.add(new ProductType(typeId, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conUtil.close(rs, st, con);
		}
		return tlist;
	}

}
