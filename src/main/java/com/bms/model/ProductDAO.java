package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.bsm.exception.ProductException;

public class ProductDAO extends AbstractDAO {

	private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM products WHERE name = ?;";
	private static final String ADD_PRODUCT = "INSERT INTO products(name, deparment) VALUES(?,?);";

	public void addProduct(Product product) throws ProductException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (product != null) {
			try {
				ps = getCon().prepareStatement(SELECT_PRODUCT_BY_NAME);
				ps.setString(1, product.getName());
				rs = ps.executeQuery();
				if (rs.next()) {
					throw new ProductException("Product with this name already exists");
				}
				ps = getCon().prepareStatement(ADD_PRODUCT);

				ps.setString(1, product.getName());
				ps.setInt(2, product.getDepartment().getId());
				ps.executeUpdate();
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				throw new ProductException("Missing Department");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ProductException("Update failed");
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
