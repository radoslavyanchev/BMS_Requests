package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bsm.exception.ProductException;

public class ProductDAO extends AbstractDAO {

	public void addProduct(Product product) throws ProductException {
		PreparedStatement ps = null;
		if (product != null) {
			try {
				ps = getCon().prepareStatement("INSERT INTO product(name, deparment) VALUES(null,?,?);");

				ps.setString(1, product.getName());
				ps.setInt(2, product.getDeparment().getId());
				ps.executeUpdate();
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
