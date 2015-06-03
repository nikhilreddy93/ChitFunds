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

	            Statement statement = connection.createStatement() ;
	            Statement statement1 = connection.createStatement() ;
	            Object value=request.getAttribute("userId");
	            ResultSet 
	            userSet= statement.executeQuery("select chitname from chitassigning where userId='"+value+"'") ;
	            	 ResultSet chitSet=statement1.executeQuery("select chitname from newchit where chitname not in(select chitname from chitassigning where userId='"+value+"')");
	            	 		   //chitSet=statement1.executeQuery("select chitname from newchit where chitname not in(select chitname from chitassigning where userId='"+value+"')");
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
		<li><a href="OrganizerHome.jsp">Home</a></li>
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
						<p class="post-info">Here are the details of the User you can update if you want</p>
					</footer>
					<content>
					<form action="UpdateUserDetails" method="post">
				<label><b>User Id:</b></label><br>
							<input type = "text"  value="<%=request.getAttribute("userId")%>" placeholder="userid" name="userId" readonly/>
				<p></p>
				<p></p>
				<label><b>Full Name:</b></label><br>
							<input type = "text" value="<%=request.getAttribute("fullname")%>"  placeholder= "Name" name="fullname" required/> <br>
				<p></p>
				<p></p>
				<label><b>Date Of Birth</b></label><br>
							<input type = "date" value="<%=request.getAttribute("dob")%>" id = "myText" placeholder="yyyy-mm-dd" name="dob" required/> <br>
				<p></p>
				<p></p>
				<label><b>Mobile:</b></label><br>
							<input type = "tel" value="<%=request.getAttribute("phone")%>" id = "myText" placeholder="995563215" name="phone" required />
				<p></p>
				<p></p>
				<label><b>Email:</b>(optional)</label><br>
							<input type = "email" value="<%=request.getAttribute("email")%>" id = "myText" placeholder="xyz@123.com" name="email" required />
				<p></p>
				<p></p>
			   <label><b>Address:</b></label><br>
							<input type = "text" value="<%=request.getAttribute("address")%>" id = "myText" placeholder="xyz@123.com" name="address" required />
			    <p></p>
				<p></p>
				<input type="submit" name="button" value="Update" />
				<input type="submit" name="button" value="Delete" />
			   </form>
			      <br>
			      <h3>User involved Chits </h3>
			      <footer>
						<p class="post-info">Here are the chits in which this user is involved select one of the chit to check that chit details</p>
					</footer>
					
					<form action="DisplayUserPaidDetails" method="post">
			      <select name="userinvolved" required>
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
			      <p></p>
				<p></p>
			      <label><b>This Month amount paid:</b></label><br>
			 
							<input type = "number"  id = "myText" placeholder="this month amount" name="monthamount" required />
			    <p></p>
			      <p></p>
			      <input type="submit" name="button" value="Submit">
			      <input type="submit" name="button" value="Update">
			      
			      <br>
			      <p></p>
				<p></p>
					
							
							<h3>Details of the above selected chit</h3>
			      <label><b>Paid Amount:</b></label><br>
							<input type = "number" value ="<%=request.getAttribute("paidamount")%>"  id = "myText" placeholder="amount paid" name="paidamount" readonly />
				<p></p>
				<p></p>
			   <label><b>Remaining Months:</b></label><br>
							<input type = "number" value ="<%=request.getAttribute("monthsleft")%>" id = "myText" placeholder="months remaining" name="monthsleft" readonly />
			    <p></p>
				<p></p>
				</form>
	
		</content>
		</article>
					
				
			</div>
			
			<aside class="top-sidebar">
			<form action="ChitAssigning" method="post">
				<h2><b>List of Available Chits</b></h2>
				 <select name="availablechitlist" required>
			      <option value="" style="display:none">Please Select a chit</option>
											<%
						while(chitSet.next()){ %>
			            <TR>
			                <option> <%= chitSet.getString(1)%></option>
			               
			            </TR>
			            <% }
											%>
			      </select>
				
				<footer>
						<p class="post-info">Select one chit to assign to this user</p>
					</footer>	
				<input type="submit" value="Assign">	
				</form>
				</aside>
				
				<aside class="middle-sidebar">
				<h2><b>Your Options</b></h2>
				<li><a href="Organizer.jsp"><b>Register New User</b></a></li>
				<li><a href="NewChit.jsp"><b>Create New Chit</b></a></li>
				
				<footer>
						<p class="post-info">Select any option to check the details</p>
					</footer>
				
				</aside>
				
				<aside class="bottom-sidebar">
					<article>
						<header>
						<h2>Documents Submitted</h2>
						</header>
						<img src="photo.jpg" height="225px" width="200px" name ="photo" id="photo id">
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

