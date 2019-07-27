package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.model.Topic;
import com.ay.wewin.api.repository.ITopicRepository;
import com.ay.wewin.api.service.common.AbstractServices;

@Service
@Transactional
public class TopicService extends AbstractServices<Topic>implements ITopicService{

	@Autowired
	ITopicRepository dao;
	
	@Override
	protected PagingAndSortingRepository<Topic, String> getDao() {
		return dao;
	}

}
