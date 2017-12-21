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
import javax.persistence.Table;

import com.github.micro.common.entity.AuditEntity;

@Entity
@Table(name="t_role")
public class Role extends AuditEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="role_name",nullable=false)
	private String roleName;
	
	@Column(name="role_desc")
	private String roleDesc;
	
	@Column
	private Byte active;
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="t_role_resource",joinColumns = @JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="resource_id"), foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
	private Set<Resource> resources = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Byte getActive() {
		return active;
	}
	public void setActive(Byte active) {
		this.active = active;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
	
}
