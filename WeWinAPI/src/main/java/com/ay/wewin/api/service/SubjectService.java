package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.model.Subject;
import com.ay.wewin.api.repository.ISubjectRespository;
import com.ay.wewin.api.service.common.AbstractServices;

@Service
@Transactional
public class SubjectService extends AbstractServices<Subject>implements ISubjectService{

	@Autowired
	ISubjectRespository dao;
	
	@Override
	protected PagingAndSortingRepository<Subject, String> getDao() {
		return dao;
	}

}
