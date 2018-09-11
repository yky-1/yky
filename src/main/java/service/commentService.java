package service;

import java.util.ArrayList;
import java.util.List;

import dao.commentDao;
import dao.newsDao;
import dao.userDao;
import javabean.Comment;
import javabean.Reply;
import tool.ComReply;
import tool.page;
import tool.userStat;

public class commentService {
	public int addCommnet(Comment com){
		commentDao cdao=new commentDao();
		return cdao.addComment(com);
	}
	public void addpraise(String commentId){
		commentDao cdao=new commentDao();
		cdao.addpraise(commentId);
	}
	public void addReply(Reply r){
		commentDao cdao=new commentDao();
		cdao.addReply(r);
	}
	public List<ComReply> getComReplyList(page p,String newsId){
		commentDao cdao=new commentDao();
		return cdao.getComReplyList(p, newsId);
	}
	public List<Comment> getComment(page p){
		commentDao cdao=new commentDao();
		return cdao.getComment(p);
	}
	public void deleteComment(String ids){
		commentDao cdao=new commentDao();
		cdao.deleteComment(ids);
	}
	public List<userStat> getuserStat(String type){
		commentDao cdao=new commentDao();
		userDao udao=new userDao();
		List<String> name=udao.getUser(type);
		List<userStat> list=new ArrayList<userStat>();
		for(int i=0;i<name.size();i++){
			userStat stat=cdao.statComment(name.get(i));
			stat.setName(name.get(i));
			list.add(stat);
		}
		return list;
	}
	public List<userStat> getnewsStat(){
		newsDao ndao=new newsDao();
		userDao udao=new userDao();
		List<String> name=udao.getUser("newsAuthor");
		List<userStat> list=new ArrayList<userStat>();
		for(int i=0;i<name.size();i++){
			userStat stat=ndao.statNews(name.get(i));
			stat.setName(name.get(i));
			list.add(stat);
		}
		return list;
	}
}
