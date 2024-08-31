package com.crio.xlido.entities;

public class Question implements Comparable<Question>{

    private final Long questionId;
    private final Long eventId;
    private final Long userid;
    private final String content;
    private Long upvote;

    public Question(String content, Long userId, Long eventId) {
        this.questionId = null;
        this.eventId = eventId;
        this.userid = userId;
        this.content = content;
        this.upvote = 0L;
    }

    public Question(long questionId, Question question) {
        this.questionId = questionId;
        this.eventId = question.getEventId();
        this.userid = question.getUserid();
        this.content = question.getContent();
        this.upvote = 0L;
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

    public Long getUpvote() {
        return upvote;
    }

    public void setUpvote(Long upvote) {
        this.upvote = upvote;
    }

   @Override
   public int compareTo(Question other){
    return other.upvote.compareTo(this.upvote);
   }
    
}
