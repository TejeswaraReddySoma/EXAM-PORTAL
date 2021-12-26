package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
public List<Quiz> findByCategory(Category category);
public List<Quiz> findByActive(Boolean b);
public List<Quiz> findByActiveAndCategory(Boolean b,Category category);


}
