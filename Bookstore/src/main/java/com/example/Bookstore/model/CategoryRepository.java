package com.example.Bookstore.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	// Add methods:
	List<Category> findByName(String name);

}
