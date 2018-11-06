package listener;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdf.service.*;

public class MyContextLoaderListener 
           implements ServletContextListener {

    public MyContextLoaderListener() {
        System.out.println("MyContextLoaderListener객체생성됨");
    }
	
    public void contextInitialized(ServletContextEvent event)  { 
         System.out.println("MyContextLoaderListener의 contextInitialized()호출됨");
         ServletContext sc = event.getServletContext();
         String fileName = sc.getInitParameter("service");
         String propertiesFileName = sc.getRealPath(fileName);
         
         AllergeService.setPropertiesFileName(propertiesFileName);
         EmployeesService.setPropertiesFileName(propertiesFileName);         
         MealsService.setPropertiesFileName(propertiesFileName);   
         
    }
    public void contextDestroyed(ServletContextEvent event)  { 
        System.out.println("MyContextLoaderListener의 contextDestroyed()호출됨");
  }

}
