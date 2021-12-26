package com.exam.services;

import java.util.List;
import java.util.Set;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;


public interface QuizService {
public Quiz addQuiz(Quiz quiz);
public Quiz updateQuiz(Quiz quiz);
public Set<Quiz> getAllQuizzes();
public Quiz getQuiz(Long quizId);
public void deleteQuiz(Long id);
public List<Quiz> getQuizzesOfCategory(Category category);
public List<Quiz> getActiveQuizzes();
public List<Quiz> getActiveQuizzesOfCategory(Category category);
}
