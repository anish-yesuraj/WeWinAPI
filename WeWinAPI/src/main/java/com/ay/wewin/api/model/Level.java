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
@Table(name = "LEVEL_MASTER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class Level implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "level_seq")
	@GenericGenerator(
			name = "level_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LVL"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
			})
	@Column(unique=true, nullable =false, length=6)
	private String levelId;
	private String levelName;
	private String levelDesc;
	private String levelRemarks;
	private boolean active;
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	public Level() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Level(String levelName, String levelDesc, String levelRemarks, boolean active, String createdId,
			String updatedId) {
		super();
		this.levelName = levelName;
		this.levelDesc = levelDesc;
		this.levelRemarks = levelRemarks;
		this.active = active;
		this.createdId = createdId;
		this.updatedId = updatedId;
	}

	
	/** Methods for DropDownMaster to access via reflection - START **/
	public String getDDMId()
	{
		return getLevelId();
	}
	public String getDDMName()
	{
		return getLevelName();
	}
	/** Methods for DropDownMaster to access via reflection - END **/
	
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public String getLevelRemarks() {
		return levelRemarks;
	}

	public void setLevelRemarks(String levelRemarks) {
		this.levelRemarks = levelRemarks;
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
		return "Level [levelId=" + levelId + ", levelName=" + levelName + ", levelDesc=" + levelDesc + ", levelRemarks="
				+ levelRemarks + ", active=" + active + ", createdId=" + createdId + ", createdDate=" + createdDate
				+ ", updatedId=" + updatedId + ", updatedDate=" + updatedDate + "]";
	}
	
	
	
	

}
