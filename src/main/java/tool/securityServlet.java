package tool;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.userDao;
import javabean.user;
import service.userService;

public class securityServlet extends HttpServlet {

	public securityServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		String type=null;
		type=request.getParameter("type");
		if(type==null){
			response.sendRedirect("/mynews/resource/login.html");
		}
		else if(type.equals("register")){
			String name=null,passwd=null,usertype=null;
			name=request.getParameter("name");
			passwd=request.getParameter("passwd");
			usertype=request.getParameter("usertype");
			user User=new user();
			User.setName(name);
			User.setPasswd(passwd);
			User.setType(usertype);
			if(usertype.equals("user"))
				User.setEnable("use");
			Timestamp time = new Timestamp(new Date().getTime());
			User.setRegisterdate(time);
			userDao dao=new userDao();
			userService service=new userService();
			User.setId(dao.getCount()+1);
			message mage=new message();
			if(service.addUser(User)){
				mage.setMessage("ע��ɹ���������ת��½����");
				mage.setRedirectUrl("/mynews/resource/login.html");
			}else{
				mage.setMessage("ע��ʧ�ܣ����ڷ���ע�����");
				mage.setRedirectUrl("/mynews/resource/register.html");
			}
			request.setAttribute("message", mage);
			request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
		}else if(type.equals("login")){
		message mage=new message();
		String name=null,passwd=null;
		name=request.getParameter("name");
		passwd=request.getParameter("passwd");
		user User=new user();
		User.setName(name);
		User.setPasswd(passwd);
		userService service=new userService();
		int i=service.login(User);
		if(i==1){
			session.setAttribute("username", name);
			session.setAttribute("user", User);
			String url=(String) session.getAttribute("originalUrl");
			session.removeAttribute("originalUrl");
			if(url==null){
				url="/mynews/index.jsp";
			}
			response.sendRedirect(url);
		}  //��½���
		else if(i==0){
			mage.setMessage("�û��ѱ�ͣ��");
			mage.setRedirectUrl("/mynews/resource/login.html");
			request.setAttribute("message", mage);
			request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
		}  //ͣ��ҳ��
		else if(i==-1){
			mage.setMessage("û�и��û�");
			mage.setRedirectUrl("/mynews/resource/login.html");
			request.setAttribute("message", mage);
			request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
		}
		else if(i==-2){
			mage.setMessage("���벻��ȷ");
			mage.setRedirectUrl("/mynews/resource/login.html");
			request.setAttribute("message", mage);
			request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
		}
		else {
			mage.setMessage("����δ֪����");
			mage.setRedirectUrl("/mynews/resource/login.html");
			request.setAttribute("message", mage);
			request.getServletContext().getRequestDispatcher("/resource/message.jsp").forward(request, response);
		}
	}
		
	}

	public void init() throws ServletException {
	}

}
