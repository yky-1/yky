package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dao.userDao;
import dao.userinformationDao;
import javabean.user;
import javabean.userinformation;
import tool.page;

public class myFirstTest {
	
	 


	@Test
	public void getList(){
		userDao dao=new userDao();
		List<user> list=dao.getList();
		assertNotNull(list);
	}
	@Test
	public  void testLogin(){
		userDao dao=new userDao();
		user u=new user();
		u.setName("Jack");
		u.setPasswd("jack123");
		int n=dao.login(u);
		assertEquals(n, 1);
	}
	@Test
	public void testGetpage(){
		userDao dao=new userDao();
		page p=new page();
		p.setNumber(9);
		p.setStep(3);
		List<user> list=dao.getpage(p);
		assertNotNull(list);
	}
	@Test
	public void testGetCount(){
		userDao dao=new userDao();
		int n=dao.getCount();
		assertEquals(n,11);
	}
	@Test
	public void testSearch_user(){
		userDao dao=new userDao();
		page p=new page();
		p.setNumber(0);
		p.setStep(3);
		user u=new user();
		u.setName("%");
		u.setEnable("stop");
		u.setType("%");
		String time1=null;
		String time2=null;
		List<user> list=dao.search_user(u, time1, time2, p);
		assertNotNull(list);
	}
	@Test
	public void testFindByName(){
		userDao dao=new userDao();
		String name="Jack";
		user u=new user();
		dao.findByName(name, u);
		assertEquals(u.getId(),2);
	}
	
	@Test
	public void testGetPasswd(){
		String name="admin";
		userDao dao=new userDao();
		String passed=dao.getPasswd(name);
		assertEquals(passed,"admins");
	}
	@Test
	public void testChangePasswd(){
		userDao dao=new userDao();
		String newpasswd="21d3rwss";
		String name="阿强";
		boolean bo=dao.changePasswd(newpasswd, name);
		assertEquals(bo,true);
	}
	@Test
	public void testGetUser(){
		userDao dao=new userDao();
		String type="user";
		List<String> list =dao.getUser(type);
		assertNotNull(list);
	}
	
	@Test
	public void testuingetList(){
		userinformationDao uin=new userinformationDao();
		List<userinformation> list=uin.getList();
		assertNotNull(list);
	}
	
	@Test
	public void testuingetOne(){
		userinformation uin=new userinformation();
		userinformationDao dao=new userinformationDao();
		int id=3;
		uin=dao.getOne(id);
		assertEquals(uin.getHobby(),"足球，篮球，球类活动");
	}
}
