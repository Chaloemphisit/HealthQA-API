/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seproject.healthqa.domain.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 59050320
 */
@Embeddable
public class TopicPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TOPIC_ID")
    private int topicId;
    @Basic(optional = false)	
    @NotNull
    @Column(name = "HEAD_TOPIC_ID")
    private int headTopicId;

    public TopicPK() {
    }

    public TopicPK(int topicId, int headTopicId) {
        this.topicId = topicId;
        this.headTopicId = headTopicId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getHeadTopicId() {
        return headTopicId;
    }

    public void setHeadTopicId(int headTopicId) {
        this.headTopicId = headTopicId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) topicId;
        hash += (int) headTopicId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TopicPK)) {
            return false;
        }
        TopicPK other = (TopicPK) object;
        if (this.topicId != other.topicId) {
            return false;
        }
        if (this.headTopicId != other.headTopicId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seproject.healthqa.domain.entity.TopicPK[ topicId=" + topicId + ", headTopicId=" + headTopicId + " ]";
    }
    
}
