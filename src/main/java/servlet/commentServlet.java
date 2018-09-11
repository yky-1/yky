package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Comment;
import javabean.Reply;
import service.commentService;
import service.newsService;
import tool.ComReply;
import tool.NewsComStat;
import tool.message;
import tool.page;
import tool.userStat;

public class commentServlet extends HttpServlet {

	
	public commentServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=null;
		type=request.getParameter("type");
		if(type.equals("addComment")){
			String username=(String) request.getSession().getAttribute("username");
			Comment com=new Comment();
			com.setUserName(username);
			String comment=request.getParameter("comment");
			String newsId=request.getParameter("newsId");
			com.setContent(comment);
			com.setNewsId(Integer.parseInt(newsId));
			com.setPraise(0);
			com.setTime(new Timestamp(new Date().getTime()));
			commentService service=new commentService();
			service.addCommnet(com);
			response.sendRedirect("/mynews/servlet/newsServlet?type=showOneNews&id="+newsId);
		}else if(type.equals("addreply")){
			String username=(String) request.getSession().getAttribute("username");
			String newsId=request.getParameter("newsId");
			Reply r=new Reply();
			r.setUserName(username);
			String content=request.getParameter("content");
			String commentId=request.getParameter("commentId");
			r.setContent(content);
			r.setCommentId(Integer.parseInt(commentId));
			r.setTime(new Timestamp(new Date().getTime()));
			commentService service=new commentService();
			service.addReply(r);
			response.sendRedirect("/mynews/servlet/newsServlet?type=showOneNews&id="+newsId);
		}else if(type.equals("praise")){
			String commentId=request.getParameter("commentid");
			String newsId=request.getParameter("id");
			commentService cservice=new commentService();
			cservice.addpraise(commentId);
			response.sendRedirect("/mynews/servlet/newsServlet?type=showOneNews&id="+newsId);
		}else if(type.equals("deleteComment")){
			page p=new page();
			String page=request.getParameter("page");
			String type1=request.getParameter("type1");
			p.setStep(5);
			p.setNumber((Integer.parseInt(page)-1)*p.getStep());
			commentService cservice=new commentService();
			if(type1!=null&&type1.equals("delete")){
				String ids=request.getParameter("ids");
				cservice.deleteComment(ids);
			}
			List<Comment> list=cservice.getComment(p);
			int lastpage=p.getSum()/p.getStep();
			if(p.getSum()%p.getStep()>0)lastpage++;
			request.setAttribute("list", list);
			request.setAttribute("page",page);
			request.setAttribute("lastpage",lastpage);
			request.getServletContext().getRequestDispatcher("/manager/deleteComment.jsp").forward(request, response);
		}else if(type.equals("stat")){
			commentService cservice=new commentService();
			newsService nservice=new newsService();
			List<userStat> ulist=cservice.getuserStat("user");
			List<userStat> nlist=cservice.getnewsStat();
			List<NewsComStat> slist=nservice.getSumNews();
			request.setAttribute("ulist", ulist);
			request.setAttribute("nlist",nlist);
			request.setAttribute("slist",slist);
			request.getServletContext().getRequestDispatcher("/manager/stat.jsp").forward(request, response);
		}
	}

	public void init() throws ServletException {
		
	}

}
