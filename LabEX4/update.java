import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class update extends HttpServlet {
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
                    String srch = " SELECT * FROM Patient_Details WHERE ID="+(String)request.getParameter("id");
                    st=con.createStatement();
                    rs=st.executeQuery(srch);
                    if(rs.next())
                    {
                        pw.println("<link rel=\"stylesheet\" href=\"formstyle.css\">");
                        pw.println("<div class=\"formblock centre\" style=\"height:95%;\">");
                        pw.println("    <form class=\"centre\" name=\"insert\" action=\"./upd1\">");
                        pw.println("        <h1> UPDATE </h1>");
                        pw.println("        <input type=\"text\" name=\"id\" id=\"id\" value=\'"+(String)request.getParameter("id")+"\' readonly><br>");
                        pw.println("        <input type=\"text\" name=\"name\" id=\"name\" value=\'"+rs.getString(1)+"\' readonly><br>");
                        pw.println("        <input type=\"number\" name=\"age\" id=\"age\" value=\'"+rs.getString(2)+"\' ><br>");
                        pw.println("        <input type=\"text\" id=\"gender\" name=\"gender\" value=\'"+rs.getString(4)+"\' readonly><br>");
                        pw.println("        <textarea id=\"addr\" name=\"addr\" rows=\"4\" cols=\"50\" required> "+rs.getString(5)+" </textarea><br>");
                        pw.println("        <input type=\"text\" id=\"ms\" name=\"ms\" value=\'"+rs.getString(6)+"\' readonly><br>");
                        pw.println("        <label for=\"dov\">Date of Visit:</label>");
                        pw.println("        <input type=\"date\" id=\"dov\" name=\"dov\" value=\'"+rs.getString(7)+"\' readonly><br>");
                        pw.println("        <input style=\"float:left;width:33%;\" type=\"submit\" value=\"Update\">");
                        pw.println("        <input style=\"float:left;width:32%;\" type=\"button\" value=\"View\" onclick='window.location.href=\"http://localhost:8080/LabEX4/view\";'>");
                        pw.println("        <input style=\"float:left;width:32%;margin:5px 3px;\" type=\"button\" value=\"Home\" onclick='window.location.href=\"http://localhost:8080/LabEX4/home.html\";'>");
                        pw.println("    </form>");
                        pw.println("</div>");
                        /*pw.println("<form name=\"insert\" action=\"./upd1\">");
                        pw.println("<input type=\"number\" id='id' name='id' style='display:hidden;' value=\'"+(String)request.getParameter("id")+"\'>");
                        pw.println("    <label>Name:</label>");
                        pw.println("    <input type=\"text\" name=\"name\" id=\"name\" value=\'"+rs.getString(1)+"\'><br><br>");
                        pw.println("    <label>Age:</label>");
                        pw.println("    <input type=\"number\" name=\"age\" id=\"age\" value=\'"+rs.getString(2)+"\'><br><br>");
                        pw.println("    <label>Gender:</label>");
                        pw.println("    <input type=\"radio\" id=\"male\" name=\"gender\" value=\"male\" required>");
                        pw.println("    <label for=\"male\">Male</label><br>");
                        pw.println("    <input type=\"radio\" id=\"female\" name=\"gender\" value=\"female\">");
                        pw.println("    <label for=\"female\">Female</label><br>");
                        pw.println("    <input type=\"radio\" id=\"other\" name=\"gender\" value=\"other\">");
                        pw.println("    <label for=\"other\">Other</label><br><br>");
                        pw.println("    <label for =\"addr\">Address:</label><br><br>");
                        pw.println("    <textarea id=\"addr\" name=\"addr\" rows=\"4\" cols=\"50\" required> "+rs.getString(5)+" </textarea><br><br>");
                        pw.println("    <label for=\"ms\">Marital Status:</label>");
                        pw.println("    <select name=\"ms\" id=\"ms\" value=\'"+rs.getString(6)+"\'required>");
                        pw.println("        <option value=\"Bachelor\">Bachelor</option>");
                        pw.println("        <option value=\"Married\">Married</option>");
                        pw.println("        <option value=\"Spinster\">Spinster</option>");
                        pw.println("        <option value=\"Widow\">Widow</option>");
                        pw.println("    </select><br><br>");
                        pw.println("    <label for=\"dov\">Date of Visit:</label>");
                        pw.println("    <input type=\"date\" id=\"dov\" name=\"dov\" value=\'"+rs.getString(7)+"\' required><br><br>");
                        pw.println("    <input type=\"submit\" value=\"Update\">");
                        pw.println("</form>");
                        pw.println("<script>");
                        pw.println("document.getElementById(\'"+rs.getString(4)+"\').checked=true;");
                        pw.println("</script>");*/
                    }
                    else
                    {
                        pw.println("<script>");
                        pw.println("alert(\"Not found \");");
                        pw.println("location='http://localhost:8080/LabEX4/search.html';");
                        pw.println("</script>");
                    }
                }
                catch(SQLException e)
                {
                    pw.println("<script>");
                    pw.println("alert(\"Could not Update data"+e+" \");");
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