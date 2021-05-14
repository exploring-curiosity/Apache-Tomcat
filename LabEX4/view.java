import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class view extends HttpServlet {
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
                con =DriverManager.getConnection("jdbc:mysql://localhost:3306/patient","root","Sun@0097");
                //Name, age, ID, gender, address, marital status, Date of Visit (Name,Age,ID,gender,address,marital_status,DateOfVisit)
                
                try
                {
                    String srch = " SELECT * FROM Patient_Details;";
                    st=con.createStatement();
                    rs=st.executeQuery(srch);
                    if(rs.next()){
                        pw.println("<link rel=\"stylesheet\" href=\"viewstyle.css\">");
                        pw.println("<div class=\"formblock centre\">");
                        pw.println("<table border=\'1px\'>");
                        pw.println("<thead>");
                        pw.println("<tr><th>Name</th><th>Age</th><th>ID</th><th>Gender</th><th>Address</th><th>Marital status</th><th>Date of Visit</th></tr>");
                        pw.println("</thead>");
                        pw.println("<tbody>");
                        do{
                            pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>"); 
                        }while(rs.next());
                        pw.println("</tbody>");
                        pw.println("</table>");
                        pw.println("<br><br><button style=\"height:10%;\" onclick=\'window.location.href=\"http://localhost:8080/LabEX4/home.html\";\'>Home</button>");
                        pw.println("</div>");
                        pw.println("<script>");
                        pw.println("alert(\"Found!! \");");
                        pw.println("</script>");
                    }
                    else
                    {
                        pw.println("<script>");
                        pw.println("alert(\"No data exist \");");
                        pw.println("location='http://localhost:8080/LabEX4/home.html';");
                        pw.println("</script>");
                    }
                }
                catch(SQLException e)
                {
                    pw.println("<script>");
                    pw.println("alert(\"Could not find data"+e+" \");");
                    pw.println("location='http://localhost:8080/LabEX4/home.html';");
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
            pw.println("location='http://localhost:8080/LabEX4/login.html';");
            pw.println("</script>");
            //response.sendRedirect("http://localhost:8080/LabEX4/login.html");
        }
        else if(flag==3)
        {
            pw.println("<script>");
            pw.println("alert(\"Driver class not found.Error Code :0 \");");
            pw.println("location='http://localhost:8080/LabEX4/login.html';");
            pw.println("</script>");
            //response.sendRedirect("http://localhost:8080/LabEX4/login.html");
        }
        pw.close();
    }
}