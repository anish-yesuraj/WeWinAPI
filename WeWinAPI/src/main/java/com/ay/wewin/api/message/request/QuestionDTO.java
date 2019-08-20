package com.ay.wewin.api.message.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class QuestionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String grade;
	private String subject;
	private String topic;
	private String level;
	private String sourceId;
	private String examTag;
	private String text;
	private String tip;
	private boolean active;
	private String imagePath;
	private String imageSrc;
	private String imageTip;
	private String answerExplanation;
	private String createdId;
	private LocalDateTime createdDate;
	private String updatedId;
	private LocalDateTime updatedDate;
	private List<AnswerChoiceDTO> answerChoices = new ArrayList<AnswerChoiceDTO>();
	
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public String getImageTip() {
		return imageTip;
	}
	public void setImageTip(String imageTip) {
		this.imageTip = imageTip;
	}
	public String getAnswerExplanation() {
		return answerExplanation;
	}
	public void setAnswerExplanation(String answerExplanation) {
		this.answerExplanation = answerExplanation;
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
	public List<AnswerChoiceDTO> getAnswerChoices() {
		return answerChoices;
	}
	public void setAnswerChoices(List<AnswerChoiceDTO> answerChoices) {
//		IntStream.range(0, answerChoices.size())
//		.forEach(i -> {
//			answerChoices.get(i).setId(String.valueOf((char)(i + 'A')));
//			});
		for (int i=0; i<answerChoices.size(); i++)
		{
			answerChoices.get(i).setId(String.valueOf((char)(i + 'A')));
		}
		this.answerChoices = answerChoices;
	}
	@Override
	public String toString() {
		return "QuestionForm [id=" + id + ", grade=" + grade + ", subject=" + subject + ", topic=" + topic + ", level="
				+ level + ", sourceId=" + sourceId + ", examTag=" + examTag + ", text=" + text + ", tip=" + tip
				+ ", active=" + active + ", imagePath=" + imagePath + ", imageSrc=" + imageSrc + ", imageTip="
				+ imageTip + ", answerExplanation=" + answerExplanation + ", createdId=" + createdId + ", createdDate="
				+ createdDate + ", updatedId=" + updatedId + ", updatedDate=" + updatedDate + ", answerChoices="
				+ answerChoices + "]";
	}
	
	

}
