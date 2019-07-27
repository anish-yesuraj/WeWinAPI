package com.ay.wewin.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ay.wewin.api.model.DropDownMaster;
import com.ay.wewin.api.model.Exam;
import com.ay.wewin.api.model.Grade;
import com.ay.wewin.api.model.Level;
import com.ay.wewin.api.model.Source;
import com.ay.wewin.api.model.Subject;
import com.ay.wewin.api.model.Topic;
import com.ay.wewin.api.service.IDropDownService;
import com.ay.wewin.api.util.CommonProps;

@CrossOrigin("*") // To enable cross origin in Spring MVC to receive Http requests from Angular
@RestController
public class DropDownController {
	
	@Autowired
	private IDropDownService dropDownService;
	
	//Get All Master Records from a MasterType for DropDown values
	@GetMapping("/GetDropDownMaster/{masterType}")
	public ResponseEntity<Object[]> getAllMasters(@PathVariable String masterType)
	{
		//TODO Validate input @PathVariable
		
		Object[] objectMasters = dropDownService.getDropDownMaster(masterType);
		DropDownMaster[] ddmArray = processDDMFromDb(objectMasters, masterType);
		
		return handleResponse(ddmArray, masterType);
	}
	
	private DropDownMaster[] processDDMFromDb(Object[] inpObjArray, String masterType)
	{
		DropDownMaster[] ddmArray = null;
		
		if (inpObjArray!=null && inpObjArray.length>0)
		{
			List<DropDownMaster> tempDDMList = new ArrayList<DropDownMaster>();
			Arrays.stream(inpObjArray)
					.filter(o -> o instanceof Object)
					.forEach( dbObj -> {
									if (CommonProps.MASTER_TYPE_SUBJECT.equals(masterType) && "com.ay.wewin.api.model.Subject".equals(dbObj.getClass().getName()))
									{
										tempDDMList.add(new DropDownMaster(masterType, ((Subject)dbObj).getDDMId(), ((Subject)dbObj).getDDMName()));
									}
									else if (CommonProps.MASTER_TYPE_GRADE.equals(masterType) && "com.ay.wewin.api.model.Grade".equals(dbObj.getClass().getName()))
									{
										tempDDMList.add(new DropDownMaster(masterType, ((Grade)dbObj).getDDMId(), ((Grade)dbObj).getDDMName()));
									}
									else if (CommonProps.MASTER_TYPE_LEVEL.equals(masterType) && "com.ay.wewin.api.model.Level".equals(dbObj.getClass().getName()))
									{
										tempDDMList.add(new DropDownMaster(masterType, ((Level)dbObj).getDDMId(), ((Level)dbObj).getDDMName()));
									}
									else if (CommonProps.MASTER_TYPE_TOPIC.equals(masterType) && "com.ay.wewin.api.model.Topic".equals(dbObj.getClass().getName()))
									{
										tempDDMList.add(new DropDownMaster(masterType, ((Topic)dbObj).getDDMId(), ((Topic)dbObj).getDDMName()));
									}
									else if (CommonProps.MASTER_TYPE_SOURCE.equals(masterType) && "com.ay.wewin.api.model.Source".equals(dbObj.getClass().getName()))
									{
										tempDDMList.add(new DropDownMaster(masterType, ((Source)dbObj).getDDMId(), ((Source)dbObj).getDDMName()));
									}
									else if (CommonProps.MASTER_TYPE_EXAM.equals(masterType) && "com.ay.wewin.api.model.Exam".equals(dbObj.getClass().getName()))
									{
										tempDDMList.add(new DropDownMaster(masterType, ((Exam)dbObj).getDDMId(), ((Exam)dbObj).getDDMName()));
									}
					});
			ddmArray = new DropDownMaster[(tempDDMList!=null && tempDDMList.size()>0)?tempDDMList.size():0];
			tempDDMList.toArray(ddmArray);
		}
		else if (inpObjArray!=null && inpObjArray.length==0)
		{
			ddmArray = new DropDownMaster[0];
		}
		
		return ddmArray;
	}
	
	
	private ResponseEntity<Object[]> handleResponse (Object[] objectArray, String masterType)
	{
		if (objectArray!=null && objectArray.length>0)
		{
			return ResponseEntity.ok().body(objectArray);
		}
		else if (objectArray!=null && objectArray.length==0)
		{
			return ResponseEntity.ok().body(new String []{"Empty DropDown master: "+masterType+""});
		}
		
		return ResponseEntity.badRequest().body(new String []{"Error getting DropDown master: "+masterType+""});
	}
	
}
