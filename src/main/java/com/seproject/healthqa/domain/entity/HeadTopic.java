/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seproject.healthqa.domain.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 59050320
 */
@Entity
@Table(name = " head_topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HeadTopic.findAll", query = "SELECT h FROM HeadTopic h")
    , @NamedQuery(name = "HeadTopic.findByHeadTopicId", query = "SELECT h FROM HeadTopic h WHERE h.headTopicId = :headTopicId")
    , @NamedQuery(name = "HeadTopic.findByCreatedDate", query = "SELECT h FROM HeadTopic h WHERE h.createdDate = :createdDate")
    , @NamedQuery(name = "HeadTopic.findByQuestionType", query = "SELECT h FROM HeadTopic h WHERE h.questionType = :questionType")
    , @NamedQuery(name = "HeadTopic.findByQuestionPurpose", query = "SELECT h FROM HeadTopic h WHERE h.questionPurpose = :questionPurpose")
    , @NamedQuery(name = "HeadTopic.findByIsDeleted", query = "SELECT h FROM HeadTopic h WHERE h.isDeleted = :isDeleted")
    , @NamedQuery(name = "HeadTopic.findByReportStatus", query = "SELECT h FROM HeadTopic h WHERE h.reportStatus = :reportStatus")})
public class HeadTopic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEAD_TOPIC_ID")
    private Integer headTopicId;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "QUESTION_TYPE")
    private Character questionType;
    @Size(max = 255)
    @Column(name = "QUESTION_PURPOSE")
    private String questionPurpose;
    @Column(name = "IS_DELETED")
    private Character isDeleted;
    @Column(name = "REPORT_STATUS")
    private Character reportStatus;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @OneToOne
    private User userId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "headTopic")
    private Topic topic;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "headTopic")
    private Comment comment;

    public HeadTopic() {
    }

    public HeadTopic(Integer headTopicId) {
        this.headTopicId = headTopicId;
    }

    public Integer getHeadTopicId() {
        return headTopicId;
    }

    public void setHeadTopicId(Integer headTopicId) {
        this.headTopicId = headTopicId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Character getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Character questionType) {
        this.questionType = questionType;
    }

    public String getQuestionPurpose() {
        return questionPurpose;
    }

    public void setQuestionPurpose(String questionPurpose) {
        this.questionPurpose = questionPurpose;
    }

    public Character getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Character isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Character getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Character reportStatus) {
        this.reportStatus = reportStatus;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (headTopicId != null ? headTopicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HeadTopic)) {
            return false;
        }
        HeadTopic other = (HeadTopic) object;
        if ((this.headTopicId == null && other.headTopicId != null) || (this.headTopicId != null && !this.headTopicId.equals(other.headTopicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seproject.healthqa.domain.entity.HeadTopic[ headTopicId=" + headTopicId + " ]";
    }
    
}
