package com.bms.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.bsm.exception.CoveringException;
import com.bsm.exception.DeparmentException;
import com.bsm.exception.ProductException;

import scala.deprecated;

public class CoveringDAO extends AbstractDAO {
	private static final String SELECT_COVERING_BY_NAME = "SELECT * FROM coverings WHERE name = ?;";
	private static final String SELECT_ALL_COVERING = "SELECT * FROM coverings";
	private static final String ADD_COVERING = "INSERT INTO coverings(name, department) VALUES(?,?);";

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
		throw new CoveringException("Update failed");
	}

	public List<Covering> selectAllCoverings() throws DeparmentException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getCon().prepareStatement(SELECT_ALL_COVERING);
			List<Covering> coverings = new ArrayList<Covering>();
			rs = ps.executeQuery();
			while (rs.next()) {
				int coveringId = rs.getInt(1);
				String covringName = rs.getString(2);
				int coveringDeparmentId = rs.getInt(3);
				DepartmentDAO departmentDAO = new DepartmentDAO();
				Department coveringDepartment = departmentDAO.getDepartmentById(coveringDeparmentId);
				Covering covering = new Covering(coveringId, covringName, coveringDepartment);
				coverings.add(covering);
			}
			return coverings;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DeparmentException("Select all coverings error.");
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

	public void deleteCovering(int coveringId) throws CoveringException {
		PreparedStatement ps = null;

		if (coveringId > 0) {
			try {
				ps = getCon().prepareStatement("DELETE FROM coverings where id=?");
				ps.setInt(1, coveringId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CoveringException("Delete failed");
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		throw new CoveringException("Delete failed");
	}

	public void editCovering(int coveringId, String coveringNewName) throws CoveringException {
		PreparedStatement ps = null;
		if (coveringId > 0) {
			try {
				ps = getCon().prepareStatement("UPDATE coverins SET name=? WHERE id=?;");
				ps.setString(1, coveringNewName);
				ps.setInt(2, coveringId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CoveringException("Update Field");
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
