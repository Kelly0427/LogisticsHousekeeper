package com.jy.domain.info;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="info")
public class Info {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name="area")
	private String area;
	
    @Column(name="address")
    private String address;
    
    @Column(name="item")
    private String item;
    
    @Column(name="description")
    private String description;
    
    @Column(name="fullname")
    private String fullName;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="imgurl")
    private String imgUrl;
    
    @Column(name="status")
    private String status;
    
    @Column(name="recordtime")
    private String recordTime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public Info() {
		super();
	}
	public Info(Long id, String area, String address, String item, String description, String fullName, String phone,
			String imgUrl,String status,String recordTime) {
		super();
		this.id = id;
		this.area = area;
		this.address = address;
		this.item = item;
		this.description = description;
		this.fullName = fullName;
		this.phone = phone;
		this.imgUrl = imgUrl;
		this.status=status;
		this.recordTime=recordTime;
	}
	
}
