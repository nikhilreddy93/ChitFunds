<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
        <%@ page import="java.sql.*" import="java.io.*"%>

<% Class.forName("com.mysql.jdbc.Driver"); %>

	<% 
	PrintWriter writer = response.getWriter();
	HttpSession session1=request.getSession(false); 
    String Name=(String)session1.getAttribute("Name");
    //System.out.println("session in register = "+session1.getId());
    if(Name!=null){ 
    	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>About Us</title>
	<link rel="stylesheet" href="AboutUs.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="body">
		<img src="image 3.jpg" height="250" width="1000">
	<header class = "mainHeader">
	<nav><ul>
	<%if(Name.charAt(0)=='U'){
		%>
		<li><a href="UserHome.jsp">Home</a></li>
		<%}else{
			%>
		<li><a href="OrganizerHome.jsp">Home</a></li>
		<%} %>
		<li class="active"><a href="#">About Us</a></li>
		<li><a href="ContactUs.jsp">Contact Us</a></li>
		<li><a href="LogoutServlet">Sign Out</a></li>
			</ul></nav>
			</header>
			
			<div class = "mainContent">
				<div class = "content">
					<article class = "topcontent">
					<header>
						<h2>About Us</h2>
					</header>
					<content>
					 <p>1.This WebSite is only for registered customers</p>
					 <p>2.If you want to join, feel free to walk to our office</p>
					 <p>3.Before joining,submit following documents</p> 
					 <ul>
						 <li>1.One ID proof(voter id/Driving licenece)</li>
						 <li>2.One Address Proof(AAdhar card/Ration card)</li>
						 <li>3.Legal Documents(property documents originals)</li>
					</ul>
					<p>4.plase collect login details from office after submitting documents</p>
					
		</content>
		</article>
					
				
	
	<footer class="mainFooter">
		<p>Copyright &copy; 2013 <a href="http://1stwebdesigner.com">1stwebdesigner</a></p>
	</footer>

</body>
</html>

<%
        }
        else{
        	System.out.println("else in session");
        	String msg="Sorry please login first";
            	writer.print("<font size='6' color=blue>" + msg + "</font>");
        	 request.getRequestDispatcher("Home.html").include(request, response);
        }
%>

