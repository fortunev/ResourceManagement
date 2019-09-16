package com.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "res", cascade = CascadeType.ALL)
	private Set<Project_Resource> projects_set = new HashSet<Project_Resource>(0);
	
	@JsonBackReference(value = "projects_set")
	public Set<Project_Resource> getProjectsSet() {
		return this.projects_set;
	}

	public void setProjectsSet(Set<Project_Resource> projects_set) {
		this.projects_set = projects_set;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resourceId")
	private Set<Cell> cells_set;
	
	@JsonBackReference(value = "cells_set")
	public Set<Cell> getCellsSet() {
		return this.cells_set;
	}

	public void setCellsSet(Set<Cell> cells_set) {
		this.cells_set = cells_set;
	}
	
	@Override
	public String toString() {
		return "Resources{" + "resourceId=" + resourceId + " resourceName="+resourceName+" resourceCode="+resourceCode+"}";
	}
}
