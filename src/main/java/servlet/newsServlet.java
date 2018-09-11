package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.News;
import service.commentService;
import service.newsService;
import tool.ComReply;
import tool.message;
import tool.page;

public class newsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public newsServlet() {
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
		News news=new News();
		message mage=new message();
		if(type!=null){
			if(type.equals("addnews")){
				String title=null,newstype=null,author=null,time=null;
				title=request.getParameter("title");
				newstype=request.getParameter("newstype");
				author=request.getParameter("author");
				time=request.getParameter("time");
				String username=(String) request.getSession().getAttribute("username");
				news.setNewsAuthor(username);
				news.setAuthor(author);
				news.setNewsType(newstype);
				news.setCaption(title);
				news.setPublishTime(new Timestamp(new Date().getTime()));
				LocalDateTime localDateTime=LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
				news.setNewsTime(localDateTime);
				news.setContent(request.getParameter("editorValue"));
				newsService service=new newsService();
				int flag=service.addNews(news);
				if(flag==1){
					mage.setMessage("添加新闻成功！正在审核...");
					mage.setRedirectUrl("/mynews/index.jsp");
				}else if(flag==-1){
					mage.setMessage("添加失败！");
					mage.setRedirectUrl("/mynews/index.jsp");
				}
				request.setAttribute("message", mage);
				request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
			}else if(type.equals("shownews")){
				page p=new page();
				String page=request.getParameter("page");
				p.setStep(4);
				int num=(Integer.parseInt(page)-1)*p.getStep();
				p.setNumber(num);
				newsService service=new newsService();
				List<News> list=service.getPassNews(p);
				int sum_page=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)sum_page++;
				request.setAttribute("page",page);
				request.setAttribute("sum_page",sum_page);
				request.setAttribute("list",list);
				request.getServletContext().getRequestDispatcher("/news/shownews.jsp").forward(request, response);
			}else if(type.equals("showOneNews")){
				commentService cservice=new commentService();
				String newsId=request.getParameter("id");
				newsService service=new newsService();
				news=service.getNewsById(newsId);
				page p=new page();
				p.setNumber(0);
				p.setStep(5);
				List<ComReply> replyList=cservice.getComReplyList(p, newsId);
				request.setAttribute("list", replyList);
				request.setAttribute("news",news);
				request.getServletContext().getRequestDispatcher("/news/showOneNews.jsp").forward(request, response);
			}else if(type.equals("deleteNews")){
				page p=new page();
				String page=request.getParameter("page");
				p.setStep(4);
				int num=(Integer.parseInt(page)-1)*p.getStep();
				p.setNumber(num);
				newsService service=new newsService();
				List<News> list=service.getList(p);
				int sum_page=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)sum_page++;
				request.setAttribute("page",page);
				request.setAttribute("lastpage",sum_page);
				request.setAttribute("list",list);
				request.getServletContext().getRequestDispatcher("/news/deletenews.jsp").forward(request, response);
			}else if(type.equals("delete")){
				String ids=request.getParameter("ids");
				newsService service=new newsService();
				int i=service.deleteNews(ids);
				if(i==1){
					mage.setMessage("删除成功！");
					mage.setRedirectUrl("/mynews/servlet/newsServlet?type=deleteNews&page=1");
				}else if(i==-1){
					mage.setMessage("删除失败！");
					mage.setRedirectUrl("/mynews/servlet/newsServlet?type=deleteNews&page=1");
				}
				request.setAttribute("message", mage);
				request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
			}else if(type.equals("manageNews")){
				String username=(String) request.getSession().getAttribute("username");
				String page=request.getParameter("page");
				newsService service=new newsService();
				page p=new page();
				p.setStep(4);
				p.setNumber((Integer.parseInt(page)-1)*p.getStep());
				List<News> list=service.getAuthorNews(username,p);
				int lastpage=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)lastpage++;
				request.setAttribute("lastpage",lastpage);
				request.setAttribute("list",list);
				request.setAttribute("page", page);
				request.getServletContext().getRequestDispatcher("/news/managerNews.jsp").forward(request, response);
			}else if(type.equals("editNews")){
				String type1=request.getParameter("type1");
				String newsId=request.getParameter("id");
				newsService service=new newsService();
				if(type1!=null&&type1.equals("edit")){
					String title=null,newstype=null,author=null,time=null;
					title=request.getParameter("title");
					newstype=request.getParameter("newstype");
					author=request.getParameter("author");
					time=request.getParameter("time");
					String username=(String) request.getSession().getAttribute("username");
					news.setNewsAuthor(username);
					news.setAuthor(author);
					news.setNewsType(newstype);
					news.setCaption(title);
					news.setNewsId(Integer.parseInt(newsId));
					news.setPublishTime(new Timestamp(new Date().getTime()));
					LocalDateTime localDateTime=LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
					news.setNewsTime(localDateTime);
					news.setContent(request.getParameter("editorValue"));
					int flag=service.updateNews(news);
					if(flag==1){
						mage.setMessage("新闻修改成功！");
						mage.setRedirectUrl("/mynews/index.jsp");
					}else if(flag==-1){
						mage.setMessage("新闻修改失败！");
						mage.setRedirectUrl("/mynews/index.jsp");
					}
					request.setAttribute("message", mage);
					request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
				}else{
					news=service.getNewsById(newsId);
					request.setAttribute("news",news);
					request.getServletContext().getRequestDispatcher("/news/editNews.jsp").forward(request, response);
				}
			}else if(type.equals("checkNews")){
				String page=request.getParameter("page");
				String newsId=request.getParameter("newsId");
				String check=request.getParameter("check");
				if(check!=null){
					newsService service=new newsService();
					service.checkNews(newsId, check);
				}
				page p=new page();
				p.setStep(4);
				p.setNumber((Integer.parseInt(page)-1)*p.getStep());
				newsService service=new newsService();
				List<News> list=service.getList(p);
				int lastpage=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)lastpage++;
				request.setAttribute("lastpage",lastpage);
				request.setAttribute("list",list);
				request.setAttribute("page", page);
				request.getServletContext().getRequestDispatcher("/manager/manageNews.jsp").forward(request, response);
			}else if(type.equals("typeNews")){
				String newsType=request.getParameter("newsType");
				newsService service=new newsService();
				List<News> list=service.getTypeNews(newsType);
				request.setAttribute("list",list);
				request.getServletContext().getRequestDispatcher("/news/typeNews.jsp").forward(request, response);
			}else if(type.equals("statNews")){
				
			}
		}
	}

	public void init() throws ServletException {
		
	}

}
