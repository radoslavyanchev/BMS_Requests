package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO extends AbstractDAO {

	public ProductDAO() {
		public void createProduct(Product product){
			PreparedStatement ps = null;
			if (product != null) {
				try {
					ps = getCon().prepareStatement("INSERT INTO product(name, deparment) VALUES(null,?,?);");

					ps.setInt(1, new StateDAO().getStateByName(clip.getState()));
					ps.setString(2, clip.getDescription());
					ps.setInt(3, clip.getViews());
					ps.setInt(4, clip.getCategory().getCategoryID());
					ps.setInt(5, clip.getClipID());
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new UserProblemException("Update failed");
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

}
