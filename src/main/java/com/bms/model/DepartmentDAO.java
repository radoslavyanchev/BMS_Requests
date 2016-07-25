package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bsm.exception.DeparmentException;

public class DepartmentDAO extends AbstractDAO {

	private static final String SELECT_ALL_DEPARMENTS = "SELECT * FROM departments;";

	public List<Department> selectAllDeparments() throws DeparmentException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			System.out.println("1111111111111111111");
			ps = getCon().prepareStatement(SELECT_ALL_DEPARMENTS);
			System.out.println("2222222222222");
			List<Department> departments = new ArrayList<Department>();
			rs = ps.executeQuery();
			while (rs.next()) {
				int departmentId = rs.getInt(1);
				String departmentName = rs.getString(2);
				Department deparment = new Department(departmentId, departmentName);
				departments.add(deparment);
			}
			return departments;
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
