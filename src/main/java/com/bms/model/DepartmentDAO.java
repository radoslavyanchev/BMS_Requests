package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

public class DepartmentDAO extends AbstractDAO {

	private static final String INSERT_INTO_DEPARTMENTS = "INSERT INTO departments (name) VALUES(?);";
	private static final String SELECT_ALL_DEPARTMENTS = "SELECT * FROM departments;";
	private static final String SELECT_DEPARTMENT_BY_ID = "SELECT * FROM departments where id=?;";
	private static final String SELECT_DEPARTMENT_BY_NAME = "SELECT * FROM departments WHERE name = ?;";

	public List<Department> selectAllDeparments() throws DeparmentException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getCon().prepareStatement(SELECT_ALL_DEPARTMENTS);
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

	public Department getDepartmentById(int id) throws DeparmentException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getCon().prepareStatement(SELECT_DEPARTMENT_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int departmentID = rs.getInt(1);
			String departmentName = rs.getString(2);
			Department department = new Department(departmentID, departmentName);
			return department;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DeparmentException("Select deparment by id error");
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

	public void addDepartment(Department department) throws DeparmentException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			System.out.println(department.getName() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Deparment dao1");
			System.out.println(getCon());
			ps = getCon().prepareStatement(SELECT_DEPARTMENT_BY_NAME);
			
			ps.setString(1, department.getName());
			System.out.println(department.getName() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Deparment dao2");
			rs = ps.executeQuery();
			if (rs.next()) {
				throw new DeparmentException("Deparment with this name already exists");
			}
			ps = getCon().prepareStatement(INSERT_INTO_DEPARTMENTS);
			ps.setString(1, department.getName());
	
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ DEP 2");
			e.printStackTrace();
			throw new DeparmentException("Invalid name for deparment");
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
