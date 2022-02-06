package JDBC_Examples;

import java.sql.*;

public class ExecuteQueryDt01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:", "", "");
		
		Statement st = con.createStatement();
		
		//1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table
		
		//1.Way: BY using OFFSET and FETCH NEXT
		String sql1 = "SELECT company, number_of_employees\n"
						+ "FROM companies\n"
						+ "ORDER BY number_of_employees DESC\n"
						+ "OFFSET 1 ROW\n"
						+ "FETCH NEXT 1 ROW ONLY";
		
		ResultSet result1 = st.executeQuery(sql1);
		
		while(result1.next()) {
			System.out.println(result1.getString(1) + " -> " + result1.getInt(2));
		}
		
		//2.Way: By using subqueries
		String sql2 = "SELECT company, number_of_employees\n"
						+ "FROM companies\n"
						+ "WHERE number_of_employees = (SELECT MAX(number_of_employees) \n"
						+ "                             FROM companies \n"
						+ "                             WHERE  number_of_employees < (SELECT MAX(number_of_employees) \n"
						+ "                                                           FROM companies))";
		
		ResultSet result2 = st.executeQuery(sql2);
		
		while(result2.next()) {
			System.out.println(result2.getString(1) + " -> " + result2.getInt(2));
		}

		con.close();
		st.close();
		result1.close();
		result2.close();
	}

}
