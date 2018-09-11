package test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import javabean.user;
import javabean.userinformation;
import service.userService;
import tool.page;

public class mysecondTest {
	static protected userService service;
	static protected user u;
	static protected page p;
	@BeforeClass
	static public void beforeClass() throws Exception {
		service = new userService();
		p = new page();
		u = new user();
	}

	@Test
	public void testLogin(){
		u.setName("Jack");
		u.setPasswd("jack123");
		int i=service.login(u);
		assertEquals(i, 1);
	}
	@Test
	public void testgetList(){
		List<user> list=service.getList();
		assertNotNull(list);
	}
	@Test
	public void testgetpage(){
		
		p.setNumber(0);
		p.setStep(4);
		List<user> list=service.getpage(p);
		assertNotNull(list);
	}
	@Test
	public void testaddUser(){
		u.setId(12);
		u.setName("lisi");
		u.setPasswd("woshilisi");
		u.setType("user");
		u.setRegisterdate(new Timestamp(new Date().getTime()));
		boolean item=service.addUser(u);
		assertEquals(item,true);
	}
	@Test
	public void testgetprivate(){
		String name="ภ๎ธี";
		userinformation uin=service.getprivate(u, name);
		assertNotNull(uin);
	}
}
