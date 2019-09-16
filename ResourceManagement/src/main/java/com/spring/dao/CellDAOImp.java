package com.spring.dao;

import java.util.ArrayList;
import java.util.Iterator;
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

@Repository
public class CellDAOImp implements CellDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public long save(Cell cell, int col_id, int r_id) {
		Session ses = sessionFactory.getCurrentSession();
		Resource existingResource = (Resource) ses.get(Resource.class, r_id);
		Columns existingColumn = (Columns) ses.get(Columns.class, col_id);
		ses.save(cell);		
		cell.setColId(existingColumn);
		cell.setResourceId(existingResource);		
  		existingResource.getCellsSet().add(cell);
  		existingColumn.getCellsSet().add(cell);
  		ses.save(existingResource);  
  		ses.save(existingColumn);  
  		return cell.getCellId();
	}

	public long add_to_Resource(int cell_id, int r_id) {
		Session ses = sessionFactory.getCurrentSession();
		Resource existingResource = (Resource) ses.get(Resource.class, r_id);
		Cell existingCell = (Cell) ses.get(Cell.class, cell_id);
		existingCell.setResourceId(existingResource);	
  		existingResource.getCellsSet().add(existingCell);
  		ses.save(existingResource);  
  		ses.save(existingCell);  
  		return existingCell.getCellId();
	}

	public long add_to_Columns(int cell_id, int col_id) {
		Session ses = sessionFactory.getCurrentSession();
		Columns existingColumn = (Columns) ses.get(Columns.class, col_id);
		Cell existingCell = (Cell) ses.get(Cell.class, cell_id);
		existingCell.setColId(existingColumn);	
		existingColumn.getCellsSet().add(existingCell);
  		ses.save(existingColumn);  
  		ses.save(existingCell);  
  		return existingCell.getCellId();
	}

	public long add_to_Res_Col(int cell_id, int r_id, int col_id) {
		Session ses = sessionFactory.getCurrentSession();
		Cell existingCell = (Cell) ses.get(Cell.class, cell_id);
		Resource existingResource = (Resource) ses.get(Resource.class, r_id);
		Columns existingColumn = (Columns) ses.get(Columns.class, col_id);
		existingCell.setColId(existingColumn);
		existingCell.setResourceId(existingResource);		
		ses.save(existingCell);		
  		existingResource.getCellsSet().add(existingCell);
  		existingColumn.getCellsSet().add(existingCell);
  		ses.save(existingResource);  
  		ses.save(existingColumn);  
  		return existingCell.getCellId();
	}

	public Cell get(int id) {
	      return sessionFactory.getCurrentSession().get(Cell.class, id);
	}

	public List<Cell> list() {
		Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Cell> cq = cb.createQuery(Cell.class);
      Root<Cell> root = cq.from(Cell.class);
      cq.select(root);
      Query<Cell> query = session.createQuery(cq);
      return query.getResultList();
	}

	public List<Cell> list_resource_id(int r_id) {
		Session session = sessionFactory.getCurrentSession();
	    Query<?> query = session.createQuery("FROM Resource as r WHERE r.resourceId="+r_id);	    
	    Resource res = (Resource) query.uniqueResult();
	    List<Cell> l = new ArrayList<Cell>();
	    l.addAll(res.getCellsSet());
	    return l;
	}

	public List<Cell> list_column_id(int col_id) {
		Session session = sessionFactory.getCurrentSession();
	    Query<?> query = session.createQuery("FROM Columns as c WHERE c.colId="+col_id);	    
	    Columns col = (Columns) query.uniqueResult();
	    List<Cell> l = new ArrayList<Cell>();
	    l.addAll(col.getCellsSet());
	    return l;
	}

	public void update(int cell_id, Cell col) {
		Session ses = sessionFactory.getCurrentSession();
		Cell existingCell = (Cell) ses.get(Cell.class, cell_id);
		existingCell.setValue(col.getValue());		
	    ses.flush();		
	}

	public void delete(int cell_id) {
		Session ses = sessionFactory.getCurrentSession();
		Cell cell = ses.byId(Cell.class).getReference(cell_id);
		cell.getResourceId().getCellsSet().remove(cell);
		cell.setResourceId(null);
		cell.getColId().getCellsSet().remove(cell);
		cell.setColId(null);
		ses.delete(cell);		
		ses.flush();
	}
}
