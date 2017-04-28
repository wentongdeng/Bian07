package com.it.entity;

/**
 * Msphoto entity. @author MyEclipse Persistence Tools
 */

public class Msphoto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mpaddress;
	private Integer did;

	// Constructors

	/** default constructor */
	public Msphoto() {
	}

	/** full constructor */
	public Msphoto(String mpaddress, Integer did) {
		this.mpaddress = mpaddress;
		this.did = did;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMpaddress() {
		return this.mpaddress;
	}

	public void setMpaddress(String mpaddress) {
		this.mpaddress = mpaddress;
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

}