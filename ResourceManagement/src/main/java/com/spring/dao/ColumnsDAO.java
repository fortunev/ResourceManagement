package com.spring.dao;

import java.util.List;

import com.spring.model.Columns;

public interface ColumnsDAO {
   long save(Columns col, int p);
   long add_to_Project(int c, int p);
   Columns get(int id);
   List<Columns> list();
   List<Columns> list_project_id(int p_id); 
   void update(int p, Columns col);
   void delete(int c);
}
