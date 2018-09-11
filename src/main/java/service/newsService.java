package service;

import java.util.List;

import dao.commentDao;
import dao.newsDao;
import javabean.Comment;
import javabean.News;
import tool.NewsComStat;
import tool.page;

public class newsService {
	public int addNews(News news){
		newsDao dao=new newsDao();
		return dao.addNews(news);
	}
	public List<News> getList(page p){
		newsDao dao=new newsDao();
		List<News> list=dao.getList(p);
		return list;
	}
	public News getNewsById(String id){
		newsDao dao=new newsDao();
		News news=dao.getNewsById(id);
		return news;
	}
	public List<Comment> getCommntList(page p,String newsId){
		commentDao cdao=new commentDao();
		List<Comment> list=cdao.getList(p,newsId);
		return list;
	}
	public int deleteNews(String ids){
		newsDao dao=new newsDao();
		return dao.deleteNews(ids);
	}
	public List<News> getAuthorNews(String username,page p){
		newsDao dao=new newsDao();
		List<News> list=dao.getAuthorNews(username,p);
		return list;
	}
	public int updateNews(News news){
		newsDao dao=new newsDao();
		return dao.updateNews(news);
	}
	public void checkNews(String newsId,String check){
		newsDao dao=new newsDao();
		dao.checkNews(newsId, check);
	}
	public List<News> getPassNews(page p){
		newsDao dao=new newsDao();
		return dao.getpassNews(p);
	}
	public List<News> getTypeNews(String type){
		newsDao dao=new newsDao();
		List<News> list=dao.getTypeNews(type);
		return list;
	}
	public List<NewsComStat> getSumNews(){
		newsDao dao=new newsDao();
		return dao.getSum();
	}
}
