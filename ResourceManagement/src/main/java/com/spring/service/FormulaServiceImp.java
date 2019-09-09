package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.FormulaDAO;
import com.spring.model.Formula;
import com.spring.model.Project;
import com.spring.model.Resource;

@Service
@Transactional(readOnly = true)
public class FormulaServiceImp {
	@Autowired
	private FormulaDAO formulaDao;
	
	@Transactional
	public List<Object> save(Formula formula, Project p, Resource r) {
		return formulaDao.save(formula, p, r);
	}
		
	public Formula get(int id) {
		return formulaDao.get(id);
	}
	
	public List<Formula> list() {
		return formulaDao.list();
	}
	
	@Transactional
	public void update(int id, Formula proj) {
		formulaDao.update(id, proj);
	}
	
	@Transactional
	public void delete(int id) {
		formulaDao.delete(id);
	}
}
