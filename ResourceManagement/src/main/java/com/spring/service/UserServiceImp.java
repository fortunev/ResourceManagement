package com.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.UserDAO;
import com.spring.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
	@Autowired
	private UserDAO userDao;
	
	@Transactional
	public long save(User user) {
		return userDao.save(user);
	}
	
	public User get(int id) {
		return userDao.get(id);
	}
	
	public List<User> list() {
		return userDao.list();
	}
	
	@Transactional
	public void update(int id, User user) {
		userDao.update(id, user);
	}
	
	@Transactional
	public void delete(int id) {
		userDao.delete(id);
	}

}
