import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class grosscal extends HttpServlet
{
	public float calgross(float sal, String desig)
	{
		if(desig.equalsIgnoreCase("Manager"))
			return sal+5000;
		else if(desig.equalsIgnoreCase("Consultant"))
			return sal+3000;
		else if(desig.equalsIgnoreCase("developer"))
			return sal+2000;
		else 
			return sal+1000;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eid = request.getParameter("eid");
		String ename = request.getParameter("ename");
		String desig = request.getParameter("desig");
		String dept = request.getParameter("dept");
		String sal = request.getParameter("sal");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String ms = request.getParameter("ms");
		float s=Float.parseFloat(sal);
		float gross = calgross(s,desig);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<link rel=\"stylesheet\" href=\"style.css\">");
		pw.println("<table border=\'1px\'>");
		pw.println("<thead>");
		pw.println("<tr><th>Attribute</th><th>Value</th></tr>");
		pw.println("</thead>");
		pw.println("<tbody>");
		pw.println("<tr><td>Name</td><td>"+ename+"</td></tr>");
		pw.println("<tr><td>ID</td><td>"+eid+"</td></tr>");
		pw.println("<tr><td>Designation</td><td>"+desig+"</td></tr>");
		pw.println("<tr><td>Department</td><td>"+dept+"</td></tr>");
		pw.println("<tr><td>Salary</td><td>"+sal+"</td></tr>");
		pw.println("<tr><td>Phone Number</td><td>"+phone+"</td></tr>");
		pw.println("<tr><td>Address</td><td>"+addr+"</td></tr>");
		pw.println("<tr><td>Date of Birth</td><td>"+dob+"</td></tr>");
		pw.println("<tr><td>Gender</td><td>"+gender+"</td></tr>");
		pw.println("<tr><td>Marital Status</td><td>"+ms+"</td></tr>");
		pw.println("<tr><td>Gross Pay</td><td>"+gross+"</td></tr>");
		pw.println("</tbody>");
		pw.println("</table>");
		pw.close();
	}
	public void destroy()
	{
		
	}
}