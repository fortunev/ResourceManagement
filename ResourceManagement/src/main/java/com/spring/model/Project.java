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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import antlr.collections.List;

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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="proj", cascade=CascadeType.ALL)
	private Set<Project_Resource> resources_set = new HashSet<Project_Resource>(0);
	
	@JsonIgnore
	public Set<Project_Resource> getResourcesSet() {
		return resources_set;
	}
	
	public void setResourcesSet(Set<Project_Resource> resourcesSet) {
		this.resources_set = resourcesSet;
	}
	
	 public void addResource(Resource r) {
	 	Project_Resource pr = new Project_Resource( this, r );
        resources_set.add( pr );
        r.getProjectsSet().add( pr );
        System.out.println("ADD ResourceSet - "+resources_set.toString());
        System.out.println("ADD R.ResourceSet - "+r.getProjectsSet().toString());
    }

    public void removeResource(Resource r) {
    	Project_Resource pr = new Project_Resource ( this, r );
        r.getCellsSet().clear();
        r.getProjectsSet().remove( pr );
        this.getColumnsSet().clear();
        resources_set.remove( pr );
        pr.setProj( null );
        pr.setRes( null );
    }
    
    public Set<Project_Resource> list_proj_res () {    	
		return this.resources_set;    	
    }

	
	@OneToMany (fetch = FetchType.EAGER, mappedBy = "projectId")
	private Set<Columns> colunmns_set = new HashSet<Columns>(0);
	
	@JsonBackReference
	public Set<Columns> getColumnsSet() {
		return this.colunmns_set;
	}

	public void setColumnsSet(Set<Columns> colunmns_set) {
		this.colunmns_set = colunmns_set;
	}
	
	@Override
	public String toString() {
		return "Projects{" + "projectId=" + projectId + " projectName="+projectName+"}";
	}
}
