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

import com.spring.model.Field;
import com.spring.service.FieldService;

@RestController
public class FieldController {
	@Autowired
	private FieldService fieldService;
	
	/* 	-----------------------------------	Field	---------------------------------------	*/
	   /*---Add new field---*/
	   @PostMapping("/field")
	   public ResponseEntity<?> save(@RequestBody Field f) {
	      long id = fieldService.save(f);
	      return ResponseEntity.ok().body("New Field has been saved with ID:" + id);
	   }
	   
	   /*---Add new field and add to formula---*/
	   @PostMapping("/formula/{p_id}-{r_id}/field")
	   public ResponseEntity<String> save_add_to_Formula(@PathVariable("p_id") int p_id, @PathVariable("r_id") int r_id, @RequestBody Field f) {
	      long id = fieldService.save_add_to_Formula(p_id, r_id, f);	      
	      return ResponseEntity.ok().body("New Field has been saved with ID:" + id+". Added to formula "+p_id);
	   }
	   
	   /*---Add field to formula---*/
	   @PostMapping("/formula/{p_id}-{r_id}/field/{f_id}")
	   public ResponseEntity<?> add_to_Formula(@PathVariable("p_id") int p_id, @PathVariable("r_id") int r_id, @PathVariable("r_id") int f_id) {
	      long id = fieldService.add_to_Formula(p_id, r_id, f_id);	      
	      return ResponseEntity.ok().body("Field with ID:" + id+" added to formula "+p_id);
	   }

	   /*---Get a field by id---*/
	   @GetMapping("/field/{id}")
	   public ResponseEntity<Field> getField(@PathVariable("id") int id) {
		  Field field = fieldService.get(id);
	      return ResponseEntity.ok().body(field);
	   }

	   /*---get all fields---*/
	   @GetMapping("/field")
	   public ResponseEntity<List<Field>> listFields() {
	      List<Field> fields = fieldService.list();
	      return ResponseEntity.ok().body(fields);
	   }
	   
	   /*---get all fields from formula id---*/
	   @GetMapping("/formula/{id}")
	   public ResponseEntity<List<Field>> listField_FormulaId(@PathVariable("id") int f_id) {	   
	      List<Field> fields = fieldService.list_formula_id(f_id);
	      return ResponseEntity.ok().body(fields);
	   }

	   /*---Update a field by id---*/
	   @PutMapping("/field/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Field f) {
		  fieldService.update(id, f);
	      return ResponseEntity.ok().body("Field has been updated successfully.");
	   }

	   /*---Delete a field by id---*/
	   @DeleteMapping("/field/{id}")
	   public ResponseEntity<?> deleteField(@PathVariable("id") int id) {
		   fieldService.delete(id);
		   return ResponseEntity.ok().body("Field has been deleted successfully.");
	   }
	   
	   
}
