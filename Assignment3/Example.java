import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Example extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		response.setContentType("text/html");
		String mail = request.getParameter("mail");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<table border=\'1px\'>");
		pw.println("<thead>");
		pw.println("<tr><th>NAME</th><th>EMAIL ID</th></tr>");
		pw.println("</thead>");
		pw.println("<tbody>");
		pw.println("<tr><td>"+name+"</td>");
		pw.println("<td>"+mail+"</td></tr>");
		pw.println("</tbody>");
		pw.println("</table>");
		pw.close();
	}
	public void destroy()
	{
		
	}
}