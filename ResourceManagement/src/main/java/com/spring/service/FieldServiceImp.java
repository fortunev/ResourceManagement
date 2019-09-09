package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.FieldDAO;
import com.spring.model.Field;

@Service
@Transactional(readOnly = true)
public class FieldServiceImp implements FieldService{
	@Autowired
	private FieldDAO fieldDao;
	
	@Transactional
	public long save(Field field) {
		return fieldDao.save(field);
	}
	
	@Transactional
	public long save_add_to_Formula(int p_id, int r_id, Field field) {
		return fieldDao.save_add_to_Formula(p_id, r_id, field);
	}
	
	@Transactional
	public long add_to_Formula (int p_id, int r_id, int field) {
		return fieldDao.add_to_Formula(p_id, r_id, field);
	}
	
	public Field get(int id) {
		return fieldDao.get(id);
	}
	
	public List<Field> list() {
		return fieldDao.list();
	}
	
	public List<Field> list_formula_id(int f_id) {
		return fieldDao.list_formula_id(f_id);
	}
	
	@Transactional
	public void update(int id, Field proj) {
		fieldDao.update(id, proj);
	}
	
	@Transactional
	public void delete(int id) {
		fieldDao.delete(id);
	}
}
