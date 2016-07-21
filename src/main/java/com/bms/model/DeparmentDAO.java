package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bsm.exception.DeparmentException;

public class DeparmentDAO extends AbstractDAO {

	private static final String SELECT_ALL_DEPARMENTS = "SELECT * FROM deparments;";

	public List<Department> selectAllDeparments() throws DeparmentException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getCon().prepareStatement(SELECT_ALL_DEPARMENTS);
			List<Department> deparments = new ArrayList<Department>();
			rs = ps.executeQuery();
			while (rs.next()) {
				int deparmentId = rs.getInt(1);
				String deparmentName = rs.getString(2);
				Department deparment = new Department(deparmentId, deparmentName);
				deparments.add(deparment);
			}
			return deparments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DeparmentException("Select all deparments error.");
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
