package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {

    Question addQuestion(Question question);

    Optional<Question> findById(Long questionId); 

    void deleteQuestion(Long questionId);

    void upvoteQuestion(Long questionId, Long userId);

    void replyQuestion(Long questionId, String content);

    List<Question> listQuestions(List eventId, String sortBy);
    

}
