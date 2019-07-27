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
@Table(name = "TOPIC_MASTER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class Topic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
	@GenericGenerator(
			name = "topic_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TPC"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
			})
	@Column(unique=true, nullable =false, length=6)
	private String topicId;
	private String topicName;
	private String topicDesc;
	private String topicType;
	private String topicSubject;
	private String topicGrade;
	private String topicRemarks;
	private boolean active;
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topic(String topicName, String topicDesc, String topicType, String topicSubject, String topicGrade,
			String topicRemarks, boolean active, String createdId, String updatedId) {
		super();
		this.topicName = topicName;
		this.topicDesc = topicDesc;
		this.topicType = topicType;
		this.topicSubject = topicSubject;
		this.topicGrade = topicGrade;
		this.topicRemarks = topicRemarks;
		this.active = active;
		this.createdId = createdId;
		this.updatedId = updatedId;
	}

	/** Methods for DropDownMaster to access via reflection - START **/
	public String getDDMId()
	{
		return getTopicId();
	}
	public String getDDMName()
	{
		return getTopicName();
	}
	/** Methods for DropDownMaster to access via reflection - END **/

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public String getTopicSubject() {
		return topicSubject;
	}

	public void setTopicSubject(String topicSubject) {
		this.topicSubject = topicSubject;
	}

	public String getTopicGrade() {
		return topicGrade;
	}

	public void setTopicGrade(String topicGrade) {
		this.topicGrade = topicGrade;
	}

	public String getTopicRemarks() {
		return topicRemarks;
	}

	public void setTopicRemarks(String topicRemarks) {
		this.topicRemarks = topicRemarks;
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
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", topicDesc=" + topicDesc + ", topicType="
				+ topicType + ", topicSubject=" + topicSubject + ", topicGrade=" + topicGrade + ", topicRemarks="
				+ topicRemarks + ", active=" + active + ", createdId=" + createdId + ", createdDate=" + createdDate
				+ ", updatedId=" + updatedId + ", updatedDate=" + updatedDate + "]";
	}
	
	

}
