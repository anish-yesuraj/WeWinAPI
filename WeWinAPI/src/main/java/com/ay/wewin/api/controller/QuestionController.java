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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ay.wewin.api.model.Question;
import com.ay.wewin.api.service.IQuestionService;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;

	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Questions
	@GetMapping("/GetAllQuestions")
	public List<Question> getAllQuestions(){
		List<Question> listQuestions = questionService.findAll();
		return listQuestions;
	}
	
	//Create a New Question
	@PostMapping("/CreateNewQuestion")
	public Question createQuestion(@RequestBody Question question){
		return questionService.create(question);
	}
	
	//Get a Question based on the id
	@GetMapping("/GetQuestion/{id}")
	public Question getQuestion(@PathVariable("id") String id){
		return questionService.findById(id);
	}
	
	//Update a Question
	@PutMapping("/UpdateQuestion/{id}")
	public Question updateQuestion(@PathVariable String id, @RequestBody Question question) {
		Question oldValue = questionService.findById(id);
		question.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return questionService.update(question);
	}
	
	//Delete a Question
	@DeleteMapping("/DeleteQuestion/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable String id){
		Question oldValue = questionService.findById(id);
		questionService.delete(oldValue);
		return ResponseEntity.ok().body("Question # "+id+" Deleted");
	}
	
}
