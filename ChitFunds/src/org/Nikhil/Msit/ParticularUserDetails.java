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
 * Servlet implementation class ParticularUserDetails
 */
@WebServlet("/ParticularUserDetails")
public class ParticularUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		HttpSession session=request.getSession(false); 
        String Name=(String)session.getAttribute("Name");
//        System.out.println("Name in particul user details = "+Name);
//        System.out.println("session in register = "+session.getId());
        if(Name!=null){
		response.setContentType("text/html");
		Statement st = null;
		//RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        ResultSet result = null;
        
        //System.out.println("userid in particular = "+Name);
        
        try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
			//PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+formName+"'");
			st = conn.createStatement();
			//PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
			//statement.setString(1, "forName");
			result = st.executeQuery("select * from registration where userId='"+Name+"'");
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
	      		
//	      		System.out.println("if");
//	      		System.out.println(userId);
//	      		System.out.println(fullname);
//	      		System.out.println(dob);
//	      		System.out.println(phone);
//	      		System.out.println(email);
//	      		System.out.println(address);

				
	      		request.setAttribute("userId", userId);
	      		request.setAttribute("fullname", fullname);
	      		request.setAttribute("dob", dob);
	      		request.setAttribute("phone", phone);
	      		request.setAttribute("email", email);
	      		request.setAttribute("address", address);
	      		
	    
	      		request.getServletContext().getRequestDispatcher("/UserNonEditable.jsp").forward(request,response);
	      		msg="Welcome "+Name;
              	writer.print("<font size='6' color=blue>" + msg + "</font>");   
	               	                  
	          }
	                  else{
	                	 
	                         writer.println("<font size='6' color=blue>" + msg + "</font>");
	                         request.getServletContext().getRequestDispatcher("/UserNonEditable.jsp").forward(request, response);
	              	  }	
	                         
	          st.close();
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
