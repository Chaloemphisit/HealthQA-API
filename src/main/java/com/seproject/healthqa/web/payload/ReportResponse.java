package com.seproject.healthqa.web.payload;

import com.seproject.healthqa.web.bean.ReportCommentResponse;
import com.seproject.healthqa.web.bean.ReportTopicResponse;
import java.util.List;

public class ReportResponse<T> {

    private List<ReportTopicResponse> topic;
    private List<ReportCommentResponse> comment;

    public List<ReportTopicResponse> getTopic() {
        return topic;
    }

    public void setTopic(List<ReportTopicResponse> topic) {
        this.topic = topic;
    }

    public List<ReportCommentResponse> getComment() {
        return comment;
    }

    public void setComment(List<ReportCommentResponse> comment) {
        this.comment = comment;
    }

   
}
