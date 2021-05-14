import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class getdata extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        int flag=0;
        Connection con;
        Statement st;
        ResultSet rs;      
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try
            {
                flag=1;
                con =DriverManager.getConnection("jdbc:mysql://localhost:3306/countries","root","Sun@0097");
                try
                {
                    String srch = " SELECT Name FROM Country WHERE name LIKE '"+(String)request.getParameter("country")+"%';";
                    st=con.createStatement();
                    rs=st.executeQuery(srch);
                    while(rs.next()){
                        response.getWriter().write("<option>"+rs.getString(1)+"</option>");
                    }
                }
                catch(SQLException e)
                {
                    response.getWriter().write("error");
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
            response.getWriter().write("error");
        }
        else if(flag==3)
        {
            response.getWriter().write("error"); 
        }
    }
}