package com.crio.xlido.entities;

public class Upvote {
    
    private final Long questionId;
    private final Long voteCount;
    
    public Upvote(Long questionId, Long voteCount) {
        this.questionId = questionId;
        this.voteCount = voteCount;
    }

    public Long getQuestionId() {
        return questionId;
    }
    public Long getVoteCount() {
        return voteCount;
    }
}
