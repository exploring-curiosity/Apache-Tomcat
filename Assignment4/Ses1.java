import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Ses1 extends HttpServlet{
public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
try{
	response.setContentType("text/html");
	PrintWriter pw = response.getWriter();
	HttpSession ses=request.getSession();
	
	String name=request.getParameter("name");
	if(ses.isNew())
	{
		pw.println("<p>new session:"+ses.getId()+" Name:"+name+"</p>");
		ses.setMaxInactiveInterval(10);
	}
	else
	{	
		pw.println("<p>old session:"+ses.getId()+" Name:"+name+"</p>");
	}
	pw.close();
}
catch(Exception e)
{
	System.out.println(e);
}
}
} 