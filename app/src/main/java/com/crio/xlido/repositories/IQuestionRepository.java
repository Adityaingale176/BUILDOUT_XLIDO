package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {

    Question addQuestion(Question question);

    Optional<Question> findById(Long questionId); 

    void deleteQuestion(Long questionId);

    void upvoteQuestion(Long questionId, Long userId);

    void replyQuestion(Long questionId, String content, Long userId);

    public List<Question> findByEventId(Long eventId);

    public List<Question> findAll();

    public Long getVotesByQuestionId(Long questionId);

    public String getReplyByQuestionID(Long questionId);

    public Long getReplyUSerByQuestionID(Long questionId);

}
