import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class trial extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try{
                con = DriverManager.getConnection("https://localhost:3306/patients","root","Sun@0097");
            }
            catch(SQLException e)
            {
                System.out.println("error");
            }
        }
        catch (ClassNotFoundException e1) {
            System.out.println("error");
        }
    }
}
