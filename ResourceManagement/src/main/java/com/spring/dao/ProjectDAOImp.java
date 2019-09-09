package com.spring.dao;

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
      Session session = sessionFactory.getCurrentSession();
      Project proj = session.byId(Project.class).load(id);
      session.delete(proj);
   }
}
