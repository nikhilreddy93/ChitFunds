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
	<title>Contact Us</title>
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
		<li><a href="AboutUs.jsp">About Us</a></li>
			<li class="active"><a href="#">Contact Us</a></li>
			<li><a href="LogoutServlet">Sign Out</a></li>
			</ul></nav>
			</header>
			
			<div class = "mainContent">
				<div class = "content">
					
					<header>
						<h2>Contact Us</h2>
					</header>
					<content>
					<p>7-1/1 Mainroad</p>
					<p>Brahmanapalli</p>
					<p>Pidiguralla</p>
					<p>Guntur</p>
					<p>Andhra Pradesh-522437</p>
					<p>cell:9177266819</p>
					<p>Email: venkatesh.2636@gmail.com</p>
					</div>
					</div>
					</article>
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

