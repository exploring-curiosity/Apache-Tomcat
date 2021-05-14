import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Insert extends HttpServlet {
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
                    String insert = "INSERT INTO Patient_Details(Name,Age,ID,gender,address,marital_status,DateOfVisit) VALUES(?,?,?,?,?,?,?)";
                    ps=con.prepareStatement(insert);
                    ps.setString(1,request.getParameter("name"));
                    ps.setInt(2,Integer.parseInt(request.getParameter("age")));
                    ps.setInt(3,Integer.parseInt(request.getParameter("id")));
                    ps.setString(4,request.getParameter("gender"));
                    ps.setString(5,request.getParameter("addr"));
                    ps.setString(6,request.getParameter("ms"));
                    ps.setDate(7, java.sql.Date.valueOf(request.getParameter("dov")));
                    ps.execute();
                    pw.println("<script>");
                    pw.println("alert(\"Inserted Successfully \");");
                    pw.println("location='http://localhost:8080/LabEX4/insert.html';");
                    pw.println("</script>");
                }
                catch(SQLException e)
                {
                    pw.println("<script>");
                    pw.println("alert(\"Could not Insert data"+e+" \");");
                    pw.println("location='http://localhost:8080/LabEX4/insert.html';");
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