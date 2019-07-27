package com.ay.wewin.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "GRADE_MASTER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class Grade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
	@GenericGenerator(
			name = "grade_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "GRD"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
			})
	@Column(unique=true, nullable =false, length=6)
	private String gradeId;
	private String gradeName;
	private String gradeDesc;
	private String gradeRemarks;
	private boolean active;
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Grade(String gradeName, String gradeDesc, String gradeRemarks, boolean active, String createdId,
			String updatedId) {
		super();
		this.gradeName = gradeName;
		this.gradeDesc = gradeDesc;
		this.gradeRemarks = gradeRemarks;
		this.active = active;
		this.createdId = createdId;
		this.updatedId = updatedId;
	}

	
	/** Methods for DropDownMaster to access via reflection - START **/
	public String getDDMId()
	{
		return getGradeId();
	}
	public String getDDMName()
	{
		return getGradeName();
	}
	/** Methods for DropDownMaster to access via reflection - END **/


	public String getGradeId() {
		return gradeId;
	}


	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}


	public String getGradeName() {
		return gradeName;
	}


	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}


	public String getGradeDesc() {
		return gradeDesc;
	}


	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}


	public String getGradeRemarks() {
		return gradeRemarks;
	}


	public void setGradeRemarks(String gradeRemarks) {
		this.gradeRemarks = gradeRemarks;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getCreatedId() {
		return createdId;
	}


	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public String getUpdatedId() {
		return updatedId;
	}


	public void setUpdatedId(String updatedId) {
		this.updatedId = updatedId;
	}


	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", gradeName=" + gradeName + ", gradeDesc=" + gradeDesc + ", gradeRemarks="
				+ gradeRemarks + ", active=" + active + ", createdId=" + createdId + ", createdDate=" + createdDate
				+ ", updatedId=" + updatedId + ", updatedDate=" + updatedDate + "]";
	}
	
	
	

}
