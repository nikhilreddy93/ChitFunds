<%@page language="java" import="java.io.*,java.sql.*,javax.servlet.*,java.util.*" %>

	<%
	
		String url="jdbc:mysql://localhost:3306/chitfunds?user=root&password=root";	
        String b1=request.getParameter("name");
		String text="";
	
try
{		
		    String driver="com.mysql.jdbc.Driver";		
			Class.forName(driver).newInstance();  			
		    Connection con=DriverManager.getConnection(url);
			String s = "";
			Statement st=con.createStatement();		
			
			ResultSet rs = st.executeQuery("select * from registration where fullname = '"+b1+"'");
			int count = 0;
			 while (rs.next())
				 {
				 out.println(rs.getString(2));
					count++;				
				 }
				out.println("<html><body>");
				 
			if(count == 1)
			{
				out.println("<font color = red>Already Exists</font>");
				request.setAttribute("fullname", text);
					  
			}
					 
			else
			{
					out.println("<font color = green>Available</font>");	

			}
 out.println("</body><html>");
 			rs.close();
 			st.close();
			con.close();
}

catch(Exception e)
		{
                       System.out.println(e.getMessage());
        }%>


