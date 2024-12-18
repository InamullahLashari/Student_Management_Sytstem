package in.sp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import in.sp.dbcm.DBMC_Connectivity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class Main extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Retrieve form parameters
        String name1 = req.getParameter("Name");
        String roll1 = req.getParameter("Roll");
        String class1 = req.getParameter("Class");
        String admissionDate1 = req.getParameter("Admission_Date");

        try {
            // Validate and parse admissionDate1
            Date sqlDate = null;
            try {
                sqlDate = Date.valueOf(admissionDate1); // Convert to java.sql.Date (format: yyyy-MM-dd)
            } catch (IllegalArgumentException e) {
                throw new ServletException("Invalid date format. Please use yyyy-MM-dd.", e);
            }

            // Establish database connection
            Connection con = DBMC_Connectivity.getconnect();

            // Prepare SQL query
            String query = "INSERT INTO Std (name, roll, class, admission_date) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name1);
            ps.setInt(2, Integer.parseInt(roll1)); // Convert roll1 to Integer
            ps.setString(3, class1);
            ps.setDate(4, sqlDate); // Set date parameter

            // Execute the query
            int result = ps.executeUpdate();

            // Handle success or failure
            if (result > 0) {
                out.println("<h3 style='color:green'>Registration successful!</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
                rd.forward(req, resp); // Forward to index.jsp
            } else {
                out.println("<h3 style='color:red'>Registration failed. Try again.</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("/register.html");
                rd.include(req, resp); // Include the registration page
            }

            // Close resources
            ps.close();
            con.close();

        } catch (NumberFormatException e) {
            out.println("<h3 style='color:red'>Invalid roll number. Please enter a valid integer.</h3>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<h3 style='color:red'>Error occurred: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}
