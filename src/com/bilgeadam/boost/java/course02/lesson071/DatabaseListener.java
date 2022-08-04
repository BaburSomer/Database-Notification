package com.bilgeadam.boost.java.course02.lesson071;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

public class DatabaseListener extends Thread {
	private Connection   conn;
	private PGConnection pgConn;
	
	public DatabaseListener(Connection conn) throws SQLException {
		super();
		this.conn = conn;
		this.pgConn = (PGConnection)conn;
		Statement stmt = conn.createStatement();
		stmt.execute("LISTEN new_log_entry_created");
		stmt.close();
	}
	
	public void run() {
		try {
			while (true) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT 1"); // bekleyen tüm notificationları okuyabiliyoruz
				rs.close();
				stmt.close();
				
				PGNotification[] notifications = pgConn.getNotifications();
				if (notifications != null) {
					for (PGNotification notification : notifications) {
						System.out.println("PostGreSql'den mesaj var: " + notification.getName() + " -- " + notification.getParameter());
					}
				}
				else {
					System.out.println("No news good news");
				}
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
