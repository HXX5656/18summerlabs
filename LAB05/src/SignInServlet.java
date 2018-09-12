import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="SignIn",urlPatterns = "/SignIn")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out =response.getWriter();
         String firstName=request.getParameter("firstName");
         String lastName=request.getParameter("lastName");
         String email=request.getParameter("email");
         if(firstName.trim().length()==0||lastName.trim().length()==0||email.trim().length()==0){
             response.sendRedirect("Error?firstName="+firstName+"&lastName="+lastName+"&email="+email);
         }
         else {
             Server.addUsers(firstName+"/./"+lastName,email);
             out.println("<!DOCTYPE html>");
             out.println("<html>");
             out.println("<head>");
             out.println("<meta charset=\"utf-8\">");
             out.println("<title>注册成功</title>");
             out.println("</head>");
             out.println("<body>");
             out.println("<h1>First Name:" + firstName + "</h1>");
             out.println("<h1>Last Name:" + lastName + "</h1>");
             out.println("<h1>Email Address:" + email + "</h1>");
             out.println("<a href=\"signUp.html\">登录");
             out.println("</body>");
             out.println("</html>");

         }
         
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                   doPost(request,response);
    }
}
