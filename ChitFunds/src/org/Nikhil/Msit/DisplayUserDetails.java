package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;



import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayUserDetails
 */
@WebServlet("/DisplayUserDetails")
public class DisplayUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		
		// checking the sessions
		 HttpSession session=request.getSession(false); 
	        String Name=(String)session.getAttribute("Name");
	        //System.out.println("session in register = "+session.getId());
	        if(Name!=null){
	        	// if session is there
	        	
	        	
	        	
		Statement st,st1,st2 = null;
		//RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        String formName = request.getParameter("userlist");
        ResultSet result = null;
        int result1=0;
        String button1=request.getParameter("button");
        //System.out.println("userid = "+formName);
        HttpSession session1 = request.getSession();
        session1.setAttribute("Name1", formName);
//        HttpSession session2=request.getSession(false);
//        System.out.println("Name2 in displayUserDetails = "+(String)session2.getAttribute("Name2"));
        if(button1.equals("View")){
        	try {
    			Class.forName(driver);
    			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
    			//PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+formName+"'");
    			st = conn.createStatement();
    			//PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
    			//statement.setString(1, "forName");
    			result = st.executeQuery("select * from registration where userId='"+formName+"'");
    			//System.out.println("query execured");
          		String msg="";
    			if(result.next()){
    	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
    				String userId = result.getString("userId");
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

    	      		request.getServletContext().getRequestDispatcher("/UserEditable.jsp").
    	                  //dispatcher=request.getRequestDispatcher("Organizer.jsp");
    	                  forward(request,response);
    	                  
    	               	                  
    	          } 
    	                  else{

    	                	  msg="values inserted";
    	                         writer.println("<font size='6' color=blue>" + msg + "</font>");
    	              	  }	
    	                         
    	          st.close();
    	                      }  
    	                      catch (Exception e){  
    	                    	  writer.println(e);
    	                    	  writer.close();  
    	                      }
    	}
        else
        	if(button1.equals("Delete")){
        		try {
        			Class.forName(driver);
        			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
        			//PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+formName+"'");
        			st = conn.createStatement();
        			st1 = conn.createStatement();
        			st2 = conn.createStatement();
        			//PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
        			//statement.setString(1, "forName");
        			result1 = st2.executeUpdate("delete from chitassigning where userId='"+formName+"'");
        			result1 = st.executeUpdate("delete from registration where userId='"+formName+"'");
        			result1 = st1.executeUpdate("delete from login where userId='"+formName+"'");
        	
        			System.out.println("query executed");
                  			
        			String msg="";
        			if(result1==0){


        	      		request.getServletContext().getRequestDispatcher("/Organizer.jsp").
        	                  //dispatcher=request.getRequestDispatcher("Organizer.jsp");
        	                  forward(request,response);
        	                  
        	               	                  
        	          }
        	                  else{
        	              	 msg="values inserted";
        	              	request.getServletContext().getRequestDispatcher("/Organizer.jsp").
        	              	forward(request,response);
        	                         writer.println("<font size='6' color=blue>" + msg + "</font>");
        	              	  }	
        	                         
        	          st.close();st1.close();st2.close();
        	                      }  
        	                      catch (Exception e){  
        	                    	  writer.println(e);
        	                    	  writer.close();  
        	                      }
        	}
        
        	else
        		if(button1.equals("Remove")){
        			try {
            			Class.forName(driver);
            			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
            			//PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+formName+"'");
//            			st = conn.createStatement();
//            			st1 = conn.createStatement();
            			st2 = conn.createStatement();
            			//PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
            			//statement.setString(1, "forName");
            			result1 = st2.executeUpdate("delete from chitassigning where userId='"+formName+"'");
//            			result1 = st.executeUpdate("delete from registration where userId='"+formName+"'");
//            			result1 = st1.executeUpdate("delete from login where userId='"+formName+"'");
            	
            			System.out.println("query executed");
                      			
            			String msg="";
            			if(result1==0){


            	      		request.getServletContext().getRequestDispatcher("/Organizer.jsp").
            	                  //dispatcher=request.getRequestDispatcher("Organizer.jsp");
            	                  forward(request,response);
            	                  
            	               	                  
            	          }
            	                  else{
            	              	 msg="values inserted";
            	              	request.getServletContext().getRequestDispatcher("/Organizer.jsp").
            	              	forward(request,response);
            	                         writer.println("<font size='6' color=blue>" + msg + "</font>");
            	              	  }	
            	                         
            	          st2.close();
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
        
        


