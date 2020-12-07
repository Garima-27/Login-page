import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	static Connection con=DBUtil.getDBConnection();
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String user=req.getParameter("uname");
		String pswd=req.getParameter("pwd");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			PreparedStatement ps=con.prepareStatement("select * from login where username=?");
			ps.setString(1, user);
			ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					if(rs.getString("password").equals(pswd))
					{
						RequestDispatcher rd=req.getRequestDispatcher("display");
						rd.forward(req,res);
					}
					else
					{
						
						pw.println("<script> alert('Incorrect Password');</script>");
						RequestDispatcher rd=req.getRequestDispatcher("login.html");
						rd.include(req, res);
					}
				}
				else
				{
					pw.println("<script> alert('Username doesnot exist');</script>");
					RequestDispatcher rd=req.getRequestDispatcher("login.html");
					rd.include(req, res);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
