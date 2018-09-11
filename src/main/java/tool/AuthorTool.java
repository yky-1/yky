package tool;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javabean.Author;
import javabean.user;

public class AuthorTool {

static public Map<String,Author> authorityMap= new Hashtable<String,Author>();
	
	public static String getKey(HttpServletRequest request){
		String originalUrl=request.getRequestURI();//获取用户请求的原始网址
		String param="";
		
		if(!originalUrl.endsWith("jsp")){
			param=request.getParameter("type");
			if(param==null){
				param="";
			}
		}
		String key=originalUrl+param;
		user user=(user)request.getSession().getAttribute("user");
		if(user==null)
			key+="anonymous";
		else
			key+=user.getType();		
		return key;
	}
}
