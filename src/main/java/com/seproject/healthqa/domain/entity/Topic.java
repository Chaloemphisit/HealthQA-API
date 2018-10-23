/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seproject.healthqa.domain.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t")
    , @NamedQuery(name = "Topic.findByTopicId", query = "SELECT t FROM Topic t WHERE t.topicPK.topicId = :topicId")
    , @NamedQuery(name = "Topic.findByHeadTopicId", query = "SELECT t FROM Topic t WHERE t.topicPK.headTopicId = :headTopicId")
    , @NamedQuery(name = "Topic.findByTopicName", query = "SELECT t FROM Topic t WHERE t.topicName = :topicName")
    , @NamedQuery(name = "Topic.findByTopicText", query = "SELECT t FROM Topic t WHERE t.topicText = :topicText")
    , @NamedQuery(name = "Topic.findByHeight", query = "SELECT t FROM Topic t WHERE t.height = :height")
    , @NamedQuery(name = "Topic.findByWeight", query = "SELECT t FROM Topic t WHERE t.weight = :weight")
    , @NamedQuery(name = "Topic.findByBirthday", query = "SELECT t FROM Topic t WHERE t.birthday = :birthday")
    , @NamedQuery(name = "Topic.findBySex", query = "SELECT t FROM Topic t WHERE t.sex = :sex")
    , @NamedQuery(name = "Topic.findByDisease", query = "SELECT t FROM Topic t WHERE t.disease = :disease")})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TopicPK topicPK;
    @Size(max = 255)
    @Column(name = "TOPIC_NAME")
    private String topicName;
    @Size(max = 10000)
    @Column(name = "TOPIC_TEXT")
    private String topicText;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "WEIGHT")
    private Integer weight;
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEX")
    private Character sex;
    @Size(max = 500)
    @Column(name = "DISEASE")
    private String disease;
    @JoinColumn(name = "HEAD_TOPIC_ID", referencedColumnName = "HEAD_TOPIC_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HeadTopic headTopic;

    public Topic() {
    }

    public Topic(TopicPK topicPK) {
        this.topicPK = topicPK;
    }

    public Topic(TopicPK topicPK, Character sex) {
        this.topicPK = topicPK;
        this.sex = sex;
    }

    public Topic(int topicId, int headTopicId) {
        this.topicPK = new TopicPK(topicId, headTopicId);
    }

    public TopicPK getTopicPK() {
        return topicPK;
    }

    public void setTopicPK(TopicPK topicPK) {
        this.topicPK = topicPK;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public HeadTopic getHeadTopic() {
        return headTopic;
    }

    public void setHeadTopic(HeadTopic headTopic) {
        this.headTopic = headTopic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topicPK != null ? topicPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.topicPK == null && other.topicPK != null) || (this.topicPK != null && !this.topicPK.equals(other.topicPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seproject.healthqa.domain.entity.Topic[ topicPK=" + topicPK + " ]";
    }
    
}
