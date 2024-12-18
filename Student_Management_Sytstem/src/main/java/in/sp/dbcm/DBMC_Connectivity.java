package in.sp.dbcm;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBMC_Connectivity {
	
	//connection is interface that provide by java sql
	public static Connection getconnect() {
		
		Connection con= null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish the connection
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/student", // Correct JDBC URL
	                "root", // Database username
	                "password" // Database password
	            );
					
					} 

		
		catch (Exception e) {
			
			e.printStackTrace();
			}
	
		return con;
		
		
		
	}

}
