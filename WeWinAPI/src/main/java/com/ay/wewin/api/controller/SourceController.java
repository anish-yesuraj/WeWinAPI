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

import com.ay.wewin.api.model.Source;
import com.ay.wewin.api.service.ISourceService;


@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class SourceController {
	
	@Autowired
	ISourceService sourceService;
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Sources
	@GetMapping("/GetAllSources")
	public List<Source> getAllSources(){
		List<Source> listSources = sourceService.findAll();
		return listSources;
	}
	
	//Create a New Source
	@PostMapping("/CreateNewSource")
	public Source createSource(@RequestBody Source source){
		return sourceService.create(source);
	}
	
	//Get a Source based on the id
	@GetMapping("/GetSource/{id}")
	public Source getSource(@PathVariable("id") String id){
		return sourceService.findById(id);
	}
	
	//Update a Source
	@PutMapping("/UpdateSource/{id}")
	public Source updateSource(@PathVariable String id, @RequestBody Source source) {
		Source oldValue = sourceService.findById(id);
		source.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return sourceService.update(source);
	}
	
	//Delete a Source
	@DeleteMapping("/DeleteSource/{id}")
	public ResponseEntity<?> deleteSource(@PathVariable String id){
		Source oldValue = sourceService.findById(id);
		sourceService.delete(oldValue);
		return ResponseEntity.ok().body("Source # "+id+" Deleted");
	}
	
}
