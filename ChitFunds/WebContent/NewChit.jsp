<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ page import="java.sql.*"
     			import="java.io.*"  %>

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
	            ResultSet 
	               userSet= statement.executeQuery("select userId from registration") ;
	            	 ResultSet chitSet=statement1.executeQuery("select chitname from newchit");
	            	 
	            	
	            	 %>
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Chit</title>
	<link rel="stylesheet" href="style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="body">
		<img src="image 3.jpg" height="250" width="1000">
	<header class = "mainHeader">
	<nav><ul>
		<li><a href="OrganizerHome.jsp">Home</a></li>
		<li><a href="AboutUs.html">About Us</a></li>
		<li><a href="ContactUs.html">Contact Us</a></li>
		<li><a href="LogoutServlet">Sign Out</a></li>
		
			</ul></nav>
			</header>
			
			
			<script type="text/javascript">
        
        function postRequest1(strURL)
		{
				var xmlHttp;
				if(window.XMLHttpRequest)
					{ // For Mozilla, Safari, ...
					var xmlHttp = new XMLHttpRequest();
					}
				else if(window.ActiveXObject)
					{ // For Internet Explorer
					var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
				xmlHttp.open('GET', strURL, true);
				xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				xmlHttp.onreadystatechange = function()
				{
				if (xmlHttp.readyState == 4)
					{
					//document.lg.sug.value = xmlHttp.responseText; 
					document.getElementById("avail").innerHTML = xmlHttp.responseText; 
					}
				}
			xmlHttp.send(strURL);
		}
        
        
        function c1(b2){
		//alert("hiii");
		 var b=b2;//document.forms["jg"]["bd"].value;
		// alert(b);
		var url = "CheckingChitName.jsp" 
		
		url = url+"?name="+b;
			
		postRequest1(url);
	}
        
        
        
        </script>
			
			
			
			
			<div class = "mainContent">
				<div class = "content">
					<article class = "topcontent">
					<header>
						<h2><b>Creating a New Chit</b></h2>
					</header>
										
					<content>
						<form action="NewChitServlet" method="post">
						
							<label><b>Name of the Chit:</b></label><br>
							<input type = "text"  id = "chitname" onkeyup="c1(this.value)" placeholder="Chit Name" name="chitname" required/>  <br>
							<td><div id = "avail"></div></td>
							<p></p>
							<p></p>
							
							<label><b>Gross Amount:</b></label><br>
							<input type = "number" id = "myText" value="Set Focus" placeholder="Amount" name="chitamount" required /> <br>
							<p></p>
							<p></p>
							<script type="text/javascript">
								function withdrawl(amount){
									var val=  document.getElementById('chitamount').value;
									var val1 =  document.getElementById('minimumamount').value;
									System.out.println("val = "+val);
									System.out.println("val 1 = "+val1);
									if(val1<val){
										return true;
									}else{
										alert("please enter a valid amount that should be less than gross amount");  
										return false;
									}
										
								}
							
							</script>
							
							<label><b>Minimum Withdrawl Amount</b></label><br>
							<input type = "number" onchange="withdrawl(this)" id = "myText" value="Set Focus" placeholder="minimum amount" name="minimumamount" required />
							
							
							<p></p>
							<p></p>
							
							<script type="text/javascript">
				            function update(elem) { 
				                document.getElementById('textareaDescription').value =  elem.value ;  
				            }
				       		 </script>
							
							
							<label><b>Customers Limit:</b></label><br>
							<input type = "number" onchange="update(this)" value="Set Focus"  id = "myText" placeholder="Limit" name="customerlimit" required /><br>
							<p></p>
							<p></p>
						
							
							<label><b>Chit Span</b></label><br>
							<input type = "number"  id = "textareaDescription" value="Set Focus" placeholder="Span" name="chitspan" readonly />
							<p></p>
							<p></p>
												
							<input type="submit" value="Create Chit" />
							
							</form>

					
				<!-- selecting a user to change or updating the details -->						
						
					<form action="DisplayChitDetails" method="post">							
					
						<h2><b>List Of Chits </b></h2>
						
						<select name="chitlist" required>
						<option value="" style="display:none">Please Select a Chit</option><br>			
						<%
						while(chitSet.next()){ %>
			            <TR>
			                <option> <%= chitSet.getString(1)%></option>
			               
			            </TR>
			            <% }
											%>								
						</select>
						
						<footer>
						<p class="post-info">Select one of the chits in the above list</p>
					</footer>
						<br>
						<input type="submit" name="button" value="View" />
						<input type="submit" name="button" value="Delete" />
						</form>
		
		</article>
		</div>
		
<!-- end of chit details -->
			
				<aside class="top-sidebar">
					<article>
					<h2>Create a New Chit</h2>
					<li><a href="#"><b>New Chit</b></a></li>
					<li><a href="Organizer.jsp"><b>Create User</b></a></li>
					<footer>
						<p class="post-info">Click new chit above to create your new Chit</p>
					</footer>
				    </article>
				</aside>
				
				<!-- selecting a user to change or updating the details -->			
				
				<aside class="bottom-sidebar">
					<article>
					<form action="DisplayUserDetails" method="post">
					<h2><b>List of Users</b></h2>
					<select name="userlist" required>
						<option value="" style="display:none">Please Select a User</option>
						<% 
						while(userSet.next()){ %>
			            <TR>
			                <option> <%= userSet.getString(1)%></option>
			               
			            </TR>
			            <% }
						%>
											
						</select>
						
<!-- end of user details -->	
					
						<footer>
						<p class="post-info">Select a user in the above list to view his details</p>
					</footer>
					
						<input type="submit" name="button" value="View" />
						<input type="submit" name="button" value="Delete" />
						
						</form>
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


