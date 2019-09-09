package com.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Field", uniqueConstraints={@UniqueConstraint(columnNames={"fieldName"})})
public class Field implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fieldId")
	private int fieldId;
	
	@Column(name = "fieldName") //Unique PK
	private String fieldName;
	
	@Column(name = "fieldType")
	private String fieldType;
	
	@Column(name = "fieldFormula")
	private String fieldFormula;
	
	@Column(name = "fieldScope")
	private String fieldScope;
	
	
	/*@ManyToOne	
	@JoinColumn(name="PROJECT_ID")
	private Project PROJECT_ID;
	@JsonIgnore
	public Project getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(Project pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}*/
	
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "FIELD_set")
	//@JoinColumn(name = "FIELD_ID", nullable = false)
	/*private Formula formula;
	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}*/

	/*private Set<Field> FIELD_set = new HashSet<Field>(0);	
	@JsonIgnore
	public Set<Field> getFIELD_set() {
		return FIELD_set;
	}
	
	public void setFIELD_set (Set<Field> FIELD_set) {
		this.FIELD_set = FIELD_set;
	}*/
	
	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldFormula() {
		return fieldFormula;
	}

	public void setFieldFormula(String fieldFormula) {
		this.fieldFormula = fieldFormula;
	}

	public String getFieldScope() {
		return fieldScope;
	}

	public void setFieldScope(String fieldScope) {
		this.fieldScope = fieldScope;
	}
}
