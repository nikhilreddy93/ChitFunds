<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*"
     import="java.io.*" %>

<% Class.forName("com.mysql.jdbc.Driver"); %>

	<% 
	PrintWriter writer = response.getWriter();
	HttpSession session1=request.getSession(false); 
    String Name=(String)session1.getAttribute("Name");
    System.out.println("session in register = "+session1.getId());
    if(Name!=null){ 
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chitfunds","root","root");
	            Statement statement = connection.createStatement() ;
	            Statement statement1 = connection.createStatement() ;
	            Object value=request.getAttribute("chitname");
	            System.out.println("value="+value);
	            
	            ResultSet 
	               userSet= statement.executeQuery("select userId from chitassigning where chitname='"+value+"'") ;
	            ResultSet chitSet=statement1.executeQuery("select chitname from newchit");
	            //ResultSet users= statement.executeQuery("select userId from registration") ;
	             
	      
	            	 %>

    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chit</title>
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
			
			
			<script type="text/JavaScript">
            function aftercommission() { 
				var data = document.getElementById("monthamount").value;
				if(data==""){
					alert("Enter this month amuount");
				}
				else{
					document.location.href="MainLogic";
				}
					
            }
        </script>
        
        <script type="text/javascript">
				            function update(elem) { 
				                document.getElementById('textareaDescription').value =  elem.value ;  
				            }
				            
				            function amountCheck(amount){
				            	var data = document.getElementById("minimumamount").value;
				            	var data1 = document.getElementById("grossamount").value;
				            	if(amount<data||amount>data1){
				            		alert("Please enter the amount greater than minimum withdrawl amount");
				            		document.getElementById("monthamount").value="";
				            	}
				            }
				            
				            
				            
				       		 </script>
			
			<div class = "mainContent">
				<div class = "content">
					<article class = "topcontent">
					<header>
						<h2>Chit Details</h2>
					</header>
					<content>
					<form action="UpdateChitDetails" method="post">
				<label><b>Chit Name:</b></label><br>
							<input type = "text"  value="<%=request.getAttribute("chitname")%>" placeholder="userid" name="chitname" readonly/>
				<p></p>
				<p></p>
				<label><b>Gross Amount</b></label><br>
							<input type = "number" id="grossamount" value="<%=request.getAttribute("chitamount")%>"  placeholder= "Name" name="chitamount" required/> <br>
				<p></p>
				<p></p>
				<label><b>Minimum Withdrawl Amount</b></label><br>
							<input type = "number" id="minimumamount" value="<%=request.getAttribute("minimumamount")%>" id = "myText" placeholder="minimumamount" name="minimumamount" required/> <br>
				<p></p>
				<p></p>
				
				
				<label><b>Customers Limit</b></label><br>
							<input type = "number" onchange="update(this)"  value="<%=request.getAttribute("customerlimit")%>" id = "myText" placeholder="customerlimit" name="customerlimit" required/>
				<p></p>
				<p></p>
				<label><b>Chit Span:</b></label><br>
							<input type = "number" id = "textareaDescription"  value="<%=request.getAttribute("chitspan")%>" id = "myText" placeholder="chitspan" name="chitspan" readonly />
	
			    <p></p>
				<p></p>
				<input type="submit" name="button" value="Update" />
				<input type="submit" name="button" value="Delete" />
			      <br>
			      
			      <h2>Money Details</h2>
			      <label><b>This Month Chit Amount</b></label><br>
							<input type = "number" onchange="amountCheck(this.value)" id = "monthamount"  placeholder="monthamount" name="monthamount" required />
	
			    <p></p>
				<p></p>
				<label><b>Your Commission</b></label><br>
							<input type = "number" id = "commission" placeholder="commission in percentage" name="commission" required />
				<p></p>
				<p></p>
					<input type="submit" name="button" value="Submit" />
			    <p></p>
				<p></p>
				<label><b>User to pay</b></label><br>
							<input type = "number" id = "userpay" placeholder="chitspan" name="usertopay" readonly="readonly" />
	
			    <p></p>
				<p></p>
				<label><b>Commission Amount</b></label><br>
							<input type = "number" id = "commissionAmount" placeholder="amount you get" name="commissionAmount" readonly="readonly"/>
	
				<p></p>
				<p></p>
				<label><b>No of Months left</b></label><br>
							<input type = "number" id = "monthsleft" placeholder="chitspan" name="monthsleft" readonly="readonly"/>
	
			    <p></p>
				<p></p>
				</form>
			    </div>  
			      
			      <aside class="middle-sidebar">
			      <form action="DisplayUserDetails" method="post">
			      <h2>Users in this chit</h2>
			      <select name="userlist" required>
			      <option value="" style="display:none">List of Users</option><br>
			      
			      	<%
						while(userSet.next()){ %>
			            <TR>
			                <option> <%= userSet.getString(1)%></option>
			               
			            </TR>
			            <% }
						%>
			     
			      </select>
			      <footer>
						<p class="post-info">Here are the users involved in this chit. Select any user to view his details</p>
					</footer>
			      <input type="submit" name="button" value="View" />
			      </form>
			      
		</content>
		</article>
			</aside>		
				
			
			
				<aside class="top-sidebar">
					<article>
					<header>
					<h2><b>Create a New Chit</b></h2>
					</header>
					<li><a href="OrganizerHome.jsp"><b>Organizer Home</b></a></li>
					<li><a href="NewChit.jsp"><b>New Chit</b></a></li>
					<footer>
						<p class="post-info">Click <b>new chit</b> above to create your new Chit</p>
					</footer>
				    </article>
				</aside>
				

				
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

