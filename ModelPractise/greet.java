import java.util.Date;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;


public class greet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        Connection con;
        Statement st;
        ResultSet rs;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try{
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "Sun@0097");
                try{
                    String srch="SELECT * FROM mvcinfo WHERE name='"+(String)req.getParameter("name")+"';";
                    st=con.createStatement();
                    rs=st.executeQuery(srch);
                    if(rs.next())
                    {
                        SimpleDateFormat day= new SimpleDateFormat("dd");
                        SimpleDateFormat month= new SimpleDateFormat("MM");
                        Date today = new Date();
                        try{
                            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(2));
                            if(day.format(today).equals(day.format(dob)))
                            {
                                if(month.format(today).equals(month.format(dob)))
                                {
                                    pw.println("<p>Happy Birthday "+rs.getString(1)+"</p>");
                                }
                                else
                                    pw.println("<p>Hello "+rs.getString(1)+"</p>");
                            }
                            else
                                pw.println("<p>Hello "+rs.getString(1)+"</p>");
                        }
                        catch(ParseException e)
                        {
                            pw.println("<script>");
                            pw.println("alert('parse error');");
                            pw.println("location='http://localhost:8080/ModelPractise/mvc.html'");
                            pw.println("</script>");
                        }
                    }
                }
                catch(SQLException e)
                {
                    pw.println("<script>");
                    pw.println("alert('error');");
                    pw.println("location='http://localhost:8080/ModelPractise/mvc.html'");
                    pw.println("</script>");
                }
            }
            catch(SQLException e)
            {
                pw.println("<script>");
                pw.println("alert('error2');");
                pw.println("location='http://localhost:8080/ModelPractise/mvc.html'");
                pw.println("</script>");
            }
        }
        catch(ClassNotFoundException e)
        {
            pw.println("<script>");
            pw.println("alert('error3');");
            pw.println("location='http://localhost:8080/ModelPractise/mvc.html'");
            pw.println("</script>");
        }
        pw.close();
    }
}