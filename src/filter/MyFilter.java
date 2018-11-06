package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/*@WebFilter(filterName="myFilter", urlPatterns= {"*.do"},
           initParams={ @WebInitParam(name="charset", 
                                      value="UTF-8")})*/
public class MyFilter implements Filter {
	private String charset;
    public MyFilter() {
    	System.out.println("MyFilter객체생성됨");
    }
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("MyFilter init()호출됨");
		charset = fConfig.getInitParameter("charset");
		System.out.println(charset);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("MyFilter doFilter()호출됨");
		
		request.setCharacterEncoding(charset);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	public void destroy() {
		System.out.println("MyFilter destroy()호출됨");
	}



}
