package com.exam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
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
import com.exam.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	private CategoryServiceImpl categoryServiceImpl2;
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category)
	{
	Category category2=this.categoryServiceImpl.addCategory(category);
		return ResponseEntity.ok(category2);
	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable("id") Long id)
	{
	System.out.println("fuckckkckckk");
		return this.categoryServiceImpl.getCategory(id);
	}
	@GetMapping("/")
	public ResponseEntity<?> getCategories()
	{
	
		return ResponseEntity.ok (this.categoryServiceImpl.getCategories());
	}
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category)
	{
	Category category2=this.categoryServiceImpl.updateCategory(category);
		return category2;
	}
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable("id") Long id)
	{
	this.categoryServiceImpl.deleteCategory(id);
	
	}
}
