package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

//import com.example.Bookstore.model.Book;
//import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.Category;
import com.example.Bookstore.model.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
	
	//@Autowired
    //private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;
    
    @Test
    public void findByNameShouldReturnExistingCategory() {
        List<Category> categories = crepository.findByName("Realism");  
        
        assertThat(categories).hasSize(1);
        //Other assertions? -> No other fields available except arraylist for books.
    }
    
    @Test
    public void createNewCategory() {
    	Category category = new Category("Classics");
    	crepository.save(category);
    	assertThat(category.getCategoryId()).isNotNull();
    }    
    

    @Test
    public void deleteNewCategory() {
    	List<Category> categories = crepository.findByName("Realism");
    	Category category = categories.get(0);
    	crepository.delete(category);
    	
    	List<Category> newCategories = crepository.findByName("Realism");
    	assertThat(newCategories).hasSize(0);
     }

}
