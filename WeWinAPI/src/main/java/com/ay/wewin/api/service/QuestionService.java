package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.model.Question;
import com.ay.wewin.api.repository.IQuestionRepository;
import com.ay.wewin.api.service.common.AbstractServices;

@Service
@Transactional
public class QuestionService extends AbstractServices<Question> implements IQuestionService{

	@Autowired
	private IQuestionRepository dao;
	
	public QuestionService() {
		super();
	}
	
	@Override
	protected PagingAndSortingRepository<Question, String> getDao() {
		return dao;
	}

}
