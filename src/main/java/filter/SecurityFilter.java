package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.user;


@WebFilter("/SecurityFilter")
public class SecurityFilter implements Filter {

    
    public SecurityFilter() {
       
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session=req.getSession();
		if(session.getAttribute("username")!=null){
			chain.doFilter(request, response);
		}else{
			String orignalurl=req.getRequestURI();
			String query=req.getQueryString();
			String type=req.getParameter("type");
			if(orignalurl.equals("/mynews/servlet/newsServlet")&&(type.equals("typeNews")||type.equals("showOneNews"))){
				chain.doFilter(request, response);
			}else{
				orignalurl=orignalurl+"?"+query;
				session.setAttribute("originalUrl",orignalurl);
				res.sendRedirect("/mynews/resource/login.html");
			}
		
	}
}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
