package javabean;

import java.sql.Timestamp;

public class user {

	private int id;
	private String name;
	private String type;
	private String passwd;
	private Timestamp registerdate;
	private String enable;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Timestamp getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(Timestamp registerdate) {
		this.registerdate = registerdate;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
}
