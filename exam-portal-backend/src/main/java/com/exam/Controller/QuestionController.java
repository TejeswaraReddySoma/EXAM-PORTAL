package com.exam.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.services.impl.QuestionServiceImpl;
import com.exam.services.impl.QuizServiceImpl;




@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	@Autowired
	private QuizServiceImpl quizServiceImpl;

	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Question question)
	{
		System.out.println("jfjfjjfjfj");
		return ResponseEntity.ok(this.questionServiceImpl.addQuestion(question));
	}
	@PutMapping("/")
	public Question updateCategory(@RequestBody Question question)
	{
	return this.questionServiceImpl.updateQuestion(question);
	 
	}
	@GetMapping("/")
	public ResponseEntity<?> getAll()
	{
		return ResponseEntity.ok(this.questionServiceImpl.getAllQuestions());
	}
	@GetMapping("/quiz/{id}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("id") Long id)
	{
//		Quiz quiz=new Quiz();
//		quiz.setqId(id);
//		return ResponseEntity.ok(this.questionServiceImpl.getQuestionsOfQuiz(quiz));
		//we dont want all questions
	Quiz quiz=quizServiceImpl.getQuiz(id);
	Set<Question> set=quiz.getQuestions();
	List list=new ArrayList(set);	
	if(list.size()>Integer.parseInt(quiz.getNoOfQuestions()))
	{
		list=list.subList(0, Integer.parseInt(quiz.getNoOfQuestions()+1));
	}
	Collections.shuffle(list);
	
	return ResponseEntity.ok(list);
		
	}
	@GetMapping("/quiz/all/{id}")
	public ResponseEntity<?> getQuestionsOfQuizForAdmin(@PathVariable("id") Long id)
	{

	Quiz quiz=quizServiceImpl.getQuiz(id);
	Set<Question> set=quiz.getQuestions();

	return ResponseEntity.ok(set);
		
	}
	
	@DeleteMapping("/{Id}")
	public void deleteQuestion(@PathVariable("Id") String id1)
	{
		Long id=Long.parseLong(id1);
		this.questionServiceImpl.deleteQuestionById(id);
	}
}
