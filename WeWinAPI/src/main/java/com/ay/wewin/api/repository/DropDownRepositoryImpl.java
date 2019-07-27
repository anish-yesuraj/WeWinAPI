package com.ay.wewin.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ay.wewin.api.model.Exam;
import com.ay.wewin.api.model.Grade;
import com.ay.wewin.api.model.Level;
import com.ay.wewin.api.model.Source;
import com.ay.wewin.api.model.Subject;
import com.ay.wewin.api.model.Topic;
import com.ay.wewin.api.util.CommonProps;

@Repository
public class DropDownRepositoryImpl implements IDropDownRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	private SessionFactory getSessionFactory() {
		return entityManager.unwrap(Session.class).getSessionFactory();
	}
	
	@Override
	public List<Subject> getSubjectsForDropDownMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grade> getGradesForDropDownMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> getLevelsForDropDownMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Topic> getTopicsForDropDownMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getExamsForDropDownMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Source> getSorucesForDropDownMaster() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object[] getAllMasterRecords(String masterType) {
		
		String strHQL = "";
				
		switch (masterType)
		{
			case CommonProps.MASTER_TYPE_SUBJECT:
			{
				strHQL = "from Subject where active=true";
				break;
			}
			case CommonProps.MASTER_TYPE_GRADE:
			{
				strHQL = "from Grade where active=true";
				break;
			}
			case CommonProps.MASTER_TYPE_LEVEL:
			{
				strHQL = "from Level where active=true";
				break;
			}
			case CommonProps.MASTER_TYPE_TOPIC:
			{
				strHQL = "from Topic where active=true";
				break;
			}
			case CommonProps.MASTER_TYPE_SOURCE:
			{
				strHQL = "from Source where active=true";
				break;
			}
			case CommonProps.MASTER_TYPE_EXAM:
			{
				strHQL = "from Exam where active=true";
				break;
			}
		}
		
		return (strHQL!=null && strHQL.trim().length()>0)?getSessionFactory().getCurrentSession().createQuery(strHQL).getResultList().toArray():null;
	}

}
