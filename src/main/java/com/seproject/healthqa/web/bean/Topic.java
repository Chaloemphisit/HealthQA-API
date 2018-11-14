package com.seproject.healthqa.web.bean;

import java.sql.Timestamp;
import java.util.List;
import sun.awt.image.ImageWatched.Link;

public class Topic {

    private String topicId;
    private String topicName;
    private String topicText;
    private Integer height;
    private Integer weight;
    private Integer ageY;
    private Integer ageM;
    private Integer ageD;
    private String gender;
    private String disease;
    private String questionPurpose;
    private String questionType;
    private String name;
    private String answerCount;
    private Timestamp createDate;

    private List<Comments> comments;

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

    public String getTopicText() {
        return topicText;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAgeY() {
        return ageY;
    }

    public void setAgeY(Integer ageY) {
        this.ageY = ageY;
    }

    public Integer getAgeM() {
        return ageM;
    }

    public void setAgeM(Integer ageM) {
        this.ageM = ageM;
    }

    public Integer getAgeD() {
        return ageD;
    }

    public void setAgeD(Integer ageD) {
        this.ageD = ageD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getQuestionPurpose() {
        return questionPurpose;
    }

    public void setQuestionPurpose(String questionPurpose) {
        this.questionPurpose = questionPurpose;
    }
    

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    
}
