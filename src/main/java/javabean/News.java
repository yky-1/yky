package javabean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class News {
	private Integer newsId;
	private String newsType ;
	private String author;
	private String caption;
	private String content;
	private LocalDateTime newsTime;
	private Timestamp publishTime;
	private String newsAuthor;
	private int ncheck;
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(LocalDateTime newsTime) {
		this.newsTime = newsTime;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	public int getCheck() {
		return ncheck;
	}
	public void setCheck(int ncheck) {
		this.ncheck = ncheck;
	}
	
	
}
