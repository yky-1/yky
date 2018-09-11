package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.userDao;
import javabean.user;
import javabean.userinformation;
import service.userService;
import tool.message;
import tool.page;

public class userServlet extends HttpServlet {

	
	public userServlet() {
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
			 if(type.equals("showpage")){
				String page=request.getParameter("page");
				userService service=new userService();
				page p=new page();
				p.setStep(3);
				p.setNumber((Integer.parseInt(page)-1)*p.getStep());
				List<user> list=service.getpage(p);
				int page_sum=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)page_sum++;
				request.setAttribute("list", list);
				request.setAttribute("page",Integer.parseInt(page));
				request.setAttribute("lastpage",page_sum);
				request.setAttribute("type", type);
				request.getServletContext().getRequestDispatcher("/manager/ShowUser.jsp").forward(request, response);
			}else if(type.equals("check")){
				String page=request.getParameter("page");
				String ids=null;
				ids=request.getParameter("ids");
				if(ids!=null&&!ids.equals("0")){
					int id=Integer.parseInt(ids);
					userDao dao=new userDao();
					dao.changeEnable(id);
				}
				userService service=new userService();
				page p=new page();
				p.setStep(3);
				p.setNumber((Integer.parseInt(page)-1)*3);
				List<user> list=service.getpage(p);
				int page_sum=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)page_sum++;
				request.setAttribute("list", list);
				request.setAttribute("page",Integer.parseInt(page));
				request.setAttribute("lastpage",page_sum);
				request.setAttribute("type", type);
				request.getServletContext().getRequestDispatcher("/manager/checkUser.jsp").forward(request, response);
			}else if(type.equals("search")){
				String name=null,usertype=null,enable=null,time1=null,time2=null;
				name=request.getParameter("name");
				usertype=request.getParameter("usertype");
				enable=request.getParameter("enable");
				time1=request.getParameter("time1");
				time2=request.getParameter("time2");
				String page=request.getParameter("page");
				page p=new page();
				p.setStep(3);
				p.setNumber((Integer.parseInt(page)-1)*3);
				if(name==null||name.equals(""))name="%";
				user u=new user();
				u.setEnable(enable);
				u.setName(name);
				u.setType(usertype);
				userDao dao=new userDao();
				request.getSession().setAttribute("u", u);
				request.getSession().setAttribute("time1", time1);
				request.getSession().setAttribute("time2", time2);
				List<user> list=dao.search_user(u, time1, time2,p);
				int page_sum=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)page_sum++;
				request.getSession().setAttribute("page_sum", page_sum);
				type="search_page";
				request.setAttribute("list", list);
				request.setAttribute("page",page);
				request.setAttribute("lastpage",page_sum);
				request.setAttribute("type", type);
				request.getServletContext().getRequestDispatcher("/manager/ShowUser.jsp").forward(request, response);
			}else if(type.equals("search_page")){
				user u=(user) request.getSession().getAttribute("u");
				String time1=(String) request.getSession().getAttribute("time1");
				String time2=(String) request.getSession().getAttribute("time2");
				String page=request.getParameter("page");
				int page_sum=(int) request.getSession().getAttribute("page_sum");
				page p=new page();
				p.setStep(3);
				p.setNumber((Integer.parseInt(page)-1)*3);
				userDao dao=new userDao();
				List<user> list=dao.search_user(u, time1, time2,p);
				request.setAttribute("list", list);
				request.setAttribute("page",page);
				request.setAttribute("lastpage",page_sum);
				request.setAttribute("type", type);
				request.getServletContext().getRequestDispatcher("/manager/ShowUser.jsp").forward(request, response);
			}else if(type.equals("deleteUser")){
				String ids=request.getParameter("ids");
				String page=request.getParameter("page");
				if(ids!=null){
					userDao dao=new userDao();
					dao.deleteUser(ids);
				}
				userService service=new userService();
				page p=new page();
				p.setStep(3);
				p.setNumber((Integer.parseInt(page)-1)*3);
				List<user> list=service.getpage(p);
				int page_sum=p.getSum()/p.getStep();
				if(p.getSum()%p.getStep()>0)page_sum++;
				request.setAttribute("list", list);
				request.setAttribute("page",page);
				request.setAttribute("lastpage",page_sum);
				request.getServletContext().getRequestDispatcher("/manager/deleteUser.jsp").forward(request, response);
			}else if(type.equals("showprivate")){
				String name=(String) request.getSession().getAttribute("username");
				userService service=new userService();
				user u=new user();
				userinformation uin=service.getprivate(u,name);
				request.setAttribute("uin", uin);
				request.setAttribute("u", u);
				request.getServletContext().getRequestDispatcher("/user/showUser.jsp").forward(request, response);
			}else if(type.equals("changepassword")){
				String oldpasswd=null,newpasswd=null;
				oldpasswd=request.getParameter("oldpasswd");
				newpasswd=request.getParameter("newpasswd");
				String name=(String) request.getSession().getAttribute("username");
				message mage=new message();
				userDao dao=new userDao();
				if(!oldpasswd.equals(dao.getPasswd(name))){
					mage.setMessage("原密码不正确！");
					mage.setRedirectUrl("/mynews/user/changepasswd.jsp");
				}else{ 
					if(dao.changePasswd(newpasswd, name)){
						mage.setMessage("修改密码成功！");
						mage.setRedirectUrl("/mynews/index.jsp");
					}else{
						mage.setMessage("发生未知错误！");
						mage.setRedirectUrl("/mynews/index.jsp");
					}
				}
				request.setAttribute("message", mage);
				request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
			}else if(type.equals("changeuser")){
				
				String name=(String) request.getSession().getAttribute("username");
				String path=null;
				String type1=request.getParameter("type1");
				userService service=new userService();
				user u=new user();
				if(type1!=null&&!type1.equals("")){
					service.changeuser(request);
					path="/user/showUser.jsp";
				}else{
					path="/user/changeUse.jsp";
				}
				userinformation uin=service.getprivate(u,name);
				request.setAttribute("uin", uin);
				request.setAttribute("u", u);
				request.getServletContext().getRequestDispatcher(path).forward(request, response);
			}else if(type.equals("exit")){
				request.getSession().removeAttribute("username");
				request.getSession().removeAttribute("user");
				response.sendRedirect("/mynews/index.jsp");
			}
	}

	public void init() throws ServletException {
		
	}

}
