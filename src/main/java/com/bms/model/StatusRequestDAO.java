package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bsm.exception.StatusRequestException;

public class StatusRequestDAO extends AbstractDAO {
	private static final String INSERT_INTO_STATUS = "INSERT INTO status (name) VALUES(?);";

	public void addStatusRequest(StatusRequest statusRequest) throws StatusRequestException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getCon().prepareStatement(INSERT_INTO_STATUS);
			ps.setString(1, statusRequest.getName());
			ps.executeUpdate();
			if (rs.next()) {
				throw new StatusRequestException("StatusRequest with this name already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StatusRequestException("Invalid name for statusRequest");
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}