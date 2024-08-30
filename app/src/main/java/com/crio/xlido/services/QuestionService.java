package com.crio.xlido.services;

import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IQuestionRepository;
import com.crio.xlido.repositories.IUserRepository;

public class QuestionService {

    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;
    private final IQuestionRepository questionRepository;

    public QuestionService(IEventRepository eventRepository, IUserRepository userRepository, IQuestionRepository questionRepository) {
        this.eventRepository = eventRepository ;
        this.userRepository = userRepository ;
        this.questionRepository = questionRepository ;
    }

    public Question ADD_QUESTION(String content, Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with an id "+userId+" does not exist"));
        Event event = eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("Event with an id "+eventId+" does not exist"));
        Question question = new Question( content, userId, eventId);
        return questionRepository.addQuestion(question);
    }

    public Question DELETE_QUESTION(Long questionId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with an id "+userId+" does not exist"));
        Question question = questionRepository.findById(questionId).orElseThrow(()->new RuntimeException("Question with an id "+questionId+" does not exist"));
        if (!question.getUserid().equals(userId)){
            throw new RuntimeException("User with an id "+userId+" is not an author of question with an id "+questionId);
        }
        questionRepository.deleteQuestion(questionId);
        return question;
    }

    public Question UPVOTE_QUESTION(Long questionId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with an id "+userId+" does not exist"));
        Question question = questionRepository.findById(questionId).orElseThrow(()->new RuntimeException("Question with an id "+questionId+" does not exist"));
        questionRepository.upvoteQuestion(questionId, userId);
        return question;
    }

    public Question REPLY_QUESTION(String content, Long questionId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with an id "+userId+" does not exist"));
        Question question = questionRepository.findById(questionId).orElseThrow(()->new RuntimeException("Question with an id "+questionId+" does not exist"));
        questionRepository.replyQuestion(questionId, content);
        return question;
    }

    public Question LIST_QUESTIONS(Long eventId, String sortBy) {
        Event event = eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("Event with an id "+eventId+" does not exist"));
        //questionRepository.listQuestions(eventId, sortBy);
        return null;
    }

}
