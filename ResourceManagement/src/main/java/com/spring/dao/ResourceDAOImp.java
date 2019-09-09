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

import com.spring.model.Project;
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
	   	sessionFactory.getCurrentSession().save(res);
  		Project existingProject = (Project) ses.get(Project.class, projId);
  		existingProject.getResourcesSet().add(res);
  		System.out.println("ResourceSet - "+existingProject.getResourcesSet().toString());
  		ses.save(existingProject);  
  		return res.getResourceId();
   }
   
   public long add_to_Proj(int projId, int resId) {
		Session ses = sessionFactory.getCurrentSession();
  		Project existingProject = (Project) ses.get(Project.class, projId);
  		Resource existingResource = (Resource) ses.get(Resource.class, resId);
  		existingProject.getResourcesSet().add(existingResource);
  		System.out.println("ResourceSet - "+existingProject.getResourcesSet().toString());
  		ses.save(existingProject);  
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
   
   public List<Resource> list_proj_id(int p_id) {
	   Session session = sessionFactory.getCurrentSession();
	   Query query = session.createQuery("FROM Project as p WHERE p.projectId="+p_id);	    
	   Project proj = (Project) query.uniqueResult();
	   List<Resource> l = new ArrayList<Resource>();
	   l.addAll(proj.getResourcesSet());

	   return l;
   }
   
   public void update(int id, Resource res) {
      Session session = sessionFactory.getCurrentSession();
      Resource res1 = session.byId(Resource.class).getReference(id);
      res1.setResourceName(res.getResourceName());
      res1.setResourceCode(res.getResourceCode());
      res1.setProjectsSet(res.getProjectsSet());
      session.flush();
   }

   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      Resource res = ((ResourceDAOImp) session.byId(Resource.class)).get(id);
      session.delete(res);
   }

}
