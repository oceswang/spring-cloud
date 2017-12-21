package com.github.micro.user.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.micro.common.entity.AuditEntity;

@Entity
@Table(name="t_user")
public class User extends AuditEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="login_name",nullable=false)
	private String loginName;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="mobile_phone")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="employ_no")
	private String employeeNo;
	
	@Column
	private Byte sex;
	
	@Column
	private Byte active;
	
	@ManyToMany
	@JoinTable(name="t_user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"), foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="organization_id", foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	private Organization organization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
