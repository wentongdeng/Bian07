package com.it.entity;

/**
 * Massage entity. @author MyEclipse Persistence Tools
 */

public class Massage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mtext;
	private String mtitle;
	private Integer mlike;
	private String mtime;
	private Integer yh;
	private Double longitude;
	private Double latitude;
	private Integer mcomment;

	// Constructors

	/** default constructor */
	public Massage() {
	}

	/** full constructor */
	public Massage(String mtext, String mtitle, Integer mlike, String mtime, Integer yh, Double longitude,
			Double latitude, Integer mcomment) {
		this.mtext = mtext;
		this.mtitle = mtitle;
		this.mlike = mlike;
		this.mtime = mtime;
		this.yh = yh;
		this.longitude = longitude;
		this.latitude = latitude;
		this.mcomment = mcomment;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMtext() {
		return this.mtext;
	}

	public void setMtext(String mtext) {
		this.mtext = mtext;
	}

	public String getMtitle() {
		return this.mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public Integer getMlike() {
		return this.mlike;
	}

	public void setMlike(Integer mlike) {
		this.mlike = mlike;
	}

	public String getMtime() {
		return this.mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public Integer getYh() {
		return this.yh;
	}

	public void setYh(Integer yh) {
		this.yh = yh;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getMcomment() {
		return this.mcomment;
	}

	public void setMcomment(Integer mcomment) {
		this.mcomment = mcomment;
	}

}