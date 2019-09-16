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
import com.spring.model.Project_Resource;
import com.spring.model.Resource;
import com.spring.service.ResourceService;

@RestController
public class ResourceController {
	@Autowired
	   private ResourceService resourceService;
	   
	 /* 	-----------------------------------	Resource	---------------------------------------	*/
	   /*---Add new resource---*/
	   @PostMapping("/resource")
	   public ResponseEntity<?> save(@RequestBody Resource res) {
	      long id = resourceService.save(res);
	      return ResponseEntity.ok().body("New Resource has been saved with ID:" + id);
	   }
	   
	   /*---Add new resource and add to project---*/
	   @PostMapping("/project/{id}/resource")
	   public ResponseEntity<?> save_add_to_Proj(@PathVariable("id") int p_id, @RequestBody Resource res) {
	      long id = resourceService.save_add_to_Proj(p_id, res);	      
	      return ResponseEntity.ok().body("New Resource has been saved with ID:" + id+". Added to project "+p_id);
	   }
	   
	   /*---Add resource to project---*/
	   @PostMapping("/project/{p_id}/resource/{r_id}")
	   public ResponseEntity<?> add_to_Proj(@PathVariable("p_id") int p_id, @PathVariable("r_id") int r_id) {
	      long id = resourceService.add_to_Proj(p_id, r_id);	      
	      return ResponseEntity.ok().body("Resource with ID:" + id+" added to project "+p_id);
	   }

	   /*---Get a resource by id---*/
	   @GetMapping("/resource/{id}")
	   public ResponseEntity<Resource> getResource(@PathVariable("id") int id) {
		  Resource resource = resourceService.get(id);
	      return ResponseEntity.ok().body(resource);
	   }

	   /*---get all resources---*/
	   @GetMapping("/resource")
	   public ResponseEntity<List<Resource>> listResources() {
	      List<Resource> resources = resourceService.list();
	      return ResponseEntity.ok().body(resources);
	   }
	   
	   /*---get all resources from project id---*/
	   @GetMapping("/project/{id}")
	   public ResponseEntity<List<Project_Resource>> listResources_ProjId(@PathVariable("id") int p_id) {	   
	      List<Project_Resource> resources = resourceService.list_proj_id(p_id);
	      return ResponseEntity.ok().body(resources);
	   }

	   /*---Update a resource by id---*/
	   @PutMapping("/resource/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Resource res) {
		  resourceService.update(id, res);
	      return ResponseEntity.ok().body("Resource has been updated successfully.");
	   }

	   /*---Delete a resource by id---*/
	   @DeleteMapping("/resource/{id}")
	   public ResponseEntity<?> deleteResource(@PathVariable("id") int id) {
		   resourceService.delete(id);
		   return ResponseEntity.ok().body("Resource has been deleted successfully.");
	   }
	   
	   /*---Delete a resource by id from project id---*/
	   @DeleteMapping("/project/{p_id}/resource/{r_id}")
	   public ResponseEntity<?> deleteResource(@PathVariable("p_id") int p_id, @PathVariable("r_id") int r_id) {
		   resourceService.delete(p_id, r_id);
		   return ResponseEntity.ok().body("Resource has been deleted successfully.");
	   }
}
