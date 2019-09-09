package com.spring.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Project;
import com.spring.model.Resource;
import com.spring.service.ProjectService;

@RestController
public class ProjectController {
		@Autowired
	   private ProjectService projectService;
	   
	/*	---------------------------	Project	----------------------------------	*/
	   /*---Add new project---*/
	   @PostMapping("/project")
	   public ResponseEntity<?> save(@RequestBody Project proj) {
	      long id = projectService.save(proj);
	      return ResponseEntity.ok().body("New Project has been saved with ID:" + id);
	   }

	   /*---Get a project by id---*/
	   /*@GetMapping("/project/{id}")
	   public ResponseEntity<Project> getProject(@PathVariable("id") int id) {
		  Project project = projectService.get(id);
	      return ResponseEntity.ok().body(project);
	   }*/

	   /*---get all projects---*/
	   @GetMapping("/project")
	   public ResponseEntity<List<Project>> listProjects() {
	      List<Project> projects = projectService.list();
	      return ResponseEntity.ok().body(projects);
	   }

	   /*---Update a project by id---*/
	   @PutMapping("/project/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Project proj) {
		  projectService.update(id, proj);
	      return ResponseEntity.ok().body("Project has been updated successfully.");
	   }

	   /*---Delete a project by id---*/
	   @DeleteMapping("/project/{id}")
	   public ResponseEntity<?> deleteProject(@PathVariable("id") int id) {
		   projectService.delete(id);
		   return ResponseEntity.ok().body("Project has been deleted successfully.");
	   }
}
