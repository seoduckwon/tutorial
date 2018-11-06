package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdf.service.EmployeesService;
/**
 * Servlet implementation class EmployeesServlet
 */
public class EmployeesServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;

	   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String subPath = request.getRequestURI().substring(request.getContextPath().length());
	      System.out.println(subPath);
	      if("/employees/login.do".equals(subPath)) {
	         login(request,response);
	      }else if("/employees/findid.do".equals(subPath)) {
	         
	      }else if("/employees/findpwd.do".equals(subPath)) {
	         
	      }
	      
	   }
	   
	private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	      String id = request.getParameter("id");
	      String pwd = request.getParameter("pwd");
	      EmployeesService service = EmployeesService.getInstance();
	      service.login(id, pwd);
	      
	 }


}
