package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javabean.Author;
import tool.AuthorTool;
import tool.message;

public class AuthorFilter implements Filter {

    public AuthorFilter() {
        
    }

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String key=AuthorTool.getKey(req);
		Author authority=AuthorTool.authorityMap.get(key);
		
		if(authority==null){//无权限
			message message=new message();
			message.setMessage("权限不够！");
			message.setRedirectTime(1000);
			message.setRedirectUrl("/mynews/index.jsp");
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request,response);
			return;
		}else
			chain.doFilter(request, response);//有权限，可以继续访问
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
