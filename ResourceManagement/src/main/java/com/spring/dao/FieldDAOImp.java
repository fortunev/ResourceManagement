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
public class FieldDAOImp implements FieldDAO{
	@Autowired
   private SessionFactory sessionFactory;

   public long save(Field field) {
      sessionFactory.getCurrentSession().save(field);
      return field.getFieldId();
   }
   
   public long save_add_to_Formula(int p_id, int r_id, Field field) {
	   Session ses = sessionFactory.getCurrentSession();
	   	sessionFactory.getCurrentSession().save(field);
	   	FormulaIdentity fi = new FormulaIdentity (p_id, r_id);
	   	Formula existingFormula = (Formula) ses.get(Formula.class, fi);
		existingFormula.setFIELD_item(field);
		System.out.println("Field_item - "+existingFormula.getFIELD_item().toString());
		ses.save(existingFormula);
	   return field.getFieldId();
   }
   
   public long add_to_Formula(int p_id, int r_id, int field_id) {
		Session ses = sessionFactory.getCurrentSession();
		FormulaIdentity fi = new FormulaIdentity (p_id, r_id);
	   	Formula existingFormula = (Formula) ses.get(Formula.class, fi);
 		Field existingField = (Field) ses.get(Field.class, field_id);
 		existingFormula.setFIELD_item(existingField);
 		System.out.println("FIELD_Item - "+existingFormula.getFIELD_item().toString());
 		ses.save(existingFormula);  
 		return existingField.getFieldId();
	}


   public Field get(int id) {
      return sessionFactory.getCurrentSession().get(Field.class, id);
   }

   public List<Field> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Field> cq = cb.createQuery(Field.class);
      Root<Field> root = cq.from(Field.class);
      cq.select(root);
      Query<Field> query = session.createQuery(cq);
      return query.getResultList();
   }
   
   public List<Field> list_formula_id(int f_id) {
	   Session session = sessionFactory.getCurrentSession();
	   Query query = session.createQuery("FROM Project as p WHERE p.projectId="+f_id);	   
	   List<Field> l = new ArrayList<Field>();
	   Formula f = (Formula) query.uniqueResult();	   
	   l.add(f.getFIELD_item());
	   return l;
   }

   public void update(int id, Field field) {
      Session session = sessionFactory.getCurrentSession();
      Field field1 = session.byId(Field.class).getReference(id);
      field1.setFieldName(field.getFieldName());
      field1.setFieldType(field.getFieldType());
      field1.setFieldScope(field.getFieldScope());
      field1.setFieldFormula(field.getFieldFormula());
      session.flush();
   }

   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      Field field = ((FieldDAOImp) session.byId(Field.class)).get(id);
      session.delete(field);
   }
}
