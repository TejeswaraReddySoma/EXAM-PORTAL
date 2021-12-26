package com.exam.services.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.exam.models.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;


public  Category addCategory(Category category) {
	// TODO Auto-generated method stub
	

return this.categoryRepository.save(category);
}


public Category updateCategory(Category category) {
	// TODO Auto-generated method stub
	return this.categoryRepository.save(category);
}

public Set<Category> getCategories() {
	// TODO Auto-generated method stub
	Set<Category> set=new LinkedHashSet<>(((JpaRepository<Category, Long>) this.categoryRepository).findAll());

		return set;
}


public Category getCategory(Long id) {
	// TODO Auto-generated method stub
	return this.categoryRepository.findById(id).get();
}


public void deleteCategory(Long id) {
	Category category=new Category();
	category.setcId(id);
	categoryRepository.delete(category);
	
}



}
