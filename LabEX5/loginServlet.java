import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class loginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        int flag=0;
        Connection con;
        PreparedStatement ps;
        Statement st;
        CallableStatement cs;
        ResultSet rs;        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try
            {
                flag=1;
                con =DriverManager.getConnection("jdbc:mysql://localhost:3306/profile","root","Sun@0097");
                
                try
                {
                    String srch = "SELECT name,pass FROM data WHERE name=\""+(String)request.getParameter("name")+"\" and pass=\""+(String)request.getParameter("pass")+"\";";
                    st=con.createStatement();
                    rs=st.executeQuery(srch);
                    if(rs.next()){
                        HttpSession ses=request.getSession(true);
                        ses.setAttribute("uname",(String)request.getParameter("name"));
                        pw.println("<html>");
                        pw.println("<head>");
                        pw.println("<link rel=\"stylesheet\" href=\"viewstyle.css\">");
                        pw.println("</head>");
                        pw.println("<body>");
                        pw.println("<div class=\"formblock centre\">");
                        pw.println("<h1>  Welcome "+(String)request.getParameter("name")+"</h1><br><br>");
                        pw.println("<script>");
                        pw.println("alert(\"login Successfully \");");
                        pw.println("</script>");
                        pw.println("<button style=\"height:10%;width:auto;\" onclick='location.replace(\"./viewProfile\");'>view profile</button>");
                        pw.println("</div>");
                        pw.println("</body>");
                        pw.println("</html>");
                    }
                    else{
                        pw.println("<script>");
                        pw.println("alert(\"username/password invalid\");");
                        pw.println("location='http://localhost:8080/LabEX5/index.html';");
                        pw.println("</script>");
                    }
                }
                catch(SQLException e)
                {
                    pw.println("<script>");
                    pw.println("alert("+e+");");
                    pw.println("location='http://localhost:8080/LabEX5/index.html';");
                    pw.println("</script>");
                }
            }
            catch (SQLException e)
            {
                flag=2;
            }     
        }
        catch(ClassNotFoundException e)
        {
            flag=3; 
        }
        
        if(flag==2)
        {
            pw.println("<script>");
            pw.println("alert(\"Failed to establish connection.Try again Later. Error Code :1 \");");
            pw.println("location='http://localhost:8080/LabEX5/index.html';");
            pw.println("</script>");
        }
        else if(flag==3)
        {
            pw.println("<script>");
            pw.println("alert(\"Driver class not found.Error Code :0 \");");
            pw.println("location='http://localhost:8080/LabEX5/index.html';");
            pw.println("</script>");
        }
        pw.close();
    }

}