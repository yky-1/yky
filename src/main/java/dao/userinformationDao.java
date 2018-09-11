package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.userinformation;

public class userinformationDao {
	public List<userinformation> getList(){
		List<userinformation> list=new ArrayList<userinformation>();
		String sql="select * from userinformation";
		try {
			databaseDao dao=new databaseDao();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public userinformation getOne(int userId){
		userinformation u=new userinformation();
		String sql="select * from userinformation where userId ='"+userId+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				u.setUserId(dao.getInteger("userId"));
				u.setHobby(dao.getString("hobby"));
				u.setSex(dao.getString("sex"));
				u.setImageurl(dao.getString("imageurl"));
				return u;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

}
