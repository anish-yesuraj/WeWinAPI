package com.ay.wewin.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ANSWER_CHOICE")
public class AnswerChoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ac_seq")
	@GenericGenerator(
			name = "ac_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "A"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
			})
	@Column(unique=true, nullable =false, length=7)
	private String id;
	private String text;
	private String tip;
	private boolean result;
	private boolean active;
	private String imagePath;
	private String imageTip;
	
	/** ManyToOne - Bidirectional (Mapped by 'question_id' in AnswerChoice) 
	 *  JoinColumn - question_id will have 'id' from Question **/
	@ManyToOne//(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	/** JSON - To ignore loading the 'AnswerChoices' again in the 'Question' Object **/
	@JsonIgnoreProperties(value= {"answerChoices"}) 
	private Question question;
	

	public AnswerChoice() {
		super();
	}

	public AnswerChoice(String text, String tip, boolean result, boolean active, String imagePath, String imageTip) {
		super();
		this.text = text;
		this.tip = tip;
		this.result = result;
		this.active = active;
		this.imagePath = imagePath;
		this.imageTip = imageTip;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
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

	public String getImageTip() {
		return imageTip;
	}

	public void setImageTip(String imageTip) {
		this.imageTip = imageTip;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "AnswerChoice [id=" + id + ", text=" + text + ", tip=" + tip + ", result=" + result + ", active="
				+ active + ", imagePath=" + imagePath + ", imageTip=" + imageTip + "]";
	}

}
