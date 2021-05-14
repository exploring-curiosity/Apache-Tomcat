import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Home extends HttpServlet {  
    
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        int flag=0;
        Connection con;        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try
            {
                flag=1;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/patient","root","Sun@0097");
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
            pw.println("location='http://localhost:8080/LabEX4/login.html';");
            pw.println("</script>");
        }
        else if(flag==3)
        {
            pw.println("<script>");
            pw.println("alert(\"Driver class not found.Error Code :0 \");");
            pw.println("location='http://localhost:8080/LabEX4/login.html';");
            pw.println("</script>");
        }
        else
        {
            pw.println("<script>");
            pw.println("alert(\"Successfully established connection. \");");
            pw.println("location='http://localhost:8080/LabEX4/home.html';");
            pw.println("</script>");
        }
    }
}
