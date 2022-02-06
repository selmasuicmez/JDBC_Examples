package JDBC_Examples;

import java.sql.*;

public class PreparedStatement01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:", "", "");
		
		Statement st =con.createStatement();
		
		//1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
		
		//1.Step:Create prepared statement query
		String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";
		//2.Step:Create PreparedStatement object
		PreparedStatement pst1 = con.prepareStatement(sql1);
		//3.Step:Set the values for ? marks
		pst1.setInt(1, 9999);
		pst1.setString(2, "IBM");
		//4.Step:Execute the query
		int numOfUpdatedRecs = pst1.executeUpdate();
		System.out.println(numOfUpdatedRecs + " rows updated");
		
		String sql2 = "SELECT * FROM companies";
		ResultSet result1 = st.executeQuery(sql2);
		
		while(result1.next()) {
			System.out.println(result1.getInt(1) + " - " + result1.getString(2) + " - " + result1.getInt(3));
		}
		
		pst1.setInt(1, 1111);
		pst1.setString(2, "GOOGLE");
		
		int numOfUpdatedRecs2 = pst1.executeUpdate();
		System.out.println(numOfUpdatedRecs2 + " rows updated");
		
		String sql3 = "SELECT * FROM companies";
		ResultSet result2 = st.executeQuery(sql3);
		
		while(result2.next()) {
			System.out.println(result2.getInt(1) + " - " + result2.getString(2) + " - " + result2.getInt(3));
		}
		
		//2.Example: Use prepared statement in "SELECT * FROM <table name>"
		String sql4 = "SELECT * FROM ?";
		/*
		 	Note: Above code does not run in Prepared Statements because
		 	PreparedStatement is for the column values not for table name.
		 	Because of that we cannot use “SELECT * FROM ?” in prepared statement
		 */

	}

}
