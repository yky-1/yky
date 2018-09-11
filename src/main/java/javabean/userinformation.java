package javabean;

public class userinformation {
	private int userId;
	private String sex;
	private String hobby;
	private String imageurl;
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getUserId() {
		return userId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
}
