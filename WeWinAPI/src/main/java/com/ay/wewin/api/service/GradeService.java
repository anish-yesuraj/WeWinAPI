package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.model.Grade;
import com.ay.wewin.api.repository.IGradeRepository;
import com.ay.wewin.api.service.common.AbstractServices;

@Service
@Transactional
public class GradeService extends AbstractServices<Grade>implements IGradeService{

	@Autowired
	IGradeRepository dao;
	
	@Override
	protected PagingAndSortingRepository<Grade, String> getDao() {
		return dao;
	}

}
