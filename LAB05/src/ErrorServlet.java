import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Error",urlPatterns = "/Error")
public class ErrorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>注册信息输入有误</title>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"SignIn\" method=\"post\" class=\"container\">");
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
        if(firstName.trim().length()==0){getContent(out,"first name","firstName");}else{getPri(out,firstName,"first name","firstName");}
        if(lastName.trim().length()==0){getContent(out,"last name","lastName");}else{getPri(out,lastName,"last name","lastName");}
        if(email.trim().length()==0){getContent(out,"email address","email");}else{getPri(out,email,"email address","email");}
        out.println("<input type=\"submit\" class=\"col-sm-2\" value=\"submit\">");
        out.println("</form>");
        out.println("<script src=\"https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js\"></script>");
        out.println("<script src=\"https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js\"></script>");
        out.println("<script src=\"https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js\"></script>");
        out.println("</body>");
        out.println("</html>");

    }
    private void getContent(PrintWriter out,String var,String col){
        out.println("<label class=\"row\" style=\"color:red;\"><span class=\"col-sm-4\">You must enter "+var+" : </span><input name="+col+" type=\"text\" class=\"col-sm-8\"></label>");
    }
    private  void getPri(PrintWriter out,String var,String sname,String col){
        out.println("<label class=\"row\"><span class=\"col-sm-4\">"+sname+" :</span><input name="+col+" type=\"text\" class=\"col-sm-8\" value="+var+"></label>");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request,response);
    }
}
