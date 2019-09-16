package com.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Columns;
import com.spring.model.Cell;
import com.spring.model.Project;
import com.spring.model.Resource;

import javassist.bytecode.Descriptor.Iterator;

@Repository
public class ColumnsDAOImp implements ColumnsDAO{
	@Autowired
   private SessionFactory sessionFactory;

	public long save(Columns col, int p) {
		Session ses = sessionFactory.getCurrentSession();
		Project existingProject = (Project) ses.get(Project.class, p);
		col.setProjectId(existingProject);
		sessionFactory.getCurrentSession().save(col);		
  		existingProject.getColumnsSet().add(col);
  		ses.save(existingProject);  
  		return col.getColId();
	}

	public long add_to_Project(int c, int p) {
		Session ses = sessionFactory.getCurrentSession();
		Project existingProject = (Project) ses.get(Project.class, p);
		Columns existingColumn = (Columns) ses.get(Columns.class, c);
		existingColumn.setProjectId(existingProject);
		existingProject.getColumnsSet().add(existingColumn);
  		ses.save(existingProject);  
  		return existingColumn.getColId();
	}

	public Columns get(int id) {
	      return sessionFactory.getCurrentSession().get(Columns.class, id);
	}

	public List<Columns> list() {
	      Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Columns> cq = cb.createQuery(Columns.class);
	      Root<Columns> root = cq.from(Columns.class);
	      cq.select(root);
	      Query<Columns> query = session.createQuery(cq);
	      return query.getResultList();
	}

	public List<Columns> list_project_id(int p_id) {
		Session session = sessionFactory.getCurrentSession();
	   Query<?> query = session.createQuery("FROM Project as p WHERE p.projectId="+p_id);	    
	   Project proj = (Project) query.uniqueResult();
	   List<Columns> l = new ArrayList<Columns>();
	   l.addAll(proj.getColumnsSet());
	   return l;
	}

	public void update(int c, Columns col) {
		Session session = sessionFactory.getCurrentSession();
	      Columns col1 = session.byId(Columns.class).getReference(c);
	      col1.setCol_name(col.getCol_name());
	      col1.setCol_type(col.getCol_type());
	      col1.setCol_scope(col.getCol_scope());
	      col1.setCol_formula(col.getCol_formula());
	      session.flush();
	}

	public void delete(int c) {
		Session ses = sessionFactory.getCurrentSession();
		Columns col = ses.byId(Columns.class).getReference(c);
		col.getProjectId().getColumnsSet().remove(col);
		col.setProjectId(null);
		Query<?> query = ses.createQuery("FROM Cell as c WHERE c.colId="+c);
		List<?> cells = query.getResultList();
		if (cells.size() > 0) {
		for (int i = 0; i < cells.size(); i++) {
			Cell cell1 = ses.byId(Cell.class).getReference(((Cell) cells.get(i)).getCellId());
			cell1.getResourceId().getCellsSet().remove(cells.get(i));
			cell1.setResourceId(null);
			cell1.getColId().getCellsSet().remove(cell1);
			cell1.setColId(null);
			ses.delete(cell1);
		}
		}
		ses.delete(col);
		ses.flush();
	}
}
