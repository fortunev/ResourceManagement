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

import com.spring.model.Project;
import com.spring.model.Resource;
import com.spring.model.User;
import com.spring.service.ProjectService;
import com.spring.service.ResourceService;
import com.spring.service.UserService;

@RestController
public class UserController {
	   @Autowired
	   private UserService userService;
	   
	   /*	---------------------------------------	USER		----------------------------------	*/
	   /*---Add new user---*/
	   @PostMapping("/user")
	   public ResponseEntity<?> save(@RequestBody User user) {
	      long id = userService.save(user);
	      return ResponseEntity.ok().body("New User has been saved with ID:" + id);
	   }

	   /*---Get a user by id---*/
	   @GetMapping("/user/{id}")
	   public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		  User user = userService.get(id);
	      return ResponseEntity.ok().body(user);
	   }

	   /*---get all users---*/
	   @GetMapping("/user")
	   public ResponseEntity<List<User>> listUsers() {
	      List<User> users = userService.list();
	      return ResponseEntity.ok().body(users);
	   }

	   /*---Update a user by id---*/
	   @PutMapping("/user/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody User user) {
		  userService.update(id, user);
	      return ResponseEntity.ok().body("User has been updated successfully.");
	   }

	   /*---Delete a user by id---*/
	   @DeleteMapping("/user/{id}")
	   public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		   userService.delete(id);
		   return ResponseEntity.ok().body("User has been deleted successfully.");
	   }	   
}
