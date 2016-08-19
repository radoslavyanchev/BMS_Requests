package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.bsm.exception.CoveringException;
import com.bsm.exception.ProductException;

public class CoveringDAO extends AbstractDAO {
	private static final String SELECT_COVERING_BY_NAME = "SELECT * FROM coverings WHERE name = ?;";
	private static final String ADD_COVERING = "INSERT INTO coverings(name, deparment) VALUES(?,?);";

	public void addCovering(Covering covering) throws CoveringException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (covering != null) {
			try {
				ps = getCon().prepareStatement(SELECT_COVERING_BY_NAME);
				ps.setString(1, covering.getName());
				rs = ps.executeQuery();
				if (rs.next()) {
					throw new CoveringException("Covering with this name already exists");
				}
				ps = getCon().prepareStatement(ADD_COVERING);

				ps.setString(1, covering.getName());
				ps.setInt(2, covering.getDepartment().getId());
				ps.executeUpdate();
			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
				throw new CoveringException("Missing Department");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CoveringException("Update failed");
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
