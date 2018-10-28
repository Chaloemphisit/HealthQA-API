package com.seproject.healthqa.web.bean;

import java.util.Date;

public class AddQuestion{

	private String topicName;
	private String topicText;
	private String questionPurpose;
	private Character questionType;
	private Character sex;
	private Integer weight;
	private Integer height;
	private Date birthday;
	private String disease;
	
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicText() {
		return topicText;
	}
	public void setTopicText(String topicText) {
		this.topicText = topicText;
	}
	public String getQuestionPurpose() {
		return questionPurpose;
	}
	public void setQuestionPurpose(String questionPurpose) {
		this.questionPurpose = questionPurpose;
	}
	public Character getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Character questionType) {
		this.questionType = questionType;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	
	
}