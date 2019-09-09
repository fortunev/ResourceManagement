package com.spring.dao;

import java.util.List;

import com.spring.model.Field;

public interface FieldDAO {
   long save(Field field);
   long save_add_to_Formula(int p_id, int r_id, Field field);
   long add_to_Formula(int p_id, int r_id, int field);
   Field get(int id);
   List<Field> list();
   void update(int id, Field field);
   void delete(int id);
   List<Field> list_formula_id(int f_id); 
}
