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
 * Servlet implementation class DisplayChitDetails
 */
@WebServlet("/DisplayChitDetails")
public class DisplayChitDetails extends HttpServlet {
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
        System.out.println("session in dispay chit details = "+session.getId());
        if(Name!=null){
		Statement st = null;
		//RequestDispatcher dispatcher = null;
		Connection conn = null;
        String driver="com.mysql.jdbc.Driver";
        String formName = request.getParameter("chitlist");
        ResultSet result = null;
        int result1=0;
         
        String button = request.getParameter("button");
        System.out.println("userid = "+formName);
        if(button.equals("View")){
        	 try {
     			Class.forName(driver);
     			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
     			//PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+formName+"'");
     			st = conn.createStatement();
     			//PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
     			//statement.setString(1, "forName");
     			result = st.executeQuery("select * from newchit where chitname='"+formName+"'");
     			System.out.println("query execured");
           		
          
     			String msg="";
     			if(result.next()){
     	      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");
     				String chitname = result.getString("chitname");
     	      		String chitamount = result.getString("chitamount");
     	      		String minimumamount = result.getString("mindraw");
     	      		String customerlimit = result.getString("noofusers");
     	      		String chitspan = result.getString("noofmonths");
     	      		
     	      		request.setAttribute("chitname", chitname);
     	      		request.setAttribute("chitamount", chitamount);
     	      		request.setAttribute("minimumamount", minimumamount);
     	      		request.setAttribute("customerlimit", customerlimit);
     	      		request.setAttribute("chitspan", chitspan);

     	      		request.getServletContext().getRequestDispatcher("/ChitDetails.jsp").
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
        	if(button.equals("Delete")){
        		 try {
        				Class.forName(driver);
        				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
        				//PreparedStatement statement =(PreparedStatement) conn.prepareStatement("select * from registration where userId='"+formName+"'");
        				st = conn.createStatement();
        				//PreparedStatement statement1 =(PreparedStatement) conn.prepareStatement("insert into login values(?,?)");
        				//statement.setString(1, "forName");
        				result1 = st.executeUpdate("delete from newchit where chitname='"+formName+"'");
        				System.out.println("query execured");
        	      		
        	     
        				String msg="";
        				if(result1!=0){
        		      		//RequestDispatcher dispatcher1=request.getRequestDispatcher("Organizer.html");

        		      		request.getServletContext().getRequestDispatcher("/Organizer.jsp").
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
       
	}
        else{
        	System.out.println("else in session");
        	String msg="Sorry please login first";
            	writer.print("<font size='6' color=blue>" + msg + "</font>");
        	 request.getRequestDispatcher("Home.html").include(request, response);
        }
	}

}
