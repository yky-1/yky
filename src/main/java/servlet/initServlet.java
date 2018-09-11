package servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import dao.AuthorDao;
import dao.databaseDao;
import javabean.Author;
import tool.AuthorTool;

public class initServlet extends HttpServlet {

	
	public initServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void init() throws ServletException {
		databaseDao.classname=this.getServletContext().getInitParameter("classname");
		databaseDao.url=this.getServletContext().getInitParameter("url");
		databaseDao.passwd=this.getServletContext().getInitParameter("passwd");
		databaseDao.user=this.getServletContext().getInitParameter("user");
		
		AuthorDao dao=new AuthorDao();
			List<Author> list;
			try {
				list = dao.getAll();
				for(Author authority:list){
		    	String key;
		    	if(authority.getParam()==null || authority.getParam().isEmpty())
		    		key=authority.getUrl()+authority.getUserType();
		    	else
		    		key=authority.getUrl()+authority.getParam()+authority.getUserType();
		    	AuthorTool.authorityMap.put(key, authority);
		    	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
