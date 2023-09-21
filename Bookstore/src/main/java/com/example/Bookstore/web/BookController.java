package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.CategoryRepository;



@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;

	// THYMELEAF: List of books (front page):
	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//RESTful: get/list all books:
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    } 
	
	//RESTful: get/list 1 student by ID:
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }

	// THYMELEAF: Form to add books:
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	// THYMELEAF: Save functionality for form (the endpoint where the form(s) will be
	// submitted):
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	// THYMELEAF: Delete book (from front page):
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	// THYMELEAF: Form to edit books:
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories",crepository.findAll());
		return "editbook";
	}
}
