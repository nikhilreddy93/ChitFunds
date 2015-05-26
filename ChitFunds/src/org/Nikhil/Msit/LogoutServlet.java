package org.Nikhil.Msit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
//        request.getSession().invalidate();
//        response.sendRedirect(request.getContextPath() + "/Home.html");
        request.getRequestDispatcher("Home.html").include(request, response);  
          
        HttpSession session=request.getSession();  
        session.invalidate();
        HttpSession session1 = request.getSession();
        session1.invalidate();
       // System.out.println("session in signout:"+session.getId());
          String msg = "You have Successfully logged out";
        out.print("<font size='6' color=blue>" + msg + "</font>");
	}

}
