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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.micro.common.entity.AuditEntity;

@Entity
@Table(name="t_role")
public class Resource extends AuditEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="resource_code",nullable=false)
	private String resourceCode;
	
	@Column(name="resource_name",nullable=false)
	private String resourceName;
	
	@Column
	private Byte active;
	
	@ManyToOne
    @JoinColumn(name="parent_id", foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
    private Resource parent;
	
	@OneToMany
    @JoinColumn(name="parent_id", foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
    private Set<Resource> children = new HashSet<Resource>();
	
	@ManyToMany(mappedBy="resources")
	private Set<Role> roles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Set<Resource> getChildren() {
		return children;
	}

	public void setChildren(Set<Resource> children) {
		this.children = children;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
