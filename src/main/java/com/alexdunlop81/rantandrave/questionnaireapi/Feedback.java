package com.alexdunlop81.rantandrave.questionnaireapi;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Feedback {
	
	@NotNull
	@Min(1)
	@Max(10)
	private Integer score;
	@NotNull
	@NotEmpty
	
	private String name;
	@NotNull
	@NotEmpty
	private String comment;
	
	public Feedback(){
		
	}
	
	

	
	public Feedback(Feedback feedback) {
		this.name = feedback.name;
		this.score = feedback.score;
		this.comment = feedback.comment;
		}

	public String getComment() {
		return comment;
	}
	public String getName() {
		return name;
	}
	public Integer getScore() {
		return score;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

}
