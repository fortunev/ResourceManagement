package com.spring.dao;

import java.util.List;

import com.spring.model.Project_Resource;
import com.spring.model.Resource;

public interface ResourceDAO {
   long save(Resource resource);
   long save_add_to_Proj(int projId, Resource res);
   long add_to_Proj (int projId, int res_Id);
   Resource get(int id);
   List<Resource> list();
   List<Project_Resource> list_proj_id(int p_id);
   void update(int id, Resource resource);
   void delete(int id);
   void delete(int p_id, int id);
}
