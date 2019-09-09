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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId")
	private int projectId;
	
	@Column(name = "projectName")
	private String projectName;
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="Project_Resource", 
    joinColumns={@JoinColumn(name="PROJECT_ID", referencedColumnName="projectId")}, 
    inverseJoinColumns={@JoinColumn(name="RESOURCE_ID", referencedColumnName="resourceId")})
    private Set<Resource> resources_set = new HashSet<Resource>(0);
	
	//@JsonManagedReference
	@JsonIgnore
	public Set<Resource> getResourcesSet() {
		return resources_set;
	}
	
	public void setResourcesSet(Set<Resource> resourcesSet) {
		this.resources_set = resourcesSet;
	}
	/*@Override
	public String toString() {
		return "Projects{" + "projectId=" + projectId + " projectName="+projectName+"}";
	}*/
}
