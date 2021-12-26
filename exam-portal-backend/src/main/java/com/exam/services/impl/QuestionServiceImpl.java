package com.exam.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.services.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return ((CrudRepository<Question, Long>) this.questionRepository).save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return ((CrudRepository<Question, Long>) this.questionRepository).save(question);
	}

	@Override
	public Set<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(((JpaRepository<Question, Long>) this.questionRepository).findAll());
	}

	@Override
	public Set<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepository.findByQuiz(quiz);
	}
	public void deleteQuestionById(Long qid)
	{
		Question q=new Question();
		q.setQuesId(qid);
		this.questionRepository.delete(q);
		
	}
	

	

	
	

}
