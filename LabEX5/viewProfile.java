import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class viewProfile extends HttpServlet {
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
                    HttpSession ses=request.getSession(false);
                    String srch = " SELECT * FROM data WHERE name=\""+(String)ses.getAttribute("uname")+"\";";
                    st=con.createStatement();
                    rs=st.executeQuery(srch);
                    if(rs.next()){
                        pw.println("<link rel=\"stylesheet\" href=\"viewstyle.css\">");
                        pw.println("<div class=\"formblock centre\">");
                        pw.println("<table border=\'1px\'>");
                        pw.println("<thead>");
                        pw.println("<tr><th>Attribute</th><th>Value</th></tr>");
                        pw.println("</thead>");
                        pw.println("<tbody>");
                        pw.println("<tr><td>Name</td><td>"+rs.getString(1)+"</td></tr>");
                        pw.println("<tr><td>Age</td><td>"+rs.getString(3)+"</td></tr>");
                        pw.println("<tr><td>Date Of Birth</td><td>"+rs.getString(4)+"</td></tr>");
                        pw.println("<tr><td>Address</td><td>"+rs.getString(5)+"</td></tr>");
                        pw.println("<tr><td>Mobile</td><td>"+rs.getString(6)+"</td></tr>");
                        pw.println("<tr><td>Occupation</td><td>"+rs.getString(7)+"</td></tr>");
                        pw.println("</tbody>");
                        pw.println("</table>");
                        pw.println("<button style=\"height:10%;\" onclick='location.replace(\"./logout\");'>logout</button>");
                        pw.println("</div>");
                    }
                    else{
                        pw.println("<script>");
                        pw.println("alert(\"Could not load data\");");
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