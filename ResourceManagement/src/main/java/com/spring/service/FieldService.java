package com.spring.service;

import java.util.List;

import com.spring.model.Field;

public interface FieldService {
   long save(Field field);
   long save_add_to_Formula(int p_id, int r_id, Field field);
   long add_to_Formula (int p_id, int r_id, int field);
   Field get(int id);
   List<Field> list();
   List<Field> list_formula_id(int f_id);
   void update(int id, Field field);
   void delete(int id);
}
