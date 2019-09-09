package com.spring.model;
import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class FormulaIdentity implements Serializable {
	private static final long serialVersionUID = 1L;

	//@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="projectId_PK", referencedColumnName="projectId_PK")
    private int projectId_key;
	/*@JsonIgnore
	public Project getprojectId_PK() {
		return projectId_PK;
	}

	public void setprojectId_PK(Project pROJECT_ID) {
		projectId_PK = pROJECT_ID;
	}*/

	//@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="resourceId_PK", referencedColumnName="resourceId_PK")
    private int resourceId_key;

	/*@JsonIgnore
	public Resource getresourceId_PK() {
		return resourceId_PK;
	}

	public void setresourceId_PK(Resource rESOURCE_ID) {
		resourceId_PK = rESOURCE_ID;
	}*/

    /*@Id
    @AttributeOverrides({
                @AttributeOverride(name = "projectId_PK",column = @Column(name="projectId_PK")),
                @AttributeOverride(name = "resourceId_PK", column = @Column(name="resourceId_PK"))
    })*/		

    private int fieldId_key;


	public FormulaIdentity () {}
    
	public FormulaIdentity (int p_id, int r_id) {
    	this.projectId_key = p_id;
    	this.resourceId_key = r_id;
    }
    
	public FormulaIdentity (int p_id, int r_id, int f_id) {
    	this.projectId_key = p_id;
    	this.resourceId_key = r_id;
    	this.fieldId_key = f_id;
    }
    
	public int getProjectId_key() {
		return projectId_key;
	}

	public void setProjectId_key(int projectId_PK) {
		this.projectId_key = projectId_PK;
	}

	public int getResourceId_key() {
		return resourceId_key;
	}

	public void setResourceId_key(int resourceId_PK) {
		this.resourceId_key = resourceId_PK;
	}
    
    public int getFieldId_key() {
		return fieldId_key;
	}

	public void setFieldId_key(int fieldId_key) {
		this.fieldId_key = fieldId_key;
	}
    @Override
    public boolean equals(Object o) { 	
    	if(o == null)
    		return false;
    	FormulaIdentity temp = (FormulaIdentity)o;
        if(this.projectId_key == temp.projectId_key && this.resourceId_key == temp.resourceId_key && this.fieldId_key == temp.fieldId_key)
        	return true;
        else
        	return false;
       
        /*if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormulaIdentity that = (FormulaIdentity) o;

        if (!projectId.equals(that.projectId)) return false;
        return resourceId.equals(that.resourceId);*/
    }

    /*@Override
    public int hashCode() {
        int result = projectId.hashCode();
        result = 31 * result + resourceId.hashCode();
        return result;
    }*/
    

   
    
}
