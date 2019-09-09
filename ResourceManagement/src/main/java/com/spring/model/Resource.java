package com.spring.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Resource")

public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resourceId")
	private int resourceId;
	
	@Column(name = "resourceName")
	private String resourceName;
	
	@Column(name = "resourceCode")
	private String resourceCode;

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "resources_set")
	private Set<Project> projects_set;
	
	@JsonBackReference
	public Set<Project> getProjectsSet() {
		return this.projects_set;
	}

	public void setProjectsSet(Set<Project> projects_set) {
		this.projects_set = projects_set;
	}
	
	/*@Override
	public String toString() {
		return "Resources{" + "resourceId=" + resourceId + " resourceName="+resourceName+" resourceCode="+resourceCode+"}";
	}*/
}
