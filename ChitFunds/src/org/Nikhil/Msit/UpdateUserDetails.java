package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateUserDetails
 */
@WebServlet("/UpdateUserDetails")
public class UpdateUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String userId;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		 HttpSession session=request.getSession(false); 
	        String Name=(String)session.getAttribute("Name");
	        System.out.println("session in register = "+session.getId());
	        if(Name!=null){
		RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        String button1 = request.getParameter("button");
        int result=0;
        		userId=request.getParameter("userId");
        String fullname=request.getParameter("fullname");
        String dob=request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email= request.getParameter("email");
        String address = request.getParameter("address");
        System.out.println("button1 = "+ button1);
        HttpSession session1 = request.getSession();
        session1.setAttribute("Name1", userId);
        System.out.println("Name1 in chitassigning = "+(String)session1.getAttribute("Name1"));
        
        if(button1.equals("Update")){
        	 try {
     			Class.forName(driver);
     			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
     			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("update registration set fullname=?,dob=?,phone=?,email=?,address=? where userId=? ");
     			
     			statement.setString(1, fullname);
     			statement.setString(2, dob);
     			statement.setString(3, phone);
     			statement.setString(4, email);
     			statement.setString(5, address);
     			statement.setString(6, userId);
     			result=statement.executeUpdate();
     			
     			String msg="";
     			if(result!=0){
     	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
     	      		
     	      		 msg="User Details Updated";
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
        	
        }else
        	if(button1.equals("Delete")){
        		try {
         			Class.forName(driver);
         			System.out.println("entered in delete");
         			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
         			System.out.println("one");
         			PreparedStatement statement2 =(PreparedStatement) conn.prepareStatement("delete from chitassigning where userId='"+userId+"'");
         			System.out.println("two");
         			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("delete from registration where userId='"+userId+"'");
         			
         			PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("delete from login where userId='"+userId+"'");
         
         			result=statement2.executeUpdate();
         			System.out.println("three");
         			result=statement.executeUpdate();
         			System.out.println("four");
         			result=statement1.executeUpdate();
        
         			String msg="";
         			if(result!=0){
         	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
         	      		
         	      		 msg="User Deleted";
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
	        }
	        else{
	        	System.out.println("else in session");
	        	String msg="Sorry please login first";
	            	writer.print("<font size='6' color=blue>" + msg + "</font>");
	        	 request.getRequestDispatcher("Home.html").include(request, response);
	        }
       
        
	}

}
