package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javabean.Comment;
import javabean.Reply;
import tool.ComReply;
import tool.page;
import tool.userStat;

public class commentDao {

	public int addComment(Comment com){
		String sql="select  max(commentId) as maxId from comment";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				if(dao.getInteger("maxId")==null)com.setCommentId(1);
				else com.setCommentId(dao.getInteger("maxId")+1);
			}
			sql="select max(stair) as maxstair from comment where newsId='"+com.getNewsId()+"'";
			dao.query(sql);
			while(dao.next()){
				if(dao.getInteger("maxstair")==null)com.setStair(1);
				else com.setStair(dao.getInteger("maxstair")+1);
			}
			sql="insert into comment values('"+com.getCommentId()+"','"+com.getNewsId()+"','"+com.getUserName()
			+"','"+com.getContent()+"','"+com.getTime()+"','"+com.getPraise()+"','"+com.getStair()+"')";
			dao.update(sql);
			return 1;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
	public List<Comment> getList(page p,String newsId){
		List<Comment> list=new ArrayList<Comment>();
		String sql="select * from comment where newsId='"+newsId+"' limit "+p.getNumber()+", "+p.getStep();
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				Comment com=new Comment();
				com.setCommentId(dao.getInteger("commentId"));
				com.setContent(dao.getString("content"));
				com.setNewsId(dao.getInteger("newsId"));
				com.setPraise(dao.getInteger("praise"));
				com.setStair(dao.getInteger("stair"));
				com.setTime(dao.getTimestamp("time"));
				com.setUserName(dao.getString("username"));
				list.add(com);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void addpraise(String commentId){
		String sql="select praise from comment where commentId='"+commentId+"'";
		int sum=0;
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				sum=dao.getInteger("praise");
			}
			sum++;
			sql="update comment set praise='"+sum+"' where commentId='"+commentId+"'";
			dao.update(sql);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	public List<Reply> getReply(String commentId){
		List<Reply> list=new ArrayList<>();
		String sql="select * from reply where commentId='"+commentId+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				Reply r=new Reply();
				r.setCommentId(dao.getInteger("commentId"));
				r.setContent(dao.getString("content"));
				r.setReplyId(dao.getInteger("replyId"));
				r.setTime(dao.getTimestamp("time"));
				r.setUserName(dao.getString("userName"));
				list.add(r);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void addReply(Reply r){
		String sql1="select max(replyId) as maxId from reply";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql1);
			while(dao.next()){
				if(dao.getInteger("maxId")==null){
					r.setReplyId(1);
				}else{
				r.setReplyId(dao.getInteger("maxId")+1);
				}
			}
			String sql="insert into reply values('"+r.getReplyId()+"','"+r.getCommentId()+"','"+r.getUserName()
			+"','"+r.getContent()+"','"+r.getTime()+"')";
			dao.update(sql);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<ComReply> getComReplyList(page p,String newsId){
		List<Comment> comlist=getList(p,newsId);
		List<ComReply> comreply=new ArrayList<ComReply>();
		for(int i=0;i<comlist.size();i++)
		{
			Comment item=comlist.get(i);
			List<Reply> rlist=getReply(item.getCommentId().toString());
			ComReply com=new ComReply();
			com.setComment(item);
			com.setReply(rlist);
			comreply.add(com);
		}
		return comreply;
	}
	public List<Comment> getComment(page p){
		List<Comment> list=new ArrayList<Comment>();
		String sql="select * from comment limit "+p.getNumber()+","+p.getStep();
		String sql1="select count(*) as sum from comment";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				Comment com=new Comment();
				com.setCommentId(dao.getInteger("commentId"));
				com.setContent(dao.getString("content"));
				com.setNewsId(dao.getInteger("newsId"));
				com.setPraise(dao.getInteger("praise"));
				com.setTime(dao.getTimestamp("time"));
				com.setUserName(dao.getString("username"));
				list.add(com);
			}
			dao.query(sql1);
			while(dao.next())p.setSum(dao.getInteger("sum"));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	public void deleteComment(String ids){
		String sql="delete from comment where commentId in ("+ids+")";
		try {
			databaseDao dao=new databaseDao();
			dao.update(sql);
			sql="delete from reply where commentId in ("+ids+")";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public userStat statComment(String username){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String sql="select count(*) as year from comment where userName='"+username+"' and time >='"
	+(df.format(new Date()))+"-01-1'";
		userStat stat=new userStat();
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			if(dao.next())
				stat.setYear(dao.getInteger("year"));
			
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
			sql="select count(*) as month from comment where userName='"+username+"' and time >='"
		+(df1.format(new Date()))+"-1'";
			dao.query(sql);
			if(dao.next())
				stat.setMonth(dao.getInteger("month")); 
			
			SimpleDateFormat df2 = new SimpleDateFormat("dd");
			String day=df2.format(new Date());
			SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
			String currSun = dateFm.format(new Date());
			int i=Integer.parseInt(day);
			if(currSun.equals("星期二"))i=i-2;
			else if(currSun.equals("星期三"))i=i-3;
			else if(currSun.equals("星期四"))i=i-4;
			else if(currSun.equals("星期五"))i=i-5;
			else if(currSun.equals("星期六"))i=i-6;
			else if(currSun.equals("星期一"))i=i--;
			sql="select count(*) as day from comment where userName='"+username+"' and time >='"
					+(df1.format(new Date()))+"-"+i+"'";
			dao.query(sql);
			if(dao.next())
				stat.setWeek(dao.getInteger("day")); 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stat;
	}
	public int getAllComment(){
		String sql="select count(*) as sum from comment";
		int sum=0;
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			sum+=dao.getInteger("sum");
			sql="select count(*) as replySum from reply";
			dao.query(sql);
			sum+=dao.getInteger("replySum");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
}
