package com.bilgeadam.boost.java.course02.lesson071;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseListenTest {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/Trigger%20Example";
			Connection conn = DriverManager.getConnection(url, "postgres", "root");
			DatabaseListener listener = new DatabaseListener(conn);
			listener.start();
		}
		catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}

	}
}
