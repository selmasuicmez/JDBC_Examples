package JDBC_Examples;

import java.sql.*;

public class CallableStatementDt01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:", "", "");
		
		Statement st  = con.createStatement();
		
		//1.Example: Create a function which uses 2 parameters and returns the sum of the parameters 
		//1.Step: Create function in SQL
		String sql1 = "CREATE OR REPLACE FUNCTION addf(a NUMBER, b NUMBER) "
						+ "RETURN NUMBER IS "
						+ "BEGIN "
							+ "RETURN a+b; "
						+ "END;";
		
		//2.Step: Execute sql1 to create the addf() function
		boolean result1 = st.execute(sql1);//execute() will return true because no records when you execute sql1
		System.out.println("execute(sql1) returns " + result1);
		
		//3.Step: Call function and use it
		CallableStatement cst1 = con.prepareCall("{ ? = call addf(?, ?) }");
		
		//4.Step: Do assignments for ? marks
		cst1.registerOutParameter(1, Types.INTEGER);
		cst1.setInt(2, 100);
		cst1.setInt(3, 50);
		
		//5.Step:Execute the function
		cst1.execute();
		
		//6.Step:See the result on the console
		System.out.println(cst1.getInt(1));//40
				
		con.close();
		st.close();
		cst1.close();
	}

}
