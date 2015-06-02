<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" import="java.io.*" %>
     <% Class.forName("com.mysql.jdbc.Driver"); %>
     
     <% 
	PrintWriter writer = response.getWriter();
	HttpSession session1=request.getSession(false); 
    String Name=(String)session1.getAttribute("Name");
    //System.out.println("session in register = "+session1.getId());
    if(Name!=null){ 
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
	String msg="Welcome "+Name;
	writer.print("<font size='6' color=blue>" + msg + "</font>");
     %>
     
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
	<link rel="stylesheet" href="style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="body">
		<img src="image 3.jpg" height="250" width="1000">
	<header class = "mainHeader">
	<nav><ul>
		<li class="active"><a href="#">Home</a></li>
		<li><a href="AboutUs.jsp">About Us</a></li>
			<li><a href="ContactUs.jsp">Contact Us</a></li>
			<li><a href="LogoutServlet">Sign Out</a></li>
			</ul></nav>
			</header>
			
			<div class = "mainContent">
				<div class = "content">
					<article class = "topcontent">
					<header>
						<h2>Instructions</h2>
					</header>
					
					 <p>1.This WebSite is only for registered customers</p>
					 <p>2.If you want to join, feel free to walk to our office</p>
					 <p>3.Before joining,submit following documents</p> 
					 <ul>
						 <li>1.One ID proof(voter id/Driving license)</li>
						 <li>2.One Address Proof(AAdhar card/Ration card)</li>
						 <li>3.Legal Documents(property documents originals)</li>
					</ul>
					<p>4.plase collect login details from office after submitting documents</p>
					

		</article>
					
			</div>
			
				<aside class="top-sidebar">
				<h2><b>Your Options</b></h2>
				<form action="ParticularUserDetails" method="post">
				<!-- <li><a href="http://localhost:8080/ChitFunds/ParticularUserDetails.java"><b>Your Page</b></a></li> -->
				<li><a href="http://localhost:8082/ChitFunds/ParticularUserDetails"><b>Your Page</b></a></li>
				<li><a href="UserNonEditable.jsp"><b>View Available Chits</b></a></li>
				</form>
				<footer>
						<p class="post-info">Select any option to check the details</p>
					</footer>
				
				</aside>
				
				
				</div>
	
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
