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
public class CommentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "COMMENT_ID")
    private int commentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEAD_TOPIC_ID")
    private int headTopicId;

    public CommentPK() {
    }

    public CommentPK(int commentId, int headTopicId) {
        this.commentId = commentId;
        this.headTopicId = headTopicId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
        hash += (int) commentId;
        hash += (int) headTopicId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentPK)) {
            return false;
        }
        CommentPK other = (CommentPK) object;
        if (this.commentId != other.commentId) {
            return false;
        }
        if (this.headTopicId != other.headTopicId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seproject.healthqa.domain.entity.CommentPK[ commentId=" + commentId + ", headTopicId=" + headTopicId + " ]";
    }
    
}
