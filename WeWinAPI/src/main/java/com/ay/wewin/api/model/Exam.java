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
@Table(name = "EXAM_MASTER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class Exam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_seq")
	@GenericGenerator(
			name = "exam_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EXM"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
			})
	@Column(unique=true, nullable =false, length=6)
	private String examId;
	private String examName;
	private String examDesc;
	private String examLevel;
	private String examAuthority;
	private String examAuthorityURL;
	private String examRemarks;
	private String examType;
	private String examLanguages;
	private boolean active;
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	public Exam() {
		super();
	}
	
	public Exam(String examName, String examDesc, String examLevel, String examAuthority, String examAuthorityURL,
			String examRemarks, String examType, String examLanguages, boolean active, String createdId,
			String updatedId) {
		super();
		this.examName = examName;
		this.examDesc = examDesc;
		this.examLevel = examLevel;
		this.examAuthority = examAuthority;
		this.examAuthorityURL = examAuthorityURL;
		this.examRemarks = examRemarks;
		this.examType = examType;
		this.examLanguages = examLanguages;
		this.active = active;
		this.createdId = createdId;
		this.updatedId = updatedId;
	}
	
	/** Methods for DropDownMaster to access via reflection - START **/
	public String getDDMId()
	{
		return getExamId();
	}
	public String getDDMName()
	{
		return getExamName();
	}
	/** Methods for DropDownMaster to access via reflection - END **/


	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamDesc() {
		return examDesc;
	}

	public void setExamDesc(String examDesc) {
		this.examDesc = examDesc;
	}

	public String getExamLevel() {
		return examLevel;
	}

	public void setExamLevel(String examLevel) {
		this.examLevel = examLevel;
	}

	public String getExamAuthority() {
		return examAuthority;
	}

	public void setExamAuthority(String examAuthority) {
		this.examAuthority = examAuthority;
	}

	public String getExamAuthorityURL() {
		return examAuthorityURL;
	}

	public void setExamAuthorityURL(String examAuthorityURL) {
		this.examAuthorityURL = examAuthorityURL;
	}

	public String getExamRemarks() {
		return examRemarks;
	}

	public void setExamRemarks(String examRemarks) {
		this.examRemarks = examRemarks;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExamLanguages() {
		return examLanguages;
	}

	public void setExamLanguages(String examLanguages) {
		this.examLanguages = examLanguages;
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
		return "Exam [examId=" + examId + ", examName=" + examName + ", examDesc=" + examDesc + ", examLevel="
				+ examLevel + ", examAuthority=" + examAuthority + ", examAuthorityURL=" + examAuthorityURL
				+ ", examRemarks=" + examRemarks + ", examType=" + examType + ", examLanguages=" + examLanguages
				+ ", active=" + active + ", createdId=" + createdId + ", createdDate=" + createdDate + ", updatedId="
				+ updatedId + ", updatedDate=" + updatedDate + "]";
	}
	
	
	

	
}
