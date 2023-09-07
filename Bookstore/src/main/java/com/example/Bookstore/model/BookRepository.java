package com.example.Bookstore.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	// Add methods:
	List<Book> findByAuthor(String author);

}
