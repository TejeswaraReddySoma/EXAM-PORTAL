package com.exam.services.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return ((CrudRepository<Quiz, Long>) this.quizRepository).save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
	 return ((CrudRepository<Quiz, Long>) this.quizRepository).save(quiz);
	}

	@Override
	public Set<Quiz> getAllQuizzes() {
return new LinkedHashSet<>(((JpaRepository<Quiz, Long>) this.quizRepository).findAll());


	

		
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return ((CrudRepository<Quiz, Long>) this.quizRepository).findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long id) {
		// TODO Auto-generated method stub
		Quiz quiz=new Quiz();
		quiz.setqId(id);
		((CrudRepository<Quiz, Long>) quizRepository).deleteById(id);
		
	}

	public List<Quiz> getQuizzesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepository.findByCategory(category);
	}


	


	public List<Quiz> getActiveQuizzes() {
		// TODO Auto-generated method stub
		return this.quizRepository.findByActive(true);
	}


	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepository.findByActiveAndCategory(true, category);
	}

}
