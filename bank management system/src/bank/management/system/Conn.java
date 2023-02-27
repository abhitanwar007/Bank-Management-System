package bank.management.system;

import java.sql.*;

public class Conn {
	
	private Connection con;
	public Statement stmt;

	Conn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
			stmt = con.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
