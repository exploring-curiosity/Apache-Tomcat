import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.script.*;

public class calculate extends HttpServlet
{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        ScriptEngineManager mgr= new ScriptEngineManager();
        ScriptEngine eng = mgr.getEngineByName("JavaScript");
        String res=(String)request.getParameter("res");
        try{
            String x=eng.eval(res).toString();
            pw.println("<h1>Result = "+x+"</h1><br>");
            pw.println("<input type='button' value='Go Back' onclick='window.location.href=\"v2.html\"'>");
        }
        catch(ScriptException e)
        {
            pw.println(e);
        }
        pw.close();
    }
}