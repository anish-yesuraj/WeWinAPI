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

import com.ay.wewin.api.model.Exam;
import com.ay.wewin.api.service.IExamService;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class ExamController {
	
	@Autowired
	IExamService examService;
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Exams
	@GetMapping("/GetAllExams")
	public List<Exam> getAllExams(){
		List<Exam> listExams = examService.findAll();
		return listExams;
	}
	
	//Create a New Exam
	@PostMapping("/CreateNewExam")
	public Exam createExam(@RequestBody Exam exam){
		return examService.create(exam);
	}
	
	//Get a Exam based on the id
	@GetMapping("/GetExam/{id}")
	public Exam getExam(@PathVariable("id") String id){
		return examService.findById(id);
	}
	
	//Update a Exam
	@PutMapping("/UpdateExam/{id}")
	public Exam updateExam(@PathVariable String id, @RequestBody Exam exam) {
		Exam oldValue = examService.findById(id);
		exam.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return examService.update(exam);
	}
	
	//Delete a Exam
	@DeleteMapping("/DeleteExam/{id}")
	public ResponseEntity<?> deleteExam(@PathVariable String id){
		Exam oldValue = examService.findById(id);
		examService.delete(oldValue);
		return ResponseEntity.ok().body("Exam # "+id+" Deleted");
	}
	
}
