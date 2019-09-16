package com.spring.dao;

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

import com.spring.model.Cell;
import com.spring.model.Columns;
import com.spring.model.Project;
import com.spring.model.Project_Resource;
import com.spring.model.Resource;

@Repository
public class ProjectDAOImp implements ProjectDAO{
	@Autowired
   private SessionFactory sessionFactory;

   public long save(Project proj) {
      sessionFactory.getCurrentSession().save(proj);
      return proj.getProjectId();
   }

   public Project get(int id) {
      return sessionFactory.getCurrentSession().get(Project.class, id);
   }

   public List<Project> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Project> cq = cb.createQuery(Project.class);
      Root<Project> root = cq.from(Project.class);
      cq.select(root);
      Query<Project> query = session.createQuery(cq);
      return query.getResultList();
   }

   public void update(int id, Project proj) {
      Session session = sessionFactory.getCurrentSession();
      Project proj1 = session.byId(Project.class).getReference(id);
      proj1.setProjectName(proj.getProjectName());
      proj1.setResourcesSet(proj.getResourcesSet());
      session.flush();
   }

   public void delete(int id) {
      Session ses = sessionFactory.getCurrentSession();
      Project existing_proj = ses.byId(Project.class).getReference(id);
      Query get_cols = ses.createQuery("FROM Columns WHERE projectID_FK="+id);
      List<?> cols = get_cols.getResultList();
      System.out.println("\nColumns SIZE: "+cols.size());
      if (cols.size() > 0) {
      for (int i = 0; i < cols.size(); i++) {
    	  Columns c = ses.byId(Columns.class).getReference(((Columns) cols.get(i)).getColId());
          Query<?> get_cells = ses.createQuery("FROM Cell as c WHERE c.colId="+c.getColId());
          List<?> cells = get_cells.getResultList();
          if (cells.size() > 0) {
        	  for (int j = 0; j < cells.size(); j++) {
        		  Cell cell1 = ses.byId(Cell.class).getReference(((Cell) cells.get(j)).getCellId());
        		  cell1.getResourceId().getCellsSet().remove(cells.get(j));
        		  cell1.setResourceId(null);
        		  cell1.getColId().getCellsSet().remove(cell1);
        		  cell1.setColId(null);
        		  Query<?> query = ses.createSQLQuery("delete FROM Cell WHERE cellID="+cell1.getCellId());
        		  int del = query.executeUpdate();
        		  ses.detach(cell1);
        	  }
          }
          c.setProjectId(null);
          existing_proj.getColumnsSet().remove(c);          
          ses.detach(c);
    	  Query<?> query = ses.createSQLQuery("delete FROM Columns WHERE projectID_FK="+id+" and colId="+c.getColId());
          int del = query.executeUpdate();
      }
      }
      Query get_p = ses.createQuery("FROM Project_Resource WHERE proj_projectId="+id);
      List<?> p = get_p.getResultList();
      for (int i = 0; i < p.size(); i++) {
    	  Project proj = ses.byId(Project.class).getReference(((Project_Resource) p.get(i)).getProj().getProjectId());
    	  Resource res = ses.byId(Resource.class).getReference(((Project_Resource) p.get(i)).getRes().getResourceId());
    	  Iterator<Project_Resource> it_r = proj.getResourcesSet().iterator();
    	  proj.removeResource(res);
    	  ses.flush();
    	  Query<?> query = ses.createSQLQuery("delete FROM Project_Resource WHERE proj_projectId="+proj.getProjectId());
          int del = query.executeUpdate();
          proj.getResourcesSet().clear();
    	  ses.update(proj);
    	  ses.flush();
      }      
      Query<?> del_p = ses.createSQLQuery("delete FROM Project WHERE projectID="+id);
      int r = del_p.executeUpdate();
      ses.flush();
   }
}
