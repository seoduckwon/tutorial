package control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdf.service.MealsService;
import com.sdf.vo.Meals;

/**
 * Servlet implementation class MealsServlet
 */
public class MealsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String subPath = request.getRequestURI().substring(request.getContextPath().length());
	      System.out.println(subPath);
	      if("/meals/daily.do".equals(subPath)) {
	         showDaily(request,response);
	      }else if("/meals/dailydetail.do".equals(subPath)) {
	         
	      }
		
	}

	private void showDaily(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MealsService service =MealsService.getInstance();
		
		/*String date = request.getParameter("date");
		System.out.println("들어온 값 " + date); 
	
		SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd"); 
		String strDate = transFormat.format(Date.parse(date));
				
		
		System.out.println(strDate);*/
		
		//오늘 날짜 넣어야함 		//test를 위해서 09/16으로
		//Date date = new Date();		//오늘날짜
		SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd"); 
		//String strDate = transFormat.format(Date.parse(date));
		
		String strDate = "18/09/16";
		System.out.println(strDate);
		
		Collection<Meals> list = new ArrayList<>(); 
		list = service.findByDay(strDate);		
		
		for(Meals m : list)
			System.out.println(m);
		
		request.setAttribute("list", list);
		
		
	
		String path = "/result/dailyresult.jsp";
		//String path = "/dailymeal.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
