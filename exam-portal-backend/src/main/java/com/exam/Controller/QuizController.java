
package com.exam.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import com.exam.services.impl.QuizServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizServiceImpl quizServiceImpl;

	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizServiceImpl.addQuiz(quiz));
	}
	@PutMapping("/")
	public Quiz updateCategory(@RequestBody Quiz quiz)
	{
	return this.quizServiceImpl.updateQuiz(quiz);
	 
	}
	@GetMapping("/")
	public ResponseEntity<?> getAll()
	{
		return ResponseEntity.ok(this.quizServiceImpl.getAllQuizzes());
	}
	@GetMapping("/{id}")
	public Quiz getQuizById(@PathVariable("id") Long id)
	{
		return this.quizServiceImpl.getQuiz(id);
	}
	@DeleteMapping("/{id}")
	public void deleteQuizById(@PathVariable("id") Long id)
	{
	 this.quizServiceImpl.deleteQuiz(id);
	}
	@GetMapping("/category/{cId}")
	public ResponseEntity<?> getQuizzesByCategory(@PathVariable("cId")Long id)
	{
		Category category=new Category();
		category.setcId(id);
		return ResponseEntity.ok(quizServiceImpl.getQuizzesOfCategory(category));
	}
	@GetMapping("/active")
	public ResponseEntity<?> getActiveQuizzes()
	{
		
		return ResponseEntity.ok(quizServiceImpl.getActiveQuizzes());
	}
	@GetMapping("/active/{cId}")
	public ResponseEntity<?> getActiveQuizzesByCategory(@PathVariable("cId")Long id)
	{
		Category category=new Category();
		category.setcId(id);
		return ResponseEntity.ok(quizServiceImpl.getActiveQuizzesOfCategory(category));
	}
	
}
