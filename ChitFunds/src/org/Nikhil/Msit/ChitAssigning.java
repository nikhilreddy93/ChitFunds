package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChitAssigning
 */
@WebServlet("/ChitAssigning")
public class ChitAssigning extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//DisplayUserDetails userDetails = new DisplayUserDetails();
		PrintWriter writer = response.getWriter();
		 HttpSession session=request.getSession(false); 
	        String Name=(String)session.getAttribute("Name");
	        System.out.println("session in register = "+session.getId());
	        if(Name!=null){  
		
		RequestDispatcher dispatcher = null;
		Connection conn = null;
		String driver="com.mysql.jdbc.Driver";
		System.out.println("userid chitassigning = "+Name);
		ResultSet result=null;
		int result1=0;
		HttpSession session1=request.getSession(false);
		String userId = (String)session1.getAttribute("Name1");
		String formname = request.getParameter("availablechitlist");
		System.out.println("chit assigning form name = "+formname);
		System.out.println("userid in chitassigning = "+userId);
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("insert into chitassigning values(?,?)");
			PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+userId+"'");
			statement.setString(1, userId);
			statement.setString(2, formname);
			result = statement1.executeQuery();
			result1=statement.executeUpdate();
			String msg="";
			if(result1!=0 && result.next()){
	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
				//String userId = result.getString("userId");
	      		String fullname = result.getString("fullname");
	      		String dob = result.getString("dob");
	      		String phone = result.getString("phone");
	      		String email = result.getString("email");
	      		String address = result.getString("address");

	      		request.setAttribute("userId", userId);
	      		request.setAttribute("fullname", fullname);
	      		request.setAttribute("dob", dob);
	      		request.setAttribute("phone", phone);
	      		request.setAttribute("email", email);
	      		request.setAttribute("address", address);
	      		
	      		 msg="User Assigned to the chit";
	                  writer.println("<font size='6' color=blue>" + msg + "</font>");
	                  
	                  dispatcher=request.getRequestDispatcher("/UserEditable.jsp");
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
	        	 else{
	             	System.out.println("else in session");
	             	String msg="Sorry please login first";
	                 	writer.print("<font size='6' color=blue>" + msg + "</font>");
	             	 request.getRequestDispatcher("Home.html").include(request, response);
	             }
		
	}


}
