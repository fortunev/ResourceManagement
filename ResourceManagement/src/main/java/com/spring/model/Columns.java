package com.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Columns")
public class Columns implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "colId")
	private int colId;
	
	public int getColId() {
		return colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="projectId_FK")
    private Project projectId;
	
	@JsonIgnore
	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project pROJECT_ID) {
		projectId = pROJECT_ID;
	}
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy = "colId")
	private Set<Cell> cells_set = new HashSet<Cell>(0);
	
	@JsonBackReference
	public Set<Cell> getCellsSet() {
		return this.cells_set;
	}

	public void setCellsSet(Set<Cell> cells_set) {
		this.cells_set = cells_set;
	}
	
	@Column(name = "col_name") 
	private String col_name;
	
	@Column(name = "col_type")
	private String col_type;
	
	@Column(name = "col_formula")
	private String col_formula;
	
	@Column(name = "col_scope")
	private String col_scope;

	public String getCol_name() {
		return col_name;
	}

	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	public String getCol_type() {
		return col_type;
	}

	public void setCol_type(String col_type) {
		this.col_type = col_type;
	}

	public String getCol_formula() {
		return col_formula;
	}

	public void setCol_formula(String col_formula) {
		this.col_formula = col_formula;
	}

	public String getCol_scope() {
		return col_scope;
	}

	public void setCol_scope(String col_scope) {
		this.col_scope = col_scope;
	}
}
