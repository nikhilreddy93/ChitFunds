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
 * Servlet implementation class NewChitServlet
 */
@WebServlet("/NewChitServlet")
public class NewChitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		 int result=0;
		 String chitname=request.getParameter("chitname");
	        String chitamount=request.getParameter("chitamount");
	        String noofusers = request.getParameter("customerlimit");
	        String noofmonths=request.getParameterValues("chitspan")[0];	       
	        String mindraw = request.getParameter("minimumamount");
	        
	        //System.out.println("no of months : "+ request.getParameterValues("chitspan")[0]);
	        
	        try {
				Class.forName(driver);
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
				PreparedStatement statement =(PreparedStatement) conn.prepareStatement("insert into newchit values(?,?,?,?,?,curdate())");
				//PreparedStatement statement1  = (PreparedStatement)conn.prepareStatement("DELETE FROM `table` WHERE `timestamp` &gt; DATE_SUB(NOW(), INTERVAL 10 MINUTE);");
				
				statement.setString(1, chitname);
				statement.setString(2, chitamount);
				statement.setString(3, noofusers);
				statement.setString(4, noofmonths);
				statement.setString(5, mindraw);
				result=statement.executeUpdate();
				String msg="";
				if(result!=0){
		      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
		      		
		      		 msg="values inserted";
		                  writer.println("<font size='6' color=blue>" + msg + "</font>");
		                  dispatcher=request.getRequestDispatcher("NewChit.jsp");
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
		}
	        
	}


