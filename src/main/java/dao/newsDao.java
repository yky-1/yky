package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javabean.News;
import tool.NewsComStat;
import tool.page;
import tool.userStat;

public class newsDao {
	
	public int addNews(News news){
		String sql="select Max(newsId) as maxId from news";
		try {
			databaseDao data=new databaseDao();
			databaseDao dao=new databaseDao();
			data.query(sql);
			while(data.next()){
				news.setNewsId(data.getInteger("maxId")+1);
			}
			String sql1="insert into news (newsId,newsType,author,caption,content,newsTime,publishTime,newsAuthor) values('"
			+news.getNewsId()+"','"+news.getNewsType()+"','"+news.getAuthor()+"','"+news.getCaption()+"','"+news.getContent()
			+"','"+news.getNewsTime()+"','"+news.getPublishTime()+"','"+news.getNewsAuthor()+"')";
			dao.update(sql1);
			return 1;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public List<News> getpassNews(page p){
		List<News> list=new ArrayList<News>();
		String sql="select * from news where ncheck='1' limit "+p.getNumber()+", "+p.getStep();
		String sql1="select count(*) as newscount from news where ncheck='1'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql1);
			while(dao.next()){
				p.setSum(dao.getInteger("newscount"));
			}
			dao.query(sql);
			while(dao.next()){
				News news=new News();
				news.setNewsId(dao.getInteger("newsId"));
				news.setAuthor(dao.getString("author"));
				news.setCaption(dao.getString("caption"));
				news.setContent(dao.getString("content"));
				news.setPublishTime(dao.getTimestamp("publishTime"));
				news.setNewsType(dao.getString("newsType"));
				news.setNewsTime(dao.getLocalDateTime("newsTime"));
				news.setCheck(dao.getInteger("ncheck"));
				list.add(news);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	public List<News> getList(page p){
		List<News> list=new ArrayList<News>();
		String sql="select * from news limit "+p.getNumber()+", "+p.getStep();
		String sql1="select count(*) as newscount from news";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql1);
			while(dao.next()){
				p.setSum(dao.getInteger("newscount"));
			}
			dao.query(sql);
			while(dao.next()){
				News news=new News();
				news.setNewsId(dao.getInteger("newsId"));
				news.setAuthor(dao.getString("author"));
				news.setCaption(dao.getString("caption"));
				news.setContent(dao.getString("content"));
				news.setPublishTime(dao.getTimestamp("publishTime"));
				news.setNewsType(dao.getString("newsType"));
				news.setNewsTime(dao.getLocalDateTime("newsTime"));
				news.setCheck(dao.getInteger("ncheck"));
				list.add(news);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	public News getNewsById(String newsId){
		News news=new News();
		String sql="select * from news where newsId='"+newsId+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				news.setNewsId(dao.getInteger("newsId"));
				news.setAuthor(dao.getString("author"));
				news.setCaption(dao.getString("caption"));
				news.setContent(dao.getString("content"));
				news.setPublishTime(dao.getTimestamp("publishTime"));
				news.setNewsType(dao.getString("newsType"));
				news.setNewsTime(dao.getLocalDateTime("newsTime"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return news;
	}
	public int deleteNews(String ids){
		String sql="delete from news where newsId in ("+ids+")";
		try {
			databaseDao dao=new databaseDao();
			dao.update(sql);
			sql="delete from comment where newsId in ("+ids+")";
			dao.update(sql);
			return 1;       //删除成功
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return -1;   //删除失败
	}
	public List<News> getAuthorNews(String username,page p){
		List<News> list=new ArrayList<News>();
		String sql="select count(*) as sum from news where newsAuthor='"+username+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				p.setSum(dao.getInteger("sum"));
			}
			sql="select * from news where newsAuthor='"+username+"' limit "+p.getNumber()+", "+p.getStep();
			dao.query(sql);
			while(dao.next()){
				News news=new News();
				news.setNewsId(dao.getInteger("newsId"));
				news.setAuthor(dao.getString("author"));
				news.setCaption(dao.getString("caption"));
				news.setContent(dao.getString("content"));
				news.setPublishTime(dao.getTimestamp("publishTime"));
				news.setNewsType(dao.getString("newsType"));
				news.setNewsTime(dao.getLocalDateTime("newsTime"));
				list.add(news);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateNews(News news){
		String sql="update news set newsType='"+news.getNewsType()+"',author='"+news.getAuthor()
		+"',caption='"+news.getCaption()+"',content='"+news.getContent()
		+"',newsTime='"+news.getNewsTime()+"',publishTime='"+news.getPublishTime()+"' where newsId='"+news.getNewsId()+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.update(sql);
			return 1;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
	public void checkNews(String newsId,String check){
		String sql="update news set ncheck='"+check+"' where newsId='"+newsId+"'";
		try {
			databaseDao dao=new databaseDao();
			dao.update(sql);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<News> getTypeNews(String type){
		String sql="select * from news where newsType='"+type+"' and ncheck='1' limit 0,9";
		List<News> list=new ArrayList<News>();
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			while(dao.next()){
				News news=new News();
				news.setNewsId(dao.getInteger("newsId"));
				news.setAuthor(dao.getString("author"));
				news.setCaption(dao.getString("caption"));
				news.setContent(dao.getString("content"));
				news.setPublishTime(dao.getTimestamp("publishTime"));
				news.setNewsType(dao.getString("newsType"));
				news.setNewsTime(dao.getLocalDateTime("newsTime"));
				news.setCheck(dao.getInteger("ncheck"));
				list.add(news);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<NewsComStat> getSum(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		SimpleDateFormat df1 = new SimpleDateFormat("MM");
		String year=df.format(new Date());
		int month=Integer.parseInt(df1.format(new Date()));
		List<NewsComStat> list=new ArrayList<NewsComStat>(); 
		for(int i=1;i<=month;i++){
			String sql="select count(*) as newsSum from news where publishTime >='"
					+year+"-"+i+"-1' and publishTime < '"+year+"-"+(i+1)+"-1'";
			try {
				databaseDao dao=new databaseDao();
				NewsComStat stat=new NewsComStat();
				dao.query(sql);
				if(dao.next()){
					stat.setNews(dao.getInteger("newsSum"));
					stat.setMonth(i);
				}
				sql="select count(*) as comSum from comment where time >='"
						+year+"-"+i+"-1' and time < '"+year+"-"+(i+1)+"-1'";
				dao.query(sql);
				if(dao.next()){
					stat.setComment(dao.getInteger("comSum"));
				}
				list.add(stat);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		}
		return list;
	}
	public userStat statNews(String username){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String sql="select count(*) as year from news where newsAuthor='"+username+"' and publishTime >='"
	+(df.format(new Date()))+"-01-1'";
		userStat stat=new userStat();
		try {
			databaseDao dao=new databaseDao();
			dao.query(sql);
			if(dao.next())
				stat.setYear(dao.getInteger("year"));
			
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
			sql="select count(*) as month from news where newsAuthor='"+username+"' and publishTime >='"
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
			sql="select count(*) as week from news where newsAuthor='"+username+"' and publishTime >='"
					+(df1.format(new Date()))+"-"+i+"'";
			dao.query(sql);
			if(dao.next())
				stat.setWeek(dao.getInteger("week")); 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stat;
	}
}
