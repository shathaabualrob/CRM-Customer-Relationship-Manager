package web.customer.tracker.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection variables
		String user = "springstudent";
		String password = "springstudent";
		
		/*
		 * Java database connectivity (JDBC) 
		 * is a standard API(application programming interface) 
		 * that allows Java programs to access database management systems
		 * like MySQL.
		*/
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		/* A JDBC driver is a software component 
		 * enabling a Java application to interact with a database.
		 * To connect with individual databases, 
		 * JDBC requires drivers for each database.
		 * */
		String driver = "com.mysql.jdbc.Driver";
		
		//get connection to database
		try {
			/*
			 * PrintWriter is a class 
			 * used to write any form of data 
			 * e.g. int, float, double, String or Object 
			 * in the form of text 
			 * either on the console or in a file in Java.
			*/
			PrintWriter out = response.getWriter();
			out.println("Connecting to the Database: "+jdbcUrl);
			
			// This method receives a class name
			// and returns an instance of that class.
			// We do this step here to load the database driver.
			Class.forName(driver);
			
			/*
			 * The DriverManager provides a basic service 
			 * for managing a set of JDBC drivers. 
			 * As part of its initialization, 
			 * the DriverManager class will attempt to 
			 * load the driver classes referenced 
			 * in the "jdbc.drivers" system property. 
			 * This allows a user to customize the JDBC Drivers 
			 * used by their applications.
			*/
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);
			
			out.println("SUCCESS!!!");
			
			// i need to close this here since 
			// it's just a test to check for the db connection
			myConn.close(); 
			
		} catch (Exception e) {
			e.printStackTrace();
			// this is just a hack for testing our code
			throw new ServletException(e);
		}
		
		
	}

}





