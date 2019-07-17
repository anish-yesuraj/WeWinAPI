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

import com.ay.wewin.api.model.Subject;
import com.ay.wewin.api.service.ISubjectService;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class SubjectController {
	
	@Autowired
	ISubjectService subjectService;
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Subjects
	@GetMapping("/GetAllSubjects")
	public List<Subject> getAllSubjects(){
		List<Subject> listSubjects = subjectService.findAll();
		return listSubjects;
	}
	
	//Create a New Subject
	@PostMapping("/CreateNewSubject")
	public Subject createSubject(@RequestBody Subject subject){
		return subjectService.create(subject);
	}
	
	//Get a Subject based on the id
	@GetMapping("/GetSubject/{id}")
	public Subject getSubject(@PathVariable("id") String id){
		return subjectService.findById(id);
	}
	
	//Update a Subject
	@PutMapping("/UpdateSubject/{id}")
	public Subject updateSubject(@PathVariable String id, @RequestBody Subject subject) {
		Subject oldValue = subjectService.findById(id);
		subject.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return subjectService.update(subject);
	}
	
	//Delete a Subject
	@DeleteMapping("/DeleteSubject/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable String id){
		Subject oldValue = subjectService.findById(id);
		subjectService.delete(oldValue);
		return ResponseEntity.ok().body("Subject # "+id+" Deleted");
	}
	
}
