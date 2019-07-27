package com.ay.wewin.api.repository;

import java.util.List;

import com.ay.wewin.api.model.Exam;
import com.ay.wewin.api.model.Grade;
import com.ay.wewin.api.model.Level;
import com.ay.wewin.api.model.Source;
import com.ay.wewin.api.model.Subject;
import com.ay.wewin.api.model.Topic;

public interface IDropDownRepository {
	
	List<Subject> getSubjectsForDropDownMaster();
	
	List<Grade> getGradesForDropDownMaster();

	List<Level> getLevelsForDropDownMaster();
	
	List<Topic> getTopicsForDropDownMaster();
	
	List<Exam> getExamsForDropDownMaster();
	
	List<Source> getSorucesForDropDownMaster();

	Object[] getAllMasterRecords(String masterType);

}
