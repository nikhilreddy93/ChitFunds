package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayUserPaidDetails
 */
@WebServlet("/DisplayUserPaidDetails")
public class DisplayUserPaidDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		HttpSession session=request.getSession(false);
		 HttpSession session1=request.getSession(false); 
	        String Name=(String)session1.getAttribute("Name1"); // main session id
	        String Name1 = (String)session.getAttribute("Name");// this name is for login id
	        

	        if(Name1!=null){
		RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        ResultSet result = null,result2=null;
        int result1=0;
        
        String button1=request.getParameter("button");
        //System.out.println("value of button = "+button1);
        String userId = Name;
        //System.out.println("Name in paid details:"+Name1);    
           
        if(button1.equals("Submit")){
        	int ThisMonthAmount = Integer.parseInt(request.getParameter("monthamount"));
        	String chitname = request.getParameter("userinvolved");
        	 //System.out.println("amount = "+ThisMonthAmount);
        	try {
				Class.forName(driver);
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
				System.out.println("entered in try block");
				PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from userpaydetails where userId=? and chitname=?");
				PreparedStatement statement2 =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+userId+"'");
				statement.setString(1, userId);
				statement.setString(2, chitname);

				result=statement.executeQuery();
				result2=statement2.executeQuery();
				
				System.out.println("query executed");
				if(result.next()&&result2.next()){
    	      		String fullname = result2.getString("fullname");
    	      		String dob = result2.getString("dob");
    	      		String phone = result2.getString("phone");
    	      		String email = result2.getString("email");
    	      		String address = result2.getString("address");

    	      		request.setAttribute("userId", userId);
    	      		request.setAttribute("fullname", fullname);
    	      		request.setAttribute("dob", dob);
    	      		request.setAttribute("phone", phone);
    	      		request.setAttribute("email", email);
    	      		request.setAttribute("address", address);
					int AmountAlreadyPaid = result.getInt("amountpaidalready");
					System.out.println("amount already paid = "+AmountAlreadyPaid);
					int monthsleft=20;
				
					int amountpaidalready = 0;
					    amountpaidalready = amountpaidalready+ThisMonthAmount;					    
					    AmountAlreadyPaid = amountpaidalready;
					    System.out.println("amount already paid = "+AmountAlreadyPaid);
					    PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("update userpaydetails set paidondate=curdate(),thismonthamount=?,amountpaidalready=? where userId=? and chitname=?");
					    statement1.setInt(1, ThisMonthAmount);
					    statement1.setInt(2, AmountAlreadyPaid);
					    statement1.setString(3, userId);
					    statement1.setString(4,chitname);
					    result1=statement1.executeUpdate();
					  						
						    request.setAttribute("paidamount", AmountAlreadyPaid);
						    request.setAttribute("monthsleft", monthsleft);
						    
						    request.getServletContext().getRequestDispatcher("/UserEditable.jsp").
      	                  //dispatcher=request.getRequestDispatcher("Organizer.jsp");
      	                  forward(request,response);
						    
					  
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				writer.println(e);
          	  writer.close();
			} 
			
        	
        }
        else
        	if(button1.equals("View")){
        		try {
    				Class.forName(driver);
    				System.out.println("entered in view block and button name is"+ button1);
    				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
    				System.out.println("entered in try block");
    				String chitname1 = request.getParameter("availablechitlist");
    				PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from userpaydetails where userId=? and chitname=?");
    				PreparedStatement statement2 =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+Name1+"'");
    				statement.setString(1, Name1);
    				statement.setString(2, chitname1);
    				
    				System.out.println("userId = "+Name1);
    				System.out.println("chitname1 = "+chitname1);

    				result=statement.executeQuery();
    				result2=statement2.executeQuery();
    				
    				System.out.println("query executed");
    				if(result.next()&&result2.next()){
        	      		String fullname = result2.getString("fullname");
        	      		String dob = result2.getString("dob");
        	      		String phone = result2.getString("phone");
        	      		String email = result2.getString("email");
        	      		String address = result2.getString("address");

        	      		request.setAttribute("userId", Name1);
        	      		request.setAttribute("fullname", fullname);
        	      		request.setAttribute("dob", dob);
        	      		request.setAttribute("phone", phone);
        	      		request.setAttribute("email", email);
        	      		request.setAttribute("address", address);
    					int AmountAlreadyPaid = result.getInt("amountpaidalready");
    					int monthsleft=20;
    				

    					  						
    						    request.setAttribute("paidamount", AmountAlreadyPaid);
    						    request.setAttribute("monthsleft", monthsleft);
    						  
    						    
    						    request.getServletContext().getRequestDispatcher("/UserNonEditable.jsp").
          	                  //dispatcher=request.getRequestDispatcher("Organizer.jsp");
          	                  forward(request,response);
    						    
    					  
    					
    				}
    				else{
    					request.getServletContext().getRequestDispatcher("/UserNonEditable.jsp").
    	                  //dispatcher=request.getRequestDispatcher("Organizer.jsp");
    	                  forward(request,response);
    				}
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
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
