package com.spring.service;

import java.util.List;
import com.spring.model.Resource;

public interface ResourceService {	
   long save(Resource res);
   long save_add_to_Proj(int p_id, Resource res);
   long add_to_Proj (int projId, int res_Id);
   Resource get(int id);
   List<Resource> list();
   List<Resource> list_proj_id(int p_id);
   void update(int id, Resource res);
   void delete(int id);
}
