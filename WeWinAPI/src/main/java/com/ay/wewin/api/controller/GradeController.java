package com.ay.wewin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ay.wewin.api.model.Grade;
import com.ay.wewin.api.service.IGradeService;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class GradeController {
	
	@Autowired
	IGradeService gradeService;
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Grades
	@GetMapping("/GetAllGrades")
	public List<Grade> getAllGrades(){
		List<Grade> listGrades = gradeService.findAll();
		return listGrades;
	}
	
	//Create a New Grade
	@PostMapping("/CreateNewGrade")
	public Grade createGrade(@RequestBody Grade grade){
		return gradeService.create(grade);
	}
	
	//Get a Grade based on the id
	@GetMapping("/GetGrade/{id}")
	public Grade getGrade(@PathVariable("id") String id){
		return gradeService.findById(id);
	}
	
	//Update a Grade
	@PutMapping("/UpdateGrade/{id}")
	public Grade updateGrade(@PathVariable String id, @RequestBody Grade grade) {
		Grade oldValue = gradeService.findById(id);
		grade.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return gradeService.update(grade);
	}
	
	//Delete a Grade
	@DeleteMapping("/DeleteGrade/{id}")
	public ResponseEntity<?> deleteGrade(@PathVariable String id){
		Grade oldValue = gradeService.findById(id);
		gradeService.delete(oldValue);
		return ResponseEntity.ok().body("Grade # "+id+" Deleted");
	}
	
}
