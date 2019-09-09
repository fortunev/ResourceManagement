package com.spring.dao;

import java.util.List;

import com.spring.model.Field;
import com.spring.model.Formula;
import com.spring.model.Project;
import com.spring.model.Resource;

public interface FormulaDAO {
   List<Object> save(Formula formula, Project p, Resource r);
   Formula get(int id);
   List<Formula> list();
   List<Field> list_formula_id(int f_id);
   void update(int id, Formula formula);
   void delete(int id);
}
