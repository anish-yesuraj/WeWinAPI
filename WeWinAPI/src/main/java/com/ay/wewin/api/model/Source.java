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
@Table(name = "SOURCE_MASTER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class Source implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_seq")
	@GenericGenerator(
			name = "source_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SRC"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
			})
	@Column(unique=true, nullable =false, length=6)
	private String sourceId;
	private String sourceName;
	private String sourceDesc;
	private String sourceCopyright;
	private String sourceScope;
	private String sourceContactNo;
	private String sourceContactEmail;
	private String sourceContactAddress;
	private String sourceContactURL;
	private String sourceRemarks;
	private boolean active;
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	public Source() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Source(String sourceName, String sourceDesc, String sourceCopyright, String sourceScope,
			String sourceContactNo, String sourceContactEmail, String sourceContactAddress, String sourceContactURL,
			String sourceRemarks, boolean active, String createdId, String updatedId) {
		super();
		this.sourceName = sourceName;
		this.sourceDesc = sourceDesc;
		this.sourceCopyright = sourceCopyright;
		this.sourceScope = sourceScope;
		this.sourceContactNo = sourceContactNo;
		this.sourceContactEmail = sourceContactEmail;
		this.sourceContactAddress = sourceContactAddress;
		this.sourceContactURL = sourceContactURL;
		this.sourceRemarks = sourceRemarks;
		this.active = active;
		this.createdId = createdId;
		this.updatedId = updatedId;
	}

	
	/** Methods for DropDownMaster to access via reflection - START **/
	public String getDDMId()
	{
		return getSourceId();
	}
	public String getDDMName()
	{
		return getSourceName();
	}
	/** Methods for DropDownMaster to access via reflection - END **/

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceDesc() {
		return sourceDesc;
	}

	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	public String getSourceCopyright() {
		return sourceCopyright;
	}

	public void setSourceCopyright(String sourceCopyright) {
		this.sourceCopyright = sourceCopyright;
	}

	public String getSourceScope() {
		return sourceScope;
	}

	public void setSourceScope(String sourceScope) {
		this.sourceScope = sourceScope;
	}

	public String getSourceContactNo() {
		return sourceContactNo;
	}

	public void setSourceContactNo(String sourceContactNo) {
		this.sourceContactNo = sourceContactNo;
	}

	public String getSourceContactEmail() {
		return sourceContactEmail;
	}

	public void setSourceContactEmail(String sourceContactEmail) {
		this.sourceContactEmail = sourceContactEmail;
	}

	public String getSourceContactAddress() {
		return sourceContactAddress;
	}

	public void setSourceContactAddress(String sourceContactAddress) {
		this.sourceContactAddress = sourceContactAddress;
	}

	public String getSourceContactURL() {
		return sourceContactURL;
	}

	public void setSourceContactURL(String sourceContactURL) {
		this.sourceContactURL = sourceContactURL;
	}

	public String getSourceRemarks() {
		return sourceRemarks;
	}

	public void setSourceRemarks(String sourceRemarks) {
		this.sourceRemarks = sourceRemarks;
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
		return "Source [sourceId=" + sourceId + ", sourceName=" + sourceName + ", sourceDesc=" + sourceDesc
				+ ", sourceCopyright=" + sourceCopyright + ", sourceScope=" + sourceScope + ", sourceContactNo="
				+ sourceContactNo + ", sourceContactEmail=" + sourceContactEmail + ", sourceContactAddress="
				+ sourceContactAddress + ", sourceContactURL=" + sourceContactURL + ", sourceRemarks=" + sourceRemarks
				+ ", active=" + active + ", createdId=" + createdId + ", createdDate=" + createdDate + ", updatedId="
				+ updatedId + ", updatedDate=" + updatedDate + "]";
	}
	
	

}
