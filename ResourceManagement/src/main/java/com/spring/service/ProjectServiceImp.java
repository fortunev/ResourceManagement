package com.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ProjectDAO;
import com.spring.model.Project;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImp implements ProjectService {
	@Autowired
	private ProjectDAO projDao;
	
	@Transactional
	public long save(Project proj) {
		return projDao.save(proj);
	}
	
	public Project get(int id) {
		return projDao.get(id);
	}
	
	public List<Project> list() {
		return projDao.list();
	}
	
	@Transactional
	public void update(int id, Project proj) {
		projDao.update(id, proj);
	}
	
	@Transactional
	public void delete(int id) {
		projDao.delete(id);
	}

}
