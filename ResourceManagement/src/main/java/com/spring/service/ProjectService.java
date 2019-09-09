package com.spring.service;

import java.util.List;
import com.spring.model.Project;

public interface ProjectService {	
   long save(Project project);
   Project get(int id);
   List<Project> list();
   void update(int id, Project project);
   void delete(int id);
}
