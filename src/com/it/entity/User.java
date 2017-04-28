package com.it.entity;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String uname;
	private String usex;
	private String uemail;
	private String qqnumber;
	private String upicture;
	private Integer uage;
	private String utime;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String uname, String usex, String uemail, String qqnumber, String upicture, Integer uage,
			String utime) {
		this.uname = uname;
		this.usex = usex;
		this.uemail = uemail;
		this.qqnumber = qqnumber;
		this.upicture = upicture;
		this.uage = uage;
		this.utime = utime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUsex() {
		return this.usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public String getUemail() {
		return this.uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getQqnumber() {
		return this.qqnumber;
	}

	public void setQqnumber(String qqnumber) {
		this.qqnumber = qqnumber;
	}

	public String getUpicture() {
		return this.upicture;
	}

	public void setUpicture(String upicture) {
		this.upicture = upicture;
	}

	public Integer getUage() {
		return this.uage;
	}

	public void setUage(Integer uage) {
		this.uage = uage;
	}

	public String getUtime() {
		return this.utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

}