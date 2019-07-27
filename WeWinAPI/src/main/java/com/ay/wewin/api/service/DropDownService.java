package com.ay.wewin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ay.wewin.api.repository.IDropDownRepository;

@Service
public class DropDownService implements IDropDownService{
	
	@Autowired
	private IDropDownRepository dropDownDAO; 
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Object[] getDropDownMaster(String masterType) {
		
		return dropDownDAO.getAllMasterRecords(masterType);
	}

}
