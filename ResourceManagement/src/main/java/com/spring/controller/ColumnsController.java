package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Columns;
import com.spring.service.ColumnsService;

@RestController
public class ColumnsController {
	@Autowired
	private ColumnsService colService;
	
	/* 	-----------------------------------	Columns	---------------------------------------	*/
	   /*---Add new column---*/
	   @PostMapping("/project/{p_id}/column")
	   public ResponseEntity<?> save(@PathVariable("p_id") int p_id, @RequestBody Columns col) {
	      long id = colService.save(col, p_id);
	      return ResponseEntity.ok().body("New Column has been saved with ID:" + id);
	   }
	      
	   /*---Add column to project---*/
	   @PostMapping("/project/{p_id}/column/{c_id}")
	   public ResponseEntity<?> add_to_Project(@PathVariable("p_id") int p_id, @PathVariable("c_id") int c_id) {
	      long id = colService.add_to_Project(c_id, p_id);	      
	      return ResponseEntity.ok().body("Column with ID:" + id+" added to project "+p_id);
	   }

	   /*---Get a column by id---*/
	   @GetMapping("/column/{id}")
	   public ResponseEntity<Columns> getColumns(@PathVariable("id") int id) {
		  Columns column = colService.get(id);
	      return ResponseEntity.ok().body(column);
	   }

	   /*---get all columns---*/
	   @GetMapping("/column")
	   public ResponseEntity<List<Columns>> listColumns() {
	      List<Columns> columns = colService.list();
	      return ResponseEntity.ok().body(columns);
	   }
	   
	   /*---get all columns from project id---*/
	   @GetMapping("/project/{p_id}/columns")
	   public ResponseEntity<List<Columns>> listColumns_FormulaId(@PathVariable("p_id") int p_id) {	   
	      List<Columns> columns = colService.list_project_id(p_id);
	      return ResponseEntity.ok().body(columns);
	   }

	   /*---Update a column by id---*/
	   @PutMapping("/column/{c_id}")
	   public ResponseEntity<?> update(@PathVariable("c_id") int c_id, @RequestBody Columns col) {
		  colService.update(c_id, col);
	      return ResponseEntity.ok().body("Column has been updated successfully.");
	   }

	   /*---Delete a column by id---*/
	   @DeleteMapping("/column/{c_id}")
	   public ResponseEntity<?> deleteColumns(@PathVariable("c_id") int c_id) {
		   colService.delete(c_id);
		   return ResponseEntity.ok().body("Column has been deleted successfully.");
	   }
	   
	   
}
