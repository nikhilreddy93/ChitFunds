package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Statement;



import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainLogic
 */
@WebServlet("/MainLogic")
public class MainLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//MainLogic logic = new MainLogic();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		RequestDispatcher dispatcher = null;
		Connection conn = null;
		//Statement st = null;
        String driver="com.mysql.jdbc.Driver";
        int result=0;
        System.out.println("entered in main logic");
        String chitname=request.getParameter("chitname");
        String chitamount = request.getParameter("chitamount");
        String customerslimit= request.getParameter("customerlimit");
        String monthamount= request.getParameter("monthamount");
        String commission=request.getParameter("commission");
        String monthsleft="20";
//        String chitname="chit11";
//        String chitamount="100000";
//        String customerslimit="20";
//        String monthamount="40000";
//        String commission="5";
        
        System.out.println(chitname);
        System.out.println(chitamount);
        System.out.println(customerslimit);
        System.out.println(monthamount);
        System.out.println(commission);
        
       // System.out.println(monthsleft);
        
        // main logic
        //int firstPayAmount=Integer.parseInt(chitamount)/Integer.parseInt(customerslimit);
        double mainUserToPay=Double.parseDouble(chitamount)/Double.parseDouble(customerslimit);
		double amountAfterSong=Double.parseDouble(chitamount)-Double.parseDouble(monthamount);
		double OrganizerCommission=(Double.parseDouble(monthamount)*Double.parseDouble(commission))/100;
			monthamount=Double.parseDouble(monthamount)-OrganizerCommission+"";
		double UserToPay= Double.parseDouble(monthamount)/Double.parseDouble(customerslimit);
			UserToPay= mainUserToPay-UserToPay;
		
			String OrganizerCommission1 = OrganizerCommission+"";
			String UserToPay1 = UserToPay+"";
			
			
		System.out.println("amount after song = "+amountAfterSong);
		System.out.println("OrganizerCommission = "+OrganizerCommission1);
		System.out.println("UserToPay = "+UserToPay1);
		
        
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
			PreparedStatement statement =(PreparedStatement) conn.prepareStatement("insert into chitdetails values(?,?,?,?,?,?)");
			System.out.println("query execured");
			statement.setString(1, chitname);
			statement.setString(2, monthamount);
			statement.setString(3, commission);
			statement.setString(4, OrganizerCommission1);
			statement.setString(5, UserToPay1);
			statement.setString(6, monthsleft);
			result=statement.executeUpdate();
      		
			
			if(result!=0){
				request.setAttribute("monthamount", monthamount);
				request.setAttribute("usertopay", UserToPay);
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
		
		// end of main logic
	}

}
