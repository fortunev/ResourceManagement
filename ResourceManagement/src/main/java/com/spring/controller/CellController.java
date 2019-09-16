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

import com.spring.model.Cell;
import com.spring.service.CellService;
import com.spring.service.CellService;

@RestController
public class CellController {
	@Autowired
	private CellService cellService;
	
	/* 	-----------------------------------	Cell	---------------------------------------	*/
	   /*---Add new cell---*/
	   @PostMapping("resource/{r_id}/column/{c_id}/cell")
	   public ResponseEntity<?> save(@PathVariable("r_id") int r_id, @PathVariable("c_id") int c_id, @RequestBody Cell c) {
	      long id = cellService.save(c, c_id, r_id);
	      return ResponseEntity.ok().body("New Cell has been saved with ID:" + id);
	   }	 
	   
	   /*---Add cell to Resource---*/
	   @PostMapping("resource/{r_id}/cell/{c_id}")
	   public ResponseEntity<?> add_to_Resource(@PathVariable("r_id") int r_id, @PathVariable("c_id") int c_id) {
	      long id = cellService.add_to_Resource(c_id, r_id);
	      return ResponseEntity.ok().body("Cell has been added to resource "+r_id+" with ID:" + id);
	   }	
	  
	   /*---Add cell to Columns---*/
	   @PostMapping("column/{col_id}/cell/{c_id}")
	   public ResponseEntity<?> add_to_Columns(@PathVariable("col_id") int col_id, @PathVariable("c_id") int c_id) {
	      long id = cellService.add_to_Columns(c_id, col_id);
	      return ResponseEntity.ok().body("Cell has been added to column "+col_id+" with ID:" + id);
	   }	
	   
	   /*---Add cell to Resources and Columns---*/
	   @PostMapping("resource/{r_id}/column/{col_id}/cell/{c_id}")
	   public ResponseEntity<?> add_to_Res_Col(@PathVariable("r_id") int r_id, @PathVariable("col_id") int col_id, @PathVariable("c_id") int c_id) {
	      long id = cellService.add_to_Res_Col(c_id, r_id, col_id);
	      return ResponseEntity.ok().body("Cell has been added to resource "+r_id+ "column "+col_id+" with ID:" + id);
	   }	
	   
	   /*---Get a cell by id---*/
	   @GetMapping("/cell/{id}")
	   public ResponseEntity<Cell> getCell(@PathVariable("id") int id) {
		  Cell cell = cellService.get(id);
	      return ResponseEntity.ok().body(cell);
	   }

	   /*---get all cells---*/
	   @GetMapping("/cell")
	   public ResponseEntity<List<Cell>> listCells() {
	      List<Cell> cells = cellService.list();
	      return ResponseEntity.ok().body(cells);
	   }
	   
	   /*---get all cells from resource id---*/
	   @GetMapping("/resource/{r_id}/cells")
	   public ResponseEntity<List<Cell>> list_resource_id(@PathVariable("r_id") int r_id) {
	      List<Cell> cells = cellService.list_resource_id(r_id);
	      return ResponseEntity.ok().body(cells);
	   }
	   
	   /*---get all cells from column id---*/
	   @GetMapping("/column/{c_id}/cells")
	   public ResponseEntity<List<Cell>> list_column_id(@PathVariable("c_id") int c_id) {
	      List<Cell> cells = cellService.list_column_id(c_id);
	      return ResponseEntity.ok().body(cells);
	   }
	  
	   /*---Update a cell by id---*/
	   @PutMapping("/cell/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Cell c) {
		  cellService.update(id, c);
	      return ResponseEntity.ok().body("Cell has been updated successfully.");
	   }

	   /*---Delete a cell by id---*/
	   @DeleteMapping("cell/{id}")
	   public ResponseEntity<?> deleteCell(@PathVariable("id") int id) {
		   cellService.delete( id);
		   return ResponseEntity.ok().body("Cell has been deleted successfully.");
	   }
}
