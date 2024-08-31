package com.crio.xlido.entities;

public class Reply {

    private final Long questionId;
    private final String content;
    private final Long userId;

    public Reply(Long questionId, String content, Long userId) {
        this.questionId = questionId;
        this.content = content;
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }
    

    
}
