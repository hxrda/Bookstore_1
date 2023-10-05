package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.Category;
import com.example.Bookstore.model.CategoryRepository;


@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;
    
    @Test
    public void findByAuthorShouldReturnBookTitle() {
        List<Book> books = repository.findByAuthor("George Orwell");  
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Animal Farm");
    }
    
    @Test
    public void findByBookTitleShouldReturnAuthor() {
        List<Book> books = repository.findByTitle("A farewell to Arms");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
    }
    
    @Test
    public void createNewBook() {
    	Category category = new Category("Classics");
    	crepository.save(category);
    	Book book = new Book("Mary Shelley", "Frankenstein", "03666744-4", 1818, 5.99, category);
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    

    @Test
    public void deleteNewBook() {
		List<Book> books = repository.findByAuthor("George Orwell");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByAuthor("George Orwell");
		assertThat(newBooks).hasSize(0);
     }

}
