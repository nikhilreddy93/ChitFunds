package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserRegisterServlet
 */

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//loginServlet login = new loginServlet();

		PrintWriter writer = response.getWriter();
		HttpSession session=request.getSession(false); 
        String Name=(String)session.getAttribute("Name");
        //System.out.println("session in register = "+session.getId());
        if(Name!=null){ 
		UserRegisterServlet user = new UserRegisterServlet();
		//String userId1=login.userId;
		String userId = "";
		String password = "";
		response.setContentType("text/html");
		
		RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        int result = 0;
        @SuppressWarnings("unused")
		int result1=0;
         
        //	System.out.println("if in session");
       // System.out.println("name in register = "+Name);
        
        //System.out.println("userID 1 = "+ userId1);
        String fullname=request.getParameter("fullname");
        String dob=request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email= request.getParameter("email");
        String address = request.getParameter("address");
        String fname=request.getParameter("file");
        
        password = phone;
        //System.out.println("password = "+password);
        userId = user.GenerateCredentials(userId);
        //System.out.println("username = " + userId);
        try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("insert into registration values(?,?,?,?,?,?,curdate())");
			PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
			
			statement.setString(1, userId);
			statement.setString(2, fullname);
			statement.setString(3, dob);
			statement.setString(4, phone);
			statement.setString(5, email);
			statement.setString(6, address);
			result=statement.executeUpdate();
			
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
        else{
        	System.out.println("else in session");
        	String msg="Sorry please login first";
            	writer.print("<font size='6' color=red>" + msg + "</font>");
        	 request.getRequestDispatcher("Home.html").include(request, response);
        }

	
}//dopost method
	public String GenerateCredentials(String username) {
		Random r = new Random( System.currentTimeMillis() );
		username="USER_"+(1000000 + r.nextInt(7000000));
		return username;		
	}//generatecredentials
}
