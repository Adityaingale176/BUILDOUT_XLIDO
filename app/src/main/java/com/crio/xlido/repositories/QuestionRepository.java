package com.crio.xlido.repositories;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;

public class QuestionRepository implements IQuestionRepository{
    //<QuestionId, Question>
    private final Map<Long, Question> storage = new HashMap();
     //<UserID, QuestionId>
    private final Map<Long, Long> upvote = new HashMap();
    //<QuestionId, reply>
    private final Map<Long, String> reply = new HashMap();
    //<QuestionId, Votes>
    private final Map<Long, Long> voteCounter = new HashMap();
    //<EventId, QuestionId>
    private final Map<Long, Long> eventMapper = new HashMap();

    //<UserID, QuestionId>

    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Question addQuestion(Question entity) {
       
        Question question = new Question(idCounter.incrementAndGet(), entity);
        storage.put(question.getQuestionId(), question);
        eventMapper.put(question.getEventId(), question.getQuestionId());
        return question;
    }

    @Override
    public Optional<Question> findById(Long questionId) {
        return Optional.ofNullable(storage.get(questionId));
    }

    @Override
    public void deleteQuestion(Long questionId) {
        storage.remove(questionId);
    }

    @Override
    public void upvoteQuestion(Long questionId, Long userId) {
        if (upvote.get(userId)==questionId){
            throw new RuntimeException("User with an id "+userId+" has already upvoted a question with an id "+questionId);
        }
        else{
            upvote.put(userId, questionId);
            voteCounter.put(questionId, voteCounter.getOrDefault(questionId, (long) 0)+1);
        }
    }

    @Override
    public void replyQuestion(Long questionId, String content ) {
     reply.put(questionId, content);
    }


    @Override
    public List<Question> listQuestions(List eventId, String sortBy) {
       
        // Collect all QuestionId related to the given EventId
        List<Long> questionIds = new ArrayList<>();
        for (Map.Entry<Long, Long> entry : eventMapper.entrySet()) {
            if (Objects.equals(entry.getKey(), eventId)) {
                questionIds.add(entry.getValue());
            }
        }
        return questionIds;
       
        
    }
    
}
