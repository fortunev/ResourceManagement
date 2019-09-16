package com.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ResourceDAO;
import com.spring.model.Project_Resource;
import com.spring.model.Resource;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImp implements ResourceService {
	@Autowired
	private ResourceDAO resDao;
	
	@Transactional
	public long save(Resource res) {
		return resDao.save(res);
	}
	@Transactional
	public long save_add_to_Proj(int projId, Resource res) {
		return resDao.save_add_to_Proj(projId, res);
	}
	@Transactional
	public long add_to_Proj(int projId, int res_Id) {
		return resDao.add_to_Proj(projId, res_Id);
	}
	public Resource get(int id) {
		return resDao.get(id);
	}
	
	public List<Resource> list() {
		return resDao.list();
	}
	
	public List<Project_Resource> list_proj_id(int p_id) {
		return resDao.list_proj_id(p_id);
	}
	
	@Transactional
	public void update(int id, Resource res) {
		resDao.update(id, res);
	}
	
	@Transactional
	public void delete(int id) {
		resDao.delete(id);
	}
	
	@Transactional
	public void delete(int p_id, int id) {
		resDao.delete(p_id, id);
	}
}
