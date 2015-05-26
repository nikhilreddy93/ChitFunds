<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" import="java.io.*"%>

<% Class.forName("com.mysql.jdbc.Driver"); %>

	<% 
	PrintWriter writer = response.getWriter();
	HttpSession session1=request.getSession(false); 
    String Name=(String)session1.getAttribute("Name");
   // System.out.println("session in register = "+session1.getId());
    if(Name!=null){ 
	
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");

	            Statement statement = connection.createStatement() ;
	            Statement statement1 = connection.createStatement() ;
	            Object value=request.getAttribute("userId");
	            ResultSet 
	            userSet= statement.executeQuery("select chitname from chitassigning where userId='"+value+"'") ;
	            	 ResultSet chitSet=statement1.executeQuery("select chitname from newchit");
	            	 %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
	<link rel="stylesheet" href="style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="body">
		<img src="image 3.jpg" height="250" width="1000">
	<header class = "mainHeader">
	<nav><ul>
		<li><a href="UserHome.jsp">Home</a></li>
		<li><a href="AboutUs.jsp">About Us</a></li>
		<li><a href="ContactUs.jsp">Contact Us</a></li>
		<li><a href="LogoutServlet">Sign Out</a></li>
			</ul></nav>
			</header>
			
			<div class = "mainContent">
				<div class = "content">
					<article class = "topcontent">
					<header>
						<h2>User Details</h2>
					</header>
					<footer>
						<p class="post-info">Here are the details of you</p>
					</footer>
					<content>
					<form action="UpdateUserDetails" method="post">
				<label><b>User Id:</b></label><br>
							<input type = "text"  value="<%=request.getAttribute("userId")%>" placeholder="userid" name="userId" readonly/>
				<p></p>
				<p></p>
				<label><b>Full Name:</b></label><br>
							<input type = "text" value="<%=request.getAttribute("fullname")%>"  placeholder= "Name" name="fullname" readonly/> <br>
				<p></p>
				<p></p>
				<label><b>Date Of Birth</b></label><br>
							<input type = "date" value="<%=request.getAttribute("dob")%>" id = "myText" placeholder="yyyy-mm-dd" name="dob" readonly /> <br>
				<p></p>
				<p></p>
				<label><b>Mobile:</b></label><br>
							<input type = "tel" value="<%=request.getAttribute("phone")%>" id = "myText" placeholder="995563215" name="phone" readonly />
				<p></p>
				<p></p>
				<label><b>Email:</b>(optional)</label><br>
							<input type = "email" value="<%=request.getAttribute("email")%>" id = "myText" placeholder="xyz@123.com" name="email" readonly/>
				<p></p>
				<p></p>
			   <label><b>Address:</b></label><br>
							<input type = "text" value="<%=request.getAttribute("address")%>" id = "myText" placeholder="xyz@123.com" name="address" readonly/>
			    <p></p>
				<p></p>
			   </form>
			      <br>
			      <h3>User involved Chits </h3>
			      <footer>
						<p class="post-info">Here are the chits in which this user is involved select one of the chit to check that chit details</p>
					</footer>
					
					<form action="DisplayChitMainDetails" method="post">
			      <select name="availablechitlist" required>
			      <option value="" style="display:none">Please Select a chit</option>
											<%
						while(userSet.next()){ %>
			            <TR>
			                <option> <%= userSet.getString(1)%></option>
			               
			            </TR>
			            <% }
						%>
			      </select>
			      <br>
		
			      
			      <br>
			      <p></p>
				<p></p>
				
					<h3>Details of the above selected chit</h3>
			      Paid amount: <br><textarea rows="1" cols="45" name="paidamount" placeholder="Paid"></textarea><br>
			      <p></p>
				<p></p>
			      Due amount:  <br><textarea rows="1" cols="45" name="dueamount" placeholder="Due"></textarea><br>
			      <p></p>
				<p></p>
			      Paid months: <br><textarea rows="1" cols="45" name="paidamount" placeholder="Months"></textarea>
			      <p></p>
				<p></p>

							</form>
	
		</content>
		</article>
					
				
			</div>
			
			
				
				<aside class="top-sidebar">
					<article>
						<header>
						<h2>Documents Submitted</h2>
						</header>
						<img src="C:\Users\nikhil\Pictures\chudu thammudu pics\10408572_1557503121179927_9030024637147022898_n.jpg" height="225px" width="200px" name ="photo" id="photo id">
				    </article>
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

