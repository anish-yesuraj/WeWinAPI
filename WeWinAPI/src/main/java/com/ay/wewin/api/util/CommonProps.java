package com.ay.wewin.api.util;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:WeWinAPI.properties")
@ConfigurationProperties(prefix="wewin.property")
public class CommonProps {
	

	public static final String MASTER_TYPE_SUBJECT = "SUBJECT";
	public static final String MASTER_TYPE_GRADE   = "GRADE";
	public static final String MASTER_TYPE_LEVEL   = "LEVEL";
	public static final String MASTER_TYPE_TOPIC   = "TOPIC";
	public static final String MASTER_TYPE_SOURCE  = "SOURCE";
	public static final String MASTER_TYPE_EXAM    = "EXAM";
	
	@NotBlank
	private String imageExtension;
	@NotBlank
	private String questionImagePath;
	@NotBlank
	private String questionImageBackupPath;
	@NotBlank
	private String answerImagePath;
	@NotBlank
	private String answerImageBackupPath;
	
	
	
	public String getImageExtension() {
		return imageExtension;
	}
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}
	public String getQuestionImagePath() {
		return questionImagePath;
	}
	public void setQuestionImagePath(String questionImagePath) {
		this.questionImagePath = questionImagePath;
	}
	public String getAnswerImagePath() {
		return answerImagePath;
	}
	public void setAnswerImagePath(String answerImagePath) {
		this.answerImagePath = answerImagePath;
	}
	public String getQuestionImageBackupPath() {
		return questionImageBackupPath;
	}
	public void setQuestionImageBackupPath(String questionImageBackupPath) {
		this.questionImageBackupPath = questionImageBackupPath;
	}
	public String getAnswerImageBackupPath() {
		return answerImageBackupPath;
	}
	public void setAnswerImageBackupPath(String answerImageBackupPath) {
		this.answerImageBackupPath = answerImageBackupPath;
	}
	
	
	

}
