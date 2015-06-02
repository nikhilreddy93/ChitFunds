package org.Nikhil.Msit;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String userId;
	//public Cookie ck;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		RequestDispatcher dispatcher = null;
		Connection conn = null;
		
        String driver="com.mysql.jdbc.Driver";
        ResultSet result = null;     
        		userId = request.getParameter("Username");
        String password = request.getParameter("Password");
        System.out.println("userid in login = "+userId);
        
        try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
	          PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from login where userId=? and password=?");
	          statement.setString(1,userId);  
	          statement.setString(2,password);
	          result = statement.executeQuery();
	          String msg="";
	          if(userId.charAt(0)=='U'){
	        	  if(result.next()){
	        		  //System.out.println("if part");
	        		  HttpSession session = request.getSession();
	                  session.setAttribute("Name", userId);
	                 // String id=session.getId();
	                 // System.out.println("session id in login ="+id);
	        		  msg="Welcome "+userId;
	  	              	writer.print("<font size='6' color=blue>" + msg + "</font>");
	                  dispatcher=request.getRequestDispatcher("UserHome.jsp");
	                  dispatcher.forward(request,response);
	                  
	                  /* code for cookie
	             	 ck = new Cookie("Name",userId);	                  
	                  response.addCookie(ck);
	                  String name=ck.getName();
	                  
	                  System.out.println("cookie name = "+name);
	                  Cookie[] value=request.getCookies();
	          		for(int i=0;i<value.length;i++){
	          			System.out.println("cookie name in login = "+value[i].getValue());
	          		}
	          		*/
	                  
	                  
	          }
	                  else{
	              	 msg="Sorry please check your username and password";
	              	writer.print("<font size='6' color=blue>" + msg + "</font>");
	                        // writer.println("<font size='6' color=blue>" + msg + "</font>");
	              	request.getRequestDispatcher("Home.html").include(request, response);
	              	  }
	          }
	          else{
	        	  if(result.next()){
	   
	  	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
	        		  HttpSession session = request.getSession();
	                  session.setAttribute("Name", userId);
	                  //String id=session.getId();
	                  //System.out.println("session id in login ="+id);
	                  //System.out.println("else part");
	  	      		 msg="valid user";
	  	                  writer.println("<font size='6' color=blue>" + msg + "</font>");
	  	                  dispatcher=request.getRequestDispatcher("OrganizerHome.jsp");
	  	                  dispatcher.forward(request,response);
	  	                
		                  
		                  
	  	                  /* code for cookie
	  	                  ck = new Cookie("Name",userId);
		                  response.addCookie(ck);
		                  String name=ck.getName();
		                  System.out.println("cookie name = "+name);
		                  Cookie[] value=request.getCookies();
		          		for(int i=0;i<value.length;i++){
		          			System.out.println(value[i].getName());
		          			System.out.println("cookie name in login = "+value[i].getValue());
		          		}
		          		*/
	  	                  
	  	                  
	  	          }
	  	                  else{
	  	              	 msg="Sorry please check your username and password";
	  	              	writer.print("<font size='6' color=blue>" + msg + "</font>");
	  	                        // writer.println("<font size='6' color=blue>" + msg + "</font>");
	  	              	request.getRequestDispatcher("Home.html").include(request, response);
	  	              	  }
	          }
	        	  
	                         
	          statement.close();
	                      }  
	                      catch (Exception e){  
	                    	  writer.println(e);
	                    	  writer.close();  
	                      }
        }
        
	                  
	      	  }

	          


