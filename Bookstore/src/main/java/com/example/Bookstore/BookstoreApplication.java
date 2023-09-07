package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// Book fields: (id), author, title, isbn, publicationYear-[int], price-[double]

	@Bean
	public CommandLineRunner studentDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of students");
			repository.save(new Book("Ernest Hemingway", "A farewell to Arms", "1232323-21", 1929, 11.99));
			repository.save(new Book("George Orwell", "Animal Farm", "2212343-5", 1945, 6.99));
			repository.save(new Book("Fyodor Dostoevsky", "The Idiot", "03757033-4", 1869, 16.41));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("fetch all books by author");
			for (Book book : repository.findByAuthor("author")) {
				log.info(book.toString());
			}

		};
	}

}
