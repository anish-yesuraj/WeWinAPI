package com.ay.wewin.api.model;

import java.io.Serializable;
import java.util.Objects;

public class AnswerChoiceId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String question ;
	
	
	public AnswerChoiceId() {
	}

	public AnswerChoiceId(String question, String id) {
		this.question = question;
		this.id = id;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerChoiceId that = (AnswerChoiceId) o;

        if (id != that.id) return false;
        return question != null ? question.equals(that.question) : that.question == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,question);
    }

	@Override
	public String toString() {
		return "AnswerChoiceId [question_id=" + question + ", id=" + id + "]";
	}
    
    
	
	
}
