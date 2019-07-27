package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.model.Exam;
import com.ay.wewin.api.repository.IExamRepository;
import com.ay.wewin.api.service.common.AbstractServices;

@Service
@Transactional
public class ExamService extends AbstractServices<Exam>implements IExamService{

	@Autowired
	IExamRepository dao;
	
	@Override
	protected PagingAndSortingRepository<Exam, String> getDao() {
		return dao;
	}

}
