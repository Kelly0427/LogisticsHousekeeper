package com.jy.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 系统权限
 * 
 * @author Shinelon
 *
 */
@Entity
public class Permission {

	public Permission() {
		super();
	}

	public Permission(Long id, Long parentId, String permission, String description) {
		super();
		this.id = id;
		this.pId = parentId;
		this.permission = permission;
		this.description = description;
	}

	/**
	 * 权限编号 主键自增
	 */
	@Id
	@GeneratedValue
	@Column
	private Long id;

	/**
	 * 父节点id
	 */
	@Column
	private Long pId;

	/**
	 * 权限名称
	 */
	@Column
	private String permission;

	/**
	 * 权限描述
	 */
	@Column
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
