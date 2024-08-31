package com.crio.xlido.repositories;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.Reply;
import com.crio.xlido.entities.Upvote;
import com.crio.xlido.entities.User;

public class QuestionRepository implements IQuestionRepository{
    //<QuestionId, Question>
    private final Map<Long, Question> storage = new HashMap();
     //<UserID, QuestionId>
    private final Map<Long, Long> upvote = new HashMap();
    //<QuestionId, reply>
    private final Map<Long, Reply> reply = new HashMap();
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
            Question question = storage.get(questionId);

            if (question.getUpvote() == 0L) {
                question.setUpvote(1L);
            } else {
                question.setUpvote(question.getUpvote() + 1L);
            }
        }
    }

    @Override
    public void replyQuestion(Long questionId, String content, Long userId ) {
        
        Reply replyEntity = new Reply(questionId, content, userId);
        //Reply reply = new Reply(questionId, replyEntity);
        reply.put(questionId, replyEntity);

    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Question> findByEventId(Long eventId) {
        List<Question> questions = findAll();
        return questions.stream().filter(q -> q.getEventId() == eventId).collect(Collectors.toList());
    } 

    @Override
    public Long getVotesByQuestionId(Long questionId){
        if(voteCounter.get(questionId)!=null){
            return voteCounter.get(questionId);
        }
        else{
            return 0L;
        }
    }

    @Override
    public String getReplyByQuestionID(Long questionId){
        if (reply.containsKey(questionId)){
            return reply.get(questionId).getContent();
        }
        else {
            return null;
        }
    }

    @Override
    public Long getReplyUSerByQuestionID(Long questionId){
        return reply.get(questionId).getUserId();
    }

}
