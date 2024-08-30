package com.crio.xlido.entities;

public class Question {

    private final Long questionId;
    private final Long eventId;
    private final Long userid;
    private final String content;

    public Question(String content, Long userId, Long eventId) {
        this.questionId = null;
        this.eventId = eventId;
        this.userid = userId;
        this.content = content;
    }

    public Question(long questionId, Question question) {
        this.questionId = questionId;
        this.eventId = question.getEventId();
        this.userid = question.getUserid();
        this.content = question.getContent();
    }

    public Long getEventId() {
        return eventId;
    }
    public Long getUserid() {
        return userid;
    }
    public String getContent() {
        return content;
    }
    public Long getQuestionId() {
        return questionId;
    } 
    
}
