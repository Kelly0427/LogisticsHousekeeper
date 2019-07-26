package com.jy.domain.system;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
public class Operator implements UserDetails {

	private static final long serialVersionUID = 2755425275834544305L;


	public Operator() {
		super();
	}

	public Operator(Long id,String name, String passWord,String classCode,String phone,String realName,String role) {
		super();
		this.id = id;
		this.name = name;
		this.passWord = passWord;
		this.classCode=classCode;
		this.phone = phone;
		this.realName = realName;
		this.role=role;
	}

	@Override
	public String toString() {
		return "Operator [id=" + id + ", name=" + name + ",  passWord=" + passWord + ", classCode="+ classCode
				+ ", phone=" + phone  + ", realName= "+realName+", role="+role+ "]";
	}

	/**
	 * 主键自增
	 */
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	/**
	 * 姓名
	 */
	@Column
	private String name;

	/**
	 * 密码
	 */
	@Column(name="password")
	private String passWord;
	
	/**
	 * 班级
	 */
	@Column
	private String classCode;

	/**
	 * 联系方式 可存放多个电话用“，”号隔开
	 */
	@Column
	private String phone;


	/**
	 * 真实姓名
	 */
	@Column
	private String realName;
	
	/**
	 * 角色
	 */
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}


	@Override
	public String getPassword() {
		return this.passWord;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
