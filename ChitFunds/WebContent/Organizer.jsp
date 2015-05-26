<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
    <%@ page import="java.sql.*" import="java.io.*"%>

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
	            ResultSet userSet= statement.executeQuery("select userId from registration") ;
	            	 ResultSet chitSet=statement1.executeQuery("select chitname from newchit");
	            	 %>


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Organizer</title>
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
			
			
			<script type="text/JavaScript">
            function afterfullname(name) { 
				var data = name.value;
				if(data==""){
					alert("Enter your full name");
				}
				else{
					document.getElementById("fullname").setAttribute("title",fullname);
					document.location.href="CheckingFullName";
					document.getElementById("fullname").value="";
				}
					
            }
        </script>
        
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
        function postRequest2(strURL)
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
					document.getElementById("availmobile").innerHTML = xmlHttp.responseText; 
					}
				}
			xmlHttp.send(strURL);
		}
        function postRequest3(strURL)
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
					document.getElementById("availemail").innerHTML = xmlHttp.responseText; 
					}
				}
			xmlHttp.send(strURL);
		}
        function c1(b2){
			//alert("hiii");
			 var b=b2;//document.forms["jg"]["bd"].value;
			// alert(b);
			var url = "CheckingFullName.jsp" 
			
			url = url+"?name="+b;
				
			postRequest1(url);
			
		}
        
        function c2(b2){
		//alert("hiii");
		 var b=b2;//document.forms["jg"]["bd"].value;
		// alert(b);
		var url = "CheckingMobile.jsp" 
		
		url = url+"?name="+b;
			
		postRequest2(url);
	}
        
        function c3(b2){
		//alert("hiii");
		 var b=b2;//document.forms["jg"]["bd"].value;
		// alert(b);
		var url = "CheckingEmail.jsp" 
		
		url = url+"?name="+b;
			
		postRequest3(url);
		
	}
        </script>	
			
			
			
			<div class = "mainContent">
				<div class = "content">
					<article class = "topcontent">
					<header>
						<h2><b>Registration</b></h2>
					</header>
						<form action="UserRegisterServlet" method="post">
							
							<label><b>Full Name:</b></label><br>
							<input type = "text"  id = fullname onkeyup="c1(this.value)" placeholder= "Name" name="fullname" required/> <br>
							<td><div id = "avail"></div></td>
							
							
							<p></p>
							<p></p>
							
							<label><b>Date Of Birth</b></label><br>
							<input type = "date" id = "dob" placeholder="yyyy-mm-dd" name="dob" required/> <br>
							
							<p></p>
							<p></p>
							
							<label><b>Mobile:</b></label><br>
							<input type = "tel" onchange="phonenumber(this)" onkeyup="c2(this.value)"  id = "myText" placeholder="9955632156" name="phone" required/>
							<td><div id = "availmobile"></div></td>
							<script>
							function phonenumber(inputtxt)  
								{  
								  var phoneno = /^\d{10}$/;  
								  if((inputtxt.value.match(phoneno)))  
										{  
									  return true;  
										}  
									  else  
										{  
										alert("please enter a valid phone number");  
										return false;  
										}  
								} 
							</script>
							<p></p>
							<p></p>
							
							
							<script>
							function ValidateEmail(mail)   
							{  
							 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.emailAddr.value))  
							  {  
							    return true;  
							  }
							 else{
							    alert("You have entered an invalid email address!")  
							    return false;
							 }
							}
							</script>
							
							<label><b>Email:</b>(optional)</label><br>
							<input type = "email" onchange="ValidateEmail(this)" onkeyup="c3(this.value)" id = "myText" placeholder="xyz@123.com" name="email"/>
							<td><div id = "availemail"></div></td>
							<p></p>
							<p></p>
							 <b>Address:</b><br><textarea rows="7" cols="50" name="address" placeholder="house no,street name, place,state,pincode " ></textarea>
							
							<p></p>
							<p></p>
							<label><b>Documents:</label></b><br>
							<input type = "checkbox" id="voter" name="document" value="Voter Id">Voter Id
							<div class="form-group" id="file-input-voter" style="display:none;">
							<label for="exampleInputFile">Choose document</label>
							<input type="file" id="exampleInputFile">
							</div>
							<script>
							document.getElementById('voter').onchange = function(){
							   if(this.checked) {document.getElementById('file-input-voter').style.display='block';
							   //more js statements
							   }
							   else {document.getElementById('file-input-voter').style.display='none';
							   //more js statements
							   }
							}
							</script>
							<br>
							
							
							
							<input type = "checkbox" id="aadhar" name="document" value="Aadhar Id">Aadhar Id
							<div class="form-group" id="file-input-aadhar" style="display:none;">
							<label for="exampleInputFile">Choose document</label>
							<input type="file" id="exampleInputFile">
							
							</div>
							<script>
							document.getElementById('aadhar').onchange = function(){
							   if(this.checked) {document.getElementById('file-input-aadhar').style.display='block';
							   //more js statements
							   }
							   else {document.getElementById('file-input-aadhar').style.display='none';
							   //more js statements
							   }
							}
							</script>
							<br>
							
							
							
							<input type = "checkbox" id="driving" name="document" value="Driving License">Driving License
							<div class="form-group" id="file-input-driving" style="display:none;">
							<label for="exampleInputFile">Choose document</label>
							<input type="file" id="exampleInputFile">
							</div>
							<script>
							document.getElementById('driving').onchange = function(){
							   if(this.checked) {document.getElementById('file-input-driving').style.display='block';
							   //more js statements
							   }
							   else {document.getElementById('file-input-driving').style.display='none';
							   //more js statements
							   }
							}
							</script>
							<br>
							
							
							
							<input type = "checkbox" id="pan" name="document" value="Pan Card">Pan Card
							<div class="form-group" id="file-input-pan" style="display:none;">
							<label for="exampleInputFile">Choose document</label>
							<input type="file" id="exampleInputFile">
							</div>
							<script>
							document.getElementById('pan').onchange = function(){
							   if(this.checked) {document.getElementById('file-input-pan').style.display='block';
							   //more js statements
							   }
							   else {document.getElementById('file-input-pan').style.display='none';
							   //more js statements
							   }
							}
							</script>
							<br>
							
							
							
							<input type = "checkbox" id="bank" name="document" value="Bank Account">Bank Account
							<div class="form-group" id="file-input-bank" style="display:none;">
							<label for="exampleInputFile">Choose document</label>
							<input type="file" id="exampleInputFile">
							</div>
							<script>
							document.getElementById('bank').onchange = function(){
							   if(this.checked) {document.getElementById('file-input-bank').style.display='block';
							   //more js statements
							   }
							   else {document.getElementById('file-input-bank').style.display='none';
							   //more js statements
							   }
							}
							
							</script>
							
							<p></p>
							<p></p>							
							<input type="submit" value="Create User"/>
							</form>
							<p></p>
							<p></p>
						
						
<!-- selecting a chit to change or updating the details -->
					<form action="DisplayChitDetails" method="post">							
					<header>
						<h2><b>List Of Chits </b></h2>
						</header>
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

	
		
<!-- To create a new chit  -->	
		
				<aside class="top-sidebar">
					<article>
					<header>
					<h2><b>Create a New Chit</b></h2>
					</header>
					<li><a href="NewChit.jsp"><b>New Chit</b></a></li>
					<li><a href="OrganizerHome.jsp"><b>Organizer Home</b></a></li>
					<footer>
						<p class="post-info">Click <b>new chit</b> above to create your new Chit</p>
					</footer>
				    </article>
				</aside>
<!-- end of chit details -->		
				
				
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
				
				
				
	
<!-- main footer -->			
	
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

