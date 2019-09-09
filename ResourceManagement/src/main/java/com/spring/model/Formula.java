package com.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Formula")
//@IdClass(FormulaIdentity.class)
public class Formula implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private FormulaIdentity forumla_id;
	
	@MapsId("projectId_key")
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="projectId_FK")
    private Project projectId;
	
	@JsonIgnore
	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project pROJECT_ID) {
		projectId = pROJECT_ID;
	}

	@MapsId("resourceId_key")
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="resourceId_FK")
    private Resource resourceId;

	@JsonIgnore
	public Resource getResourceId() {
		return resourceId;
	}

	public void setResourceId(Resource rESOURCE_ID) {
		resourceId = rESOURCE_ID;
	}

	
	/*@JoinColumns({@JoinColumn(name = "FIELD_ID_FK", referencedColumnName="fieldId"), 
        @JoinColumn(name= "PROJECT_ID_FK", referencedColumnName="PROJECT_ID")})*/
	@MapsId("fieldId_key")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FIELD_ID", nullable = false)
	private Field FIELD_item;
	@JsonIgnore
	public Field getFIELD_item() {
		return FIELD_item;
	}
	
	public void setFIELD_item (Field FIELD_item) {
		this.FIELD_item = FIELD_item;
	}
	
	/*private Field field_fk;
	@JsonIgnore
	public Field getStock() {
		return this.field_fk;
	}
	public void setField (Field f) {
		this.field_fk = f;
	}*/
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "")
	//@JoinColumn(name="FIELD_ID")	
	private Set<Field> FIELD_set = new HashSet<Field>(0);
    	
	@JsonIgnore
	public Set<Field> getFIELD_set() {
		return FIELD_set;
	}
	
	public void setFIELD_set (Set<Field> FIELD_set) {
		this.FIELD_set = FIELD_set;
	}*/
	
	@Column(name = "value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Formula () {}
	
	public Formula (FormulaIdentity f_i, Project p, Resource r) {
		this.forumla_id = f_i; 
		this.projectId = p;
		this.resourceId = r;
	}
}
