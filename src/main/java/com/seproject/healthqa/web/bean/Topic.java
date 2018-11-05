package com.seproject.healthqa.web.bean;

import java.util.List;
import sun.awt.image.ImageWatched.Link;

public class Topic {

    private String topicName;
    private String topicText;
    private Integer height;
    private Integer wieght;
    private Integer ageY;
    private Integer ageM;
    private String gender;
    private String disease;
    private String questionType;
    private String username;
    private String commentCount;
    
    private List<Comment> comment;

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

    public Integer getWieght() {
        return wieght;
    }

    public void setWieght(Integer wieght) {
        this.wieght = wieght;
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

}
