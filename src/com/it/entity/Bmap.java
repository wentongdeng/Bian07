package com.it.entity;

/**
 * Bmap entity. @author MyEclipse Persistence Tools
 */

public class Bmap implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double longitude;
	private Double latitude;
	private String pmphoto;
	private String ptitle;
	private String pdescribe;
	private Integer yh;
	private String ptime;

	// Constructors

	/** default constructor */
	public Bmap() {
	}

	/** full constructor */
	public Bmap(Double longitude, Double latitude, String pmphoto, String ptitle, String pdescribe, Integer yh,
			String ptime) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.pmphoto = pmphoto;
		this.ptitle = ptitle;
		this.pdescribe = pdescribe;
		this.yh = yh;
		this.ptime = ptime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPmphoto() {
		return this.pmphoto;
	}

	public void setPmphoto(String pmphoto) {
		this.pmphoto = pmphoto;
	}

	public String getPtitle() {
		return this.ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPdescribe() {
		return this.pdescribe;
	}

	public void setPdescribe(String pdescribe) {
		this.pdescribe = pdescribe;
	}

	public Integer getYh() {
		return this.yh;
	}

	public void setYh(Integer yh) {
		this.yh = yh;
	}

	public String getPtime() {
		return this.ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

}