package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.CellDAO;
import com.spring.dao.CellDAO;
import com.spring.model.Columns;
import com.spring.model.Cell;
import com.spring.model.Project;
import com.spring.model.Resource;

@Service
@Transactional(readOnly = true)
public class CellServiceImp implements CellService {
	@Autowired
	private CellDAO cellDao;

	public List<Integer> list_column_id_resource_id(int c_id, int r_id) {
		return cellDao.list_column_id_resource_id(c_id, r_id);
	}
	
	@Transactional
	public long save(Cell cell, int col_id, int r_id) {
		return cellDao.save(cell, col_id, r_id);
	}
	
	@Transactional
	public long add_to_Resource(int cell_id, int r_id){
		return cellDao.add_to_Resource(cell_id, r_id);
	}
	
	@Transactional
	public long add_to_Columns(int cell_id, int col_id){
		return cellDao.add_to_Columns(cell_id, col_id);
	}
	
	@Transactional
	public long add_to_Res_Col (int cell_id, int r_id, int col_id){
		return cellDao.add_to_Res_Col (cell_id, r_id, col_id);
	}
	
	public Cell get(int id) {
		return cellDao.get(id);
	}
	
	public List<Cell> list() {
		return cellDao.list();
	}
	
	public List<Cell> list_resource_id(int r_id) {
		return cellDao.list_resource_id(r_id);
	}
	
	public List<Cell> list_column_id(int col_id) {
		return cellDao.list_column_id(col_id);
	}
	
	@Transactional
	public void update(int cell_id, Cell cell) {
		cellDao.update(cell_id, cell);
	}
	
	@Transactional
	public void delete(int cell_id) {
		cellDao.delete(cell_id);
	}

}
