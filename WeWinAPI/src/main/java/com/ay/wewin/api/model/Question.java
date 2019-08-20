package com.ay.wewin.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "QUESTION_MASTER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qn_seq")
	@GenericGenerator(
			name = "qn_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "Q"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
			})
	@Column(unique=true, nullable =false, length=7)
	private String id;
	private String grade;
	private String subject;
	private String topic;
	private String level;
	private String text;
	private String tip;
	private boolean active;
	private String imagePath;
	//private String imageName;
	//private String imageFile;
	@Transient
	private String imageSrc;
	private String imageTip;
	private String answerExplanation;
	private String sourceId;
	private String examTag;
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	

	/** OneToMany - Bidirectional (Mapped by 'question_id' in AnswerChoice) 
	 * - To Load 'AnswerChoices' while 'Question' is Loaded 
	 * - To Persist 'AnswerChoices' while 'Question' is Persisted **/
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER) 
	/** JSON - To ignore loading the 'Question' again in the 'AnswerChoice' Object **/
	@JsonIgnoreProperties(value= {"question"}) 
	private List<AnswerChoice> answerChoices = new ArrayList<AnswerChoice>();

	
	public Question() {
		super();
	}

	public Question(String grade, String subject, String topic, String level, String text, String tip, boolean active,
			String imagePath, String imageSrc, String imageTip, String answerExplanation, String sourceId,
			String examTag, String createdId, String updatedId) {
		super();
		this.grade = grade;
		this.subject = subject;
		this.topic = topic;
		this.level = level;
		this.text = text;
		this.tip = tip;
		this.active = active;
		this.imagePath = imagePath;
		this.imageSrc = imageSrc;
		this.imageTip = imageTip;
		this.answerExplanation = answerExplanation;
		this.sourceId = sourceId;
		this.examTag = examTag;
		this.createdId = createdId;
		this.updatedId = updatedId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageTip() {
		return imageTip;
	}

	public void setImageTip(String imageTip) {
		this.imageTip = imageTip;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<AnswerChoice> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(List<AnswerChoice> answerChoices) {
		
//		IntStream.range(0, answerChoices.size())
//				.forEach(i -> {
//					answerChoices.get(i).setQuestion(this);
//					answerChoices.get(i).setId(String.valueOf((char)(i + 'A')));
//					});
		
		for (int i=0; i<answerChoices.size(); i++)
		{
			answerChoices.get(i).setId(String.valueOf((char)(i + 'A')));
			answerChoices.get(i).setQuestion(this);
		}
		this.answerChoices = answerChoices;
	}

	public String getAnswerExplanation() {
		return answerExplanation;
	}

	public void setAnswerExplanation(String answerExplanation) {
		this.answerExplanation = answerExplanation;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getExamTag() {
		return examTag;
	}

	public void setExamTag(String examTag) {
		this.examTag = examTag;
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

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", grade=" + grade + ", subject=" + subject + ", topic=" + topic + ", level="
				+ level + ", text=" + text + ", tip=" + tip + ", active=" + active + ", imagePath=" + imagePath
				+ ", imageSrc=" + imageSrc + ", imageTip=" + imageTip + ", answerExplanation=" + answerExplanation
				+ ", sourceId=" + sourceId + ", examTag=" + examTag + ", createdId=" + createdId + ", createdDate="
				+ createdDate + ", updatedId=" + updatedId + ", updatedDate=" + updatedDate + ", answerChoices="
				+ answerChoices + "]";
	}


}
