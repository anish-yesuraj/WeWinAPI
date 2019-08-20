package com.ay.wewin.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ay.wewin.api.message.request.QuestionDTO;
import com.ay.wewin.api.model.AnswerChoice;
import com.ay.wewin.api.model.Question;
import com.ay.wewin.api.service.IQuestionService;
import com.ay.wewin.api.util.FileHandlerUtil;
import com.ay.wewin.api.util.Util;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;
	
	@Autowired
	private FileHandlerUtil fileHandlerUtil;
	
	@Autowired
	private ModelMapper modelMapper;
	
	Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	//TODO Preconditions.checkNotNull(requestObject) - check the request object
	//TODO RestPreconditions.checkFound(responseObject) - check the response object
	
	//Get all the Questions
	@GetMapping("/GetAllQuestions")
	public List<QuestionDTO> getAllQuestions(){
		log.trace("Get All Questions ");
		List<Question> listQuestions = questionService.findAll();
		return listQuestions.stream()
			.map(question -> {  
							QuestionDTO questionDTO = convertQuestionToDTO(question); 
							readFileAndSetQImage(questionDTO);
							return questionDTO;
						})
			.collect(Collectors.toList());
	}

	//Get a Question based on the id
	@GetMapping("/GetQuestion/{id}")
	public QuestionDTO getQuestion(@PathVariable("id") String id){
		QuestionDTO outQuestionDTO = new QuestionDTO(); 
		log.trace("Get Question ID Request :"+id);
		if (Util.isNotEmpty(id))
		{
			Question question = questionService.findById(id);
			outQuestionDTO = convertQuestionToDTO(question); 
			readFileAndSetQImage(outQuestionDTO);
		}
		log.trace("Get Question ID Response :"+outQuestionDTO);
		return outQuestionDTO; //TODO If Question id is not available give an apt response.
	}
	
	//Create a New Question
	@PostMapping("/CreateNewQuestion")
	@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_DE') or hasRole('ROLE_SME') or hasRole('ROLE_ADMIN')")
	public QuestionDTO createQuestion(@RequestBody QuestionDTO inpQuestionDTO){
		QuestionDTO outQuestionDTO = new QuestionDTO(); 
		if (inpQuestionDTO!=null)
		{
			log.trace("Create inpQuestionDTO -"+inpQuestionDTO.toString());
			Question question = convertQuestionToEntity(inpQuestionDTO);
			question = questionService.create(question);
			outQuestionDTO = convertQuestionToDTO(question);
			inpQuestionDTO.setId(outQuestionDTO.getId());
			writeFileByGetQImage(inpQuestionDTO); //TO NOTE - Image Source taken from the input object
			readFileAndSetQImage(outQuestionDTO);
		}
		log.trace("Create outQuestionDTO -"+outQuestionDTO.toString());
		return outQuestionDTO;
	}
	
	@PutMapping("/UpdateQuestion")//TODO Is id required in the path during update?
	@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_DE') or hasRole('ROLE_SME') or hasRole('ROLE_ADMIN')")
	public QuestionDTO updateQuestion(@RequestBody QuestionDTO inpQuestionDTO) {
		QuestionDTO outQuestionDTO = new QuestionDTO(); 
		if (inpQuestionDTO!=null && Util.isNotEmpty(inpQuestionDTO.getId()))
		{
			log.trace("Update inpQuestionDTO -"+inpQuestionDTO.toString());
			writeFileByGetQImage(inpQuestionDTO);
			Question oldQuestion = convertQuestionToEntity(inpQuestionDTO);
			oldQuestion = questionService.update(oldQuestion);
			outQuestionDTO = convertQuestionToDTO(oldQuestion);
			readFileAndSetQImage(outQuestionDTO);
		}
		log.trace("Update outQuestionDTO -"+outQuestionDTO.toString());
		return outQuestionDTO;
	}
	
	//Delete a Question
	@DeleteMapping("/DeleteQuestion/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteQuestion(@PathVariable String id){
		log.trace("Delete Question Request :"+id);
		String responseString = "NO ACTION";
		if (Util.isNotEmpty(id))
		{
			Question oldValue = questionService.findById(id);
			log.trace("Delete Question findById :"+oldValue);
			if (oldValue!=null && oldValue.getId().equals(id))
			{
				List<String> aIds = oldValue.getAnswerChoices().stream().map(answer -> answer.getId()).collect(Collectors.toList());
				questionService.delete(oldValue);
				deleteQuestionAnswersImageFile(id, aIds);
				responseString = "Question # "+id+" and AnswerChoices # "+ aIds +"are Deleted";
			}
		}
		log.trace("Delete Question Response :"+responseString);
		return ResponseEntity.ok().body(responseString);
	}
	
	//Activate or Deactivate a Question
	@PutMapping("/QuestionState/{id}/{state}")
	@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_SME') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deactivateQuestion(@PathVariable String id, @PathVariable String state){
		log.trace("Question Activate/Deactivate Request ID :"+id+"- State :"+state);
		String responseString = "NO ACTION";
		
		if (Util.isNotEmpty(id) && Util.isNotEmpty(state))
		{
			Question oldValue = questionService.findById(id);
			log.trace("Question Activate/Deactivate findById :"+oldValue);
			if (oldValue!=null && oldValue.getId().equals(id) && ("ACTIVATE".equals(state) || "DEACTIVATE".equals(state)))
			{
				if ("ACTIVATE".equals(state))
				{
					oldValue.setActive(true);
					responseString = "Question # "+ id +"is Activated";
				}
				else if ("DEACTIVATE".equals(state))
				{
					oldValue.setActive(false);
					responseString = "Question # "+ id +"is Deactivated";
				}
				questionService.update(oldValue);
			}
		}
		log.trace("Question Activate/Deactivate Response :"+responseString);
		return ResponseEntity.ok().body(responseString);
	}
	
	private QuestionDTO convertQuestionToDTO(Question question)
	{
		QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
		return questionDTO;
	}
	
	private Question convertQuestionToEntity(QuestionDTO questionDTO)
	{
		Question question = modelMapper.map(questionDTO, Question.class);
		return question;
	}
	
	private QuestionDTO readFileAndSetQImage(QuestionDTO question)
	{
		question.setImageSrc(fileHandlerUtil.readImageFromServer(question.getId()));
		question.getAnswerChoices().stream()
		.map(answerChoice -> {
			answerChoice.setImageSrc(fileHandlerUtil.readImageFromServer(buildAnswerImageName(question.getId(),answerChoice.getId())));
			return answerChoice;})
		.collect(Collectors.toList());
		return question;
	}
	
	private void writeFileByGetQImage(QuestionDTO question)
	{
		fileHandlerUtil.createOrBackupImageInServer(question.getImageSrc(), question.getId());
		question.getAnswerChoices().stream()
		.forEach(answerChoice -> {
					fileHandlerUtil.createOrBackupImageInServer(answerChoice.getImageSrc(), buildAnswerImageName(question.getId(),answerChoice.getId()));
					});
	}
	
	private void deleteQuestionAnswersImageFile(String qId, List<String> aIds) 
	{
		fileHandlerUtil.createOrBackupImageInServer("", qId);
		aIds.stream()
			.forEach((aId) -> {fileHandlerUtil.createOrBackupImageInServer("", buildAnswerImageName(qId,aId));});
	}
	
	private String buildAnswerImageName(String qId, String aId)
	{
		return qId.replace("Q", "A").concat(aId);
	}

	
}
