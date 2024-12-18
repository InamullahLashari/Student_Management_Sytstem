package in.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.sp.dbcm.DBMC_Connectivity;
import in.sp.controller.Data_store;

public class StudentDAO {

    // Method to fetch students from the database
	public List<Data_store> getStudents() {
	    List<Data_store> students = new ArrayList<>();  // Initialize the list to store students
	    
	    try {
	        Connection con = DBMC_Connectivity.getconnect();  // Get database connection
	        String query = "SELECT * FROM Std";  // SQL query to fetch students
	        
	        PreparedStatement ps = con.prepareStatement(query);  // Prepare the query
	        ResultSet rs = ps.executeQuery();  // Execute the query
	        
	        while (rs.next()) {
	            Data_store model = new Data_store();
	            model.setName(rs.getString("name"));
	            model.setRoll(rs.getString("roll"));
	            model.setStudentClass(rs.getString("class"));
	            model.setAdmissionDate(rs.getDate("admission_date"));  // Fetch as Date
	            students.add(model);
	        }
	        
	        rs.close();
	        ps.close();
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return students;  // Return the list of students
	}

        
      
}
