import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class Display extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		int t=0;
		String username=req.getParameter("uname");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		DBUtil db=new DBUtil();
		Connection con=db.getDBConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from profile where username=?");
			ps.setString(1,username);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				pw.println("<html><body background='bg.jpg'>");
				pw.println("<center style='padding-top:100px'><font color='white'><h1>Welcome "+rs.getString(2)+"!</h1></font></center>");
				pw.println("<center style='padding-top:70px'>");
				pw.println("<font color='white' size=5>");
				pw.println("Username  :- "+rs.getString(1)+"<br>");
				pw.println("Name  :- "+rs.getString(2)+"<br>");
				pw.println("Gender  :- "+rs.getString(3)+"<br>");
				pw.println("Age  :- "+rs.getInt(4)+"<br>");
				pw.println("Occupation  :- "+rs.getString(5)+"<br>");
				pw.println("Salary  :- "+rs.getInt(6)+"<br>");
			    pw.println("<font>");
				pw.println("</center>");
			}
			else
			{
				pw.println("<script> alert('Data not found');</script>");
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, res);
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
