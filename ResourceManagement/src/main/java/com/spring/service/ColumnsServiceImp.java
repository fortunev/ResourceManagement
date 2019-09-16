package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ColumnsDAO;
import com.spring.model.Columns;

@Service
@Transactional(readOnly = true)
public class ColumnsServiceImp implements ColumnsService{
	@Autowired
	private ColumnsDAO colDao;
	
	@Transactional
	public long save(Columns col, int p){
		return colDao.save(col, p);
	}
	
	@Transactional
	public long add_to_Project(int c, int p) {
		return colDao.add_to_Project(c, p);
	}
	
	public Columns get(int id) {
		return colDao.get(id);
	}
	
	public List<Columns> list() {
		return colDao.list();
	}
	
	public List<Columns> list_project_id(int p_id) {
		return colDao.list_project_id(p_id);
	}
	
	@Transactional
	public void update(int p, Columns col) {
		colDao.update(p, col);
	}
	
	@Transactional
	public void delete(int p) {
		colDao.delete(p);
	}
}
