package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.model.Source;
import com.ay.wewin.api.repository.ISourceRepository;
import com.ay.wewin.api.service.common.AbstractServices;

@Service
@Transactional
public class SourceService extends AbstractServices<Source>implements ISourceService{

	@Autowired
	ISourceRepository dao;
	
	@Override
	protected PagingAndSortingRepository<Source, String> getDao() {
		return dao;
	}

}
