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

import com.spring.model.Field;
import com.spring.model.Formula;
import com.spring.model.FormulaIdentity;
import com.spring.model.Project;
import com.spring.model.Resource;

@Repository
public class FormulaDAOImp implements FormulaDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Object> save(Formula formula, Project p, Resource r) {
		sessionFactory.getCurrentSession().save(formula);
		FormulaIdentity fi = new FormulaIdentity (p.getProjectId(), r.getResourceId());
		Formula f = new Formula (fi, p, r);
		List<Object> l = new ArrayList<Object>();
		l.add(f.getProjectId().getProjectId());
		l.add(f.getResourceId().getResourceId());
	    return l;
	}

	public Formula get(int id) {
		return sessionFactory.getCurrentSession().get(Formula.class, id);
	}

	public List<Formula> list() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Formula> cq = cb.createQuery(Formula.class);
      	Root<Formula> root = cq.from(Formula.class);
      	cq.select(root);
      	Query<Formula> query = session.createQuery(cq);
      	return query.getResultList();
	}
	
	public List<Field> list_formula_id(int f_id) {
		Session session = sessionFactory.getCurrentSession();
	   Query query = session.createQuery("FROM Formula as f WHERE f.formulaId="+f_id);	    
	   Formula f = (Formula) query.uniqueResult();
	   List<Field> l = new ArrayList<Field>();
	   l.add(f.getFIELD_item());

	   return l;
	}
	
	public void update(int id, Formula formula) {	// update foreign keys too?
		Session session = sessionFactory.getCurrentSession();
		Formula res1 = session.byId(Formula.class).getReference(id);
		  res1.setValue(formula.getValue());
		  session.flush();		
	}

	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Formula res = ((FormulaDAOImp) session.byId(Formula.class)).get(id);
	      session.delete(res);
		
	}

}
