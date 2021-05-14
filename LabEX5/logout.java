import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class logout extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        HttpSession ses = request.getSession();
        PrintWriter pw=response.getWriter();
        ses.invalidate();
        pw.println("<script>");
        pw.println("alert(\"Logged out Successfully\");");
        pw.println("location='http://localhost:8080/LabEX5/index.html';");
        pw.println("</script>");
    }
}