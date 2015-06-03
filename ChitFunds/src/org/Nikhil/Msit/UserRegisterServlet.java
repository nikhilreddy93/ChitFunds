package org.Nikhil.Msit;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.oreilly.servlet.MultipartRequest; 

/**
 * Servlet implementation class UserRegisterServlet
 * 
 */
//@WebServlet("/UserRegisterServlet") 
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB 
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserRegisterServlet user = new UserRegisterServlet();
		String userId = "";
		String password = "";
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        int result = 0;
        @SuppressWarnings("unused")
		int result1=0;
        
        String fullname=request.getParameter("fullname");
        String dob=request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email= request.getParameter("email");
        String address = request.getParameter("address");
        String filename =request.getParameter("photos");
        
       /* /////////////////////////////////////////////////////
        //my code for file uploading
        InputStream inputStream = null; // input stream of the upload file
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photos");
      //  if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        //}
        ////////////////////////////////////////////////////////
*/        
        password = phone;
        System.out.println("password = "+password);
        userId = user.GenerateCredentials(userId);
        System.out.println("username = " + userId);
        try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("insert into registration values(?,?,?,?,?,?)");
			PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
			
			statement.setString(1, userId);
			statement.setString(2, fullname);
			statement.setString(3, dob);
			statement.setString(4, phone);
			statement.setString(5, email);
			statement.setString(6, address);
			
			MultipartRequest m=new MultipartRequest(request,"d:/new");  
			/*/////////////////////////////////////////////////////////
			if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                //statement.setBlob(7, inputStream);
				statement.setBinaryStream(7, inputStream, (int)filePart.getSize());
				//statement.setBinaryStream(7, inputStream);
            }
			/////////////////////////////////////////////////////////
			 * 
*/			result=statement.executeUpdate();
		/*	if (result>0){
				System.out.println("file uploaded succesfully");
			}*/
			
			statement1.setString(1, userId);
			statement1.setString(2, password);
			result1=statement1.executeUpdate();
			String msg="";
			if(result!=0){
	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
	      		
	      		 msg="values inserted";
	                  writer.println("<font size='6' color=blue>" + msg + "</font>");
	                  dispatcher=request.getRequestDispatcher("Organizer.jsp");
	                  dispatcher.forward(request,response);
	          }
	                  else{
	              	 msg="values inserted";
	                         writer.println("<font size='6' color=blue>" + msg + "</font>");
	              	  }	
	                         
	          statement.close();
	                      }  
	                      catch (Exception e){  
	                    	  writer.println(e);
	                    	  writer.close();  
	                      }  
	}
	
	private String GenerateCredentials(String username) {
		Random r = new Random( System.currentTimeMillis() );
		username="USER_"+(1000000 + r.nextInt(7000000));
		return username;		
	}

}
