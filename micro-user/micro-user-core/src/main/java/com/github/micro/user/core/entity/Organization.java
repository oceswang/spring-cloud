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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.micro.common.entity.AuditEntity;

@Entity
@Table(name="t_organization")
public class Organization extends AuditEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="organization_code",nullable=false)
	private String code;
	
	@Column(name="organization_name",nullable=false)
	private String orgName;
	
	@Column(name="organization_desc")
	private String orgDesc;
	
	@Column
	private Byte active;
	
	@ManyToOne
    @JoinColumn(name="parent_id", foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
    private Organization parent;
	
	@OneToMany
    @JoinColumn(name="parent_id", foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
    private Set<Organization> children = new HashSet<Organization>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public Set<Organization> getChildren() {
		return children;
	}

	public void setChildren(Set<Organization> children) {
		this.children = children;
	}
}
