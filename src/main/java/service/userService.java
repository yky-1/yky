package service;

import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.databaseDao;
import dao.userDao;
import dao.userinformationDao;
import javabean.user;
import javabean.userinformation;
import tool.page;
import tool.FileTool;

public class userService {
	
	public int login(user u){
		userDao dao=new userDao();
		int i=dao.login(u);
		return i;
	}
	
	public List<user> getList(){
		userDao dao=new userDao();
		List<user> list=dao.getList();
		return list;
	}
	public List<user> getpage(page p){
		userDao dao=new userDao();
		List<user> list=dao.getpage(p);
		return list;
	}
	public boolean addUser(user u){
		userDao dao=new userDao();
		dao.addUser(u);
		try {
			databaseDao database=new databaseDao();
			String sql="select name from user where userId="+u.getId();
			database.query(sql);
			while(database.next()){
				return true;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public userinformation getprivate(user u,String name){
		userinformationDao indao=new userinformationDao();
		userDao udao=new userDao();
		udao.findByName(name,u);
		userinformation uin=indao.getOne(u.getId());
		return uin;
	}
	public boolean changeuser(HttpServletRequest request){
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		userinformation uin=new userinformation();
		String userName=(String) request.getSession().getAttribute("username");
		userDao dao=new userDao();
		user u=new user();
		dao.findByName(userName, u);
		uin.setUserId(u.getId());
		try {
			databaseDao database =new databaseDao();
			database.query("select imageurl from userinformation where userId='"+uin.getUserId()+"'");
			while(database.next()){
				uin.setImageurl(database.getString("imageurl"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			List<FileItem> items=upload.parseRequest(request);
			Iterator<FileItem> iter=items.iterator();
			while(iter.hasNext()){
				FileItem item=iter.next();
				if(item.isFormField()){
					String name=item.getFieldName();
					if("sex".equals(name)){
						uin.setSex(item.getString("UTF-8"));
					}
					else if("hobby".equals(name))
						uin.setHobby(item.getString("UTF-8"));
				}
				else if(!item.getName().equals("")){
					String randomFileName=FileTool.getRandomFileNameByCurrentTime(item.getName());
					String path="/upload/images/"+randomFileName;
					path=request.getServletContext().getRealPath(path);
					File file=new File(path);
					item.write(file);
					item.delete();
					uin.setImageurl("/mynews/upload/images/"+randomFileName);
				}
			}
			String sql="update userinformation set sex='"+uin.getSex()+"',hobby='"+uin.getHobby()+"', imageurl='"+uin.getImageurl()+
					"' where userId='"+uin.getUserId()+"'";
			databaseDao data =new databaseDao();
			data.setAutoCommit(false);
			data.update(sql);
			data.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
	}
}
