package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.user;
import tool.page;

public class userDao {
	
	public List<user> getList(){
		List<user> list=new ArrayList<user>();
		String sql="select * from user";
		try {
			databaseDao database=new databaseDao();
			database.query(sql);
			while(database.next()){
				user useritem=new user();
				useritem.setId(database.getInteger("userId"));
				useritem.setEnable(database.getString("Enable"));
				useritem.setName(database.getString("name"));
				useritem.setPasswd(database.getString("password"));
				useritem.setRegisterdate(database.getTimestamp("registerdate"));
				useritem.setType(database.getString("type"));
				list.add(useritem);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int login(user u){
		String sql="select * from user where name='"+u.getName()+"'";
		try {
			databaseDao database=new databaseDao();
			database.query(sql);
			while(database.next()){
				if(database.getString("enable").equals("use")){
					if(database.getString("password").equals(u.getPasswd())){
					u.setType(database.getString("type"));
					return 1;//可以登录
					}
					else return -2;//密码不正确
				}
				else return 0;//用户停用
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //没有该用户
	}

	
	public List<user> getpage(page p){
		List<user> list=new ArrayList<user>();
		String sql="select * from user limit "+p.getNumber()+", "+p.getStep();
		try {
			databaseDao dao=new databaseDao();
			dao.query("select count(*) as recordcount from user");
			while(dao.next()){
				p.setSum(dao.getInteger("recordcount"));
			}
			dao.query(sql);
			while(dao.next()){
				user useritem=new user();
				useritem.setId(dao.getInteger("userId"));
				useritem.setEnable(dao.getString("enable"));
				useritem.setName(dao.getString("name"));
				useritem.setPasswd(dao.getString("password"));
				useritem.setRegisterdate(dao.getTimestamp("registerDate"));
				useritem.setType(dao.getString("type"));
				list.add(useritem);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int getCount(){
		int count=0;
		try {
			databaseDao dao= new databaseDao();
			dao.query("select count(*) as recordcount from user");
			if(dao.next()){
				count=dao.getInteger("recordcount");	
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public void addUser(user u){
		String sql="insert into user values ('"+u.getId()+"','"+u.getType()+"','"+
	u.getName()+"','"+u.getPasswd()+"','"+u.getRegisterdate()+"','"+u.getEnable()+"')";
		try {
			databaseDao dao= new databaseDao();
			dao.update(sql);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public void changeEnable(int id){
		String sql="select enable from user where userId='"+id+"'";
		try {
			databaseDao dao= new databaseDao();
			dao.query(sql);
			while(dao.next()){
				if(dao.getString("enable").equals("use")) 
					sql="update user set enable='stop' where userId='"+id+"'";
				else sql="update user set enable='use' where userId='"+id+"'";
				dao.update(sql);
				break;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<user> search_user(user u,String time1,String time2,page p){
		List<user> list=new ArrayList<user>();
		String sql,sql1;
		if(time1==null||time2==null||time2.equals("")||time1.equals("")){
			sql="select userId from user where name like '"+u.getName()+"' and enable like '"+
					u.getEnable()+"' and type like '"+u.getType()+"'";
			sql1="select count(*) as recordcount from user where name like '"+u.getName()+"' and enable like '"+
					u.getEnable()+"' and type like '"+u.getType()+"'";
			sql="select * from user where userId in ("+sql+") limit "+p.getNumber()+", "+p.getStep();
			
		}else{
			sql="select userId from user where name like '"+u.getName()+"' and enable like '"+
					u.getEnable()+"' and type like '"+u.getType()+"' and registerDate > '"+time1+"' and registerDate < '"+time2+"'";
			sql1="select count(*) as recordcount from user where name like '"+u.getName()+"' and enable like '"+
					u.getEnable()+"' and type like '"+u.getType()+"' and registerDate > '"+time1+"' and registerDate < '"+time2+"'";
			sql="select * from user where userId in ("+sql+") limit "+p.getNumber()+", "+p.getStep();
		}
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				user useritem=new user();
				useritem.setId(dao.getInteger("userId"));
				useritem.setEnable(dao.getString("enable"));
				useritem.setName(dao.getString("name"));
				useritem.setPasswd(dao.getString("password"));
				useritem.setRegisterdate(dao.getTimestamp("registerDate"));
				useritem.setType(dao.getString("type"));
				list.add(useritem);
			}
			dao.query(sql1);
			while(dao.next()){
				p.setSum(dao.getInteger("recordcount"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void deleteUser(String ids){
		String sql="delete from user where userId in ("+ids+")";
		try {
			databaseDao dao =new databaseDao();
			dao.update(sql);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findByName(String name,user u){
		String sql="select * from user where name='"+name+"'";
		try {
			databaseDao dao =new databaseDao();
			dao.query(sql);
			while(dao.next()){
				u.setId(dao.getInteger("userId"));
				u.setEnable(dao.getString("enable"));
				u.setName(dao.getString("name"));
				u.setRegisterdate(dao.getTimestamp("registerdate"));
				u.setType(dao.getString("type"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	public String getPasswd(String name){
		String sql="select password from user where name='"+name+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				return dao.getString("password");
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changePasswd(String newpasswd,String name){
		String sql="update user set password='"+newpasswd+"' where name='"+name+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.update(sql);
			return true;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public List<String> getUser(String type){
		List<String> list=new ArrayList<String>();
		String sql="select name from user where type='"+type+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				String name=dao.getString("name");
				list.add(name);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
