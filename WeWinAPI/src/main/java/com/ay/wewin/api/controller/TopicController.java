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

import com.ay.wewin.api.model.Topic;
import com.ay.wewin.api.service.ITopicService;


@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class TopicController {
	
	@Autowired
	ITopicService topicService;
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Topics
	@GetMapping("/GetAllTopics")
	public List<Topic> getAllTopics(){
		List<Topic> listTopics = topicService.findAll();
		return listTopics;
	}
	
	//Create a New Topic
	@PostMapping("/CreateNewTopic")
	public Topic createTopic(@RequestBody Topic topic){
		return topicService.create(topic);
	}
	
	//Get a Topic based on the id
	@GetMapping("/GetTopic/{id}")
	public Topic getTopic(@PathVariable("id") String id){
		return topicService.findById(id);
	}
	
	//Update a Topic
	@PutMapping("/UpdateTopic/{id}")
	public Topic updateTopic(@PathVariable String id, @RequestBody Topic topic) {
		Topic oldValue = topicService.findById(id);
		topic.setCreatedDate(oldValue.getCreatedDate());//TODO This may not be the correct way of retaining the created timestamp.
		//TODO How to put the new values in the old object?
		return topicService.update(topic);
	}
	
	//Delete a Topic
	@DeleteMapping("/DeleteTopic/{id}")
	public ResponseEntity<?> deleteTopic(@PathVariable String id){
		Topic oldValue = topicService.findById(id);
		topicService.delete(oldValue);
		return ResponseEntity.ok().body("Topic # "+id+" Deleted");
	}
	
}
