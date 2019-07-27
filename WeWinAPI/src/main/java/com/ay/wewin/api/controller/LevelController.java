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

import com.ay.wewin.api.model.Level;
import com.ay.wewin.api.service.ILevelService;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class LevelController {
	
	@Autowired
	ILevelService levelService;
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Levels
	@GetMapping("/GetAllLevels")
	public List<Level> getAllLevels(){
		List<Level> listLevels = levelService.findAll();
		return listLevels;
	}
	
	//Create a New Level
	@PostMapping("/CreateNewLevel")
	public Level createLevel(@RequestBody Level level){
		return levelService.create(level);
	}
	
	//Get a Level based on the id
	@GetMapping("/GetLevel/{id}")
	public Level getLevel(@PathVariable("id") String id){
		return levelService.findById(id);
	}
	
	//Update a Level
	@PutMapping("/UpdateLevel/{id}")
	public Level updateLevel(@PathVariable String id, @RequestBody Level level) {
		Level oldValue = levelService.findById(id);
		level.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return levelService.update(level);
	}
	
	//Delete a Level
	@DeleteMapping("/DeleteLevel/{id}")
	public ResponseEntity<?> deleteLevel(@PathVariable String id){
		Level oldValue = levelService.findById(id);
		levelService.delete(oldValue);
		return ResponseEntity.ok().body("Level # "+id+" Deleted");
	}
	
}
