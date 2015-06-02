package org.Nikhil.Msit;

import java.io.*;  

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;  

import com.oreilly.servlet.MultipartRequest;  
  
@WebServlet("/Documents")
public class Documents extends HttpServlet {  
  
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response)  
		throws ServletException, IOException {  
	
	response.setContentType("text/html");  
	PrintWriter out = response.getWriter();
	MultipartRequest m=new MultipartRequest(request,"LENOVO(D:)/new");  
	out.print("successfully uploaded");  
	RequestDispatcher dispatcher=request.getRequestDispatcher("Organizer.jsp");
                  dispatcher.forward(request,response);
                  
	}//post method  
}  //Documents