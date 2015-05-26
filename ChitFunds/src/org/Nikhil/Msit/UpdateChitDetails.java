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
 * Servlet implementation class UpdateChitDetails
 */
@WebServlet("/UpdateChitDetails")
public class UpdateChitDetails extends HttpServlet {
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
	        //System.out.println("session in register = "+session.getId());
	        if(Name!=null){
		RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        int result=0;
        String button1=request.getParameter("button");
        String chitname = request.getParameter("chitname");
  		String chitamount = request.getParameter("chitamount");
  		String minimumamount = request.getParameter("minimumamount");
  		String customerlimit = request.getParameter("customerlimit");
  		String chitspan = request.getParameter("chitspan");
        
        if(button1.equals("Update")){
        	try {
    			Class.forName(driver);
    			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
    			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("update newchit set chitamount=?,noofusers=?,noofmonths=?,mindraw=? where chitname=? ");
    			
    			statement.setString(1, chitamount);
    			statement.setString(2, customerlimit);
    			statement.setString(3, chitspan);
    			statement.setString(4, minimumamount);
    			statement.setString(5, chitname);
    			result=statement.executeUpdate();
    			
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
        }else
        	if(button1.equals("Delete")){
        		try {
        			Class.forName(driver);
        			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
        			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("delete from newchit where chitname='"+chitname+"'");
        			
  
        			result=statement.executeUpdate();
        			
        			String msg="";
        			if(result!=0){
        	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
        	      		
        	      		 msg="Chit Deleted";
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
        
        	else
        		if(button1.equals("Submit")){
        			
        			 String monthamount= request.getParameter("monthamount");
        		        String commission=request.getParameter("commission");
        		        String monthsleft="20";
        		        
        		        
        		        double mainUserToPay=Double.parseDouble(chitamount)/Double.parseDouble(customerlimit);
        				double amountAfterSong=Double.parseDouble(chitamount)-Double.parseDouble(monthamount);
        				double OrganizerCommission=(Double.parseDouble(monthamount)*Double.parseDouble(commission))/100;
        					monthamount=Double.parseDouble(monthamount)-OrganizerCommission+"";
        				double UserToPay= Double.parseDouble(monthamount)/Double.parseDouble(customerlimit);
        					UserToPay= (mainUserToPay-UserToPay);
        					
        					String OrganizerCommission1 = OrganizerCommission+"";
        					String UserToPay1 = UserToPay+"";
        					
        					
        				System.out.println("amount after song = "+amountAfterSong);
        				System.out.println("OrganizerCommission = "+OrganizerCommission1);
        				//System.out.println("UserToPay = "+UserToPay1);
        				
        		        
        				try {
        					Class.forName(driver);
        					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
        					PreparedStatement statement =(PreparedStatement) conn.prepareStatement("insert into chitdetails values(?,?,?,?,?,20,curdate(),?)");
        					System.out.println("query executed");
        					statement.setString(1, chitname);
        					statement.setString(2, monthamount);
        					statement.setString(3, commission);
        					statement.setString(4, OrganizerCommission1);
        					statement.setString(5, monthsleft);
        					statement.setDouble(6, UserToPay);
        					result=statement.executeUpdate();
        		      		
        					
        					if(result!=0){
        						request.setAttribute("monthamount", monthamount);
        						request.setAttribute("usertopay", UserToPay1);
        						request.setAttribute("commissionamount", OrganizerCommission);
        						request.setAttribute("monthsleft", monthsleft);
        						writer.println("<font size='6' color=blue></font>");
        		                dispatcher=request.getRequestDispatcher("ChitDetails.jsp");
        		                dispatcher.forward(request,response);
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
	            	writer.print("<font size='6' color=red>" + msg + "</font>");
	        	 request.getRequestDispatcher("Home.html").include(request, response);
	        }
        
        
	}

}
