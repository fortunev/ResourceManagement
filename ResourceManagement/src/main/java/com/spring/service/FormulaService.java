package com.spring.service;

import java.util.List;

import com.spring.model.Formula;
import com.spring.model.Project;
import com.spring.model.Resource;

public interface FormulaService {
	List<Object> save(Formula formula, Project p, Resource r);
   Formula get(int id);
   List<Formula> list();
   void update(int id, Formula formula);
   void delete(int id);
}
