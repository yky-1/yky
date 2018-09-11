package javabean;

import java.sql.Timestamp;

public class Comment {
	private Integer commentId;
	private Integer  newsId;
	private String userName;
	private String content;
	private Timestamp time;
	private Integer  praise;
	private Integer stair;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Integer getStair() {
		return stair;
	}
	public void setStair(Integer stair) {
		this.stair = stair;
	}
	
}
