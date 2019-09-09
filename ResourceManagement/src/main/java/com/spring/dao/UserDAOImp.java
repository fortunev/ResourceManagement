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

import com.spring.model.User;

@Repository
public class UserDAOImp implements UserDAO{
	@Autowired
   private SessionFactory sessionFactory;

   public long save(User user) {
      sessionFactory.getCurrentSession().save(user);
      return user.getUserId();
   }

   public User get(int id) {
      return sessionFactory.getCurrentSession().get(User.class, id);
   }

   public List<User> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<User> cq = cb.createQuery(User.class);
      Root<User> root = cq.from(User.class);
      cq.select(root);
      Query<User> query = session.createQuery(cq);
      return query.getResultList();
   }

   public void update(int id, User user) {
      Session session = sessionFactory.getCurrentSession();
      User user1 = session.byId(User.class).getReference(id);
      user1.setUserName(user.getUserName());
      user1.setUserPassword(user.getUserPassword());
      session.flush();
   }

   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      User book = ((UserDAOImp) session.byId(User.class)).get(id);
      session.delete(book);
   }
}
