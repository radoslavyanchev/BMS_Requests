package com.bms.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton DB connection for all DAO db objects.
 * 
 * @author VOTeam
 *
 */
public class DBConnection {

	private static final String DB_PASSWORD = "";
	private static final String DB_USER = "root";
	private static final String DATABASE = "bms_requests";
	private static final String DB_PORT = "8888";
	private static final String DB_HOST = "127.0.0.1";
	private static final String DB_URL = DB_HOST + ":" + DB_PORT;

	private static DBConnection connectionInstance = null;
	private static Connection connection = null;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
	"jdbc:mysql://" + DB_URL + "/" + DATABASE + "?useUnicode=true&characterEncoding=utf-8", DB_USER,DB_PASSWORD);// +"?characterEncoding=utf8"
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBConnection getInstance() {
		synchronized (DBConnection.class) {
			if (connectionInstance == null) {
				connectionInstance = new DBConnection();
			}
		}

		return connectionInstance;
	}

}
