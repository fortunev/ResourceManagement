package com.spring.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Cell;
import com.spring.model.Project;
import com.spring.model.Project_Resource;
import com.spring.model.Resource;

@Repository
public class ResourceDAOImp implements ResourceDAO{
	@Autowired
   private SessionFactory sessionFactory;

   public long save(Resource res) {
      sessionFactory.getCurrentSession().save(res);
      return res.getResourceId();
   }
   
   public long save_add_to_Proj(int projId, Resource res) {
	   	Session ses = sessionFactory.getCurrentSession();
	   	ses.save(res);
  		Project existingProject = (Project) ses.get(Project.class, projId);
  		existingProject.addResource(res);
  		ses.flush();
  		return res.getResourceId();
   }
   
   public long add_to_Proj(int projId, int resId) {
		Session ses = sessionFactory.getCurrentSession();
  		Project existingProject = (Project) ses.get(Project.class, projId);
  		Resource existingResource = (Resource) ses.get(Resource.class, resId);
  		Project_Resource pr = new Project_Resource ( existingProject, existingResource );
  		existingProject.getResourcesSet().add( pr );
  		existingResource.getProjectsSet().add( pr );
  		ses.save(existingResource);
  		ses.save(existingProject);  
  		ses.flush();
  		return existingResource.getResourceId();
	}

   public Resource get(int id) {
      return sessionFactory.getCurrentSession().get(Resource.class, id);
   }

   public List<Resource> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Resource> cq = cb.createQuery(Resource.class);
      Root<Resource> root = cq.from(Resource.class);
      cq.select(root);
      Query<Resource> query = session.createQuery(cq);
      return query.getResultList();
   }
   
   public List<Project_Resource> list_proj_id(int p_id) {
	   Session session = sessionFactory.getCurrentSession();
	   Query query = session.createQuery("FROM Project as p WHERE p.projectId="+p_id);	    
	   Project proj = (Project) query.uniqueResult();
	   List<Project_Resource> l = new ArrayList<Project_Resource>();
	   l.addAll(proj.list_proj_res());
	   return l;
   }
   
   public void update(int id, Resource res) {
      Session session = sessionFactory.getCurrentSession();
      Resource res1 = session.byId(Resource.class).getReference(id);
      res1.setResourceName(res.getResourceName());
      res1.setResourceCode(res.getResourceCode());
      session.flush();
   }

   @SuppressWarnings("deprecation")
public void delete(int id) {
	   Session ses = sessionFactory.getCurrentSession();
      Resource res = ses.byId(Resource.class).getReference(id);
      Query get_p = ses.createQuery("FROM Project_Resource WHERE res_resourceID="+id);
      List<?> p = get_p.getResultList();
      for (int i = 0; i < p.size(); i++) {
    	  Project proj = ses.byId(Project.class).getReference(((Project_Resource) p.get(i)).getProj().getProjectId());
    	  Query<?> query = ses.createSQLQuery("delete from Project_Resource WHERE res_resourceID="+id+" and proj_projectId="+proj.getProjectId());
          int del = query.executeUpdate();
          proj.removeResource(res);
    	  ses.update(proj);
      }
      Query get_cells = ses.createQuery("FROM Cell WHERE resourceID_FK="+id);
      List<?> cells = get_cells.getResultList();
      if (cells.size() > 0) {
      for (int i = 0; i < cells.size(); i++) {
    	  Cell c = ses.byId(Cell.class).getReference(((Cell) cells.get(i)).getCellId());
    	  Query<?> query = ses.createSQLQuery("delete from Cell WHERE resourceID_FK="+id);
          int del = query.executeUpdate();
      }
      }
      Query<?> del_r = ses.createSQLQuery("delete FROM Resource WHERE resourceID="+id);
      int r = del_r.executeUpdate();
      ses.flush();
   }
   
   @SuppressWarnings("deprecation")
public void delete(int p_id, int id) {
      Session ses = sessionFactory.getCurrentSession();
      Project existingProject = (Project) ses.get(Project.class, p_id);
      Resource res = ses.byId(Resource.class).getReference(id);
      Query<?> query = ses.createSQLQuery("delete from Project_Resource WHERE res_resourceID="+id+" and proj_projectId="+p_id);
      int del = query.executeUpdate();
      existingProject.removeResource(res);
      ses.update(existingProject);
      ses.flush();
   }

}
