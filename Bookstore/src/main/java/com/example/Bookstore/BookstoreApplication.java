package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.model.AppUser;
import com.example.Bookstore.model.AppUserRepository;
import com.example.Bookstore.model.Book;
import com.example.Bookstore.model.BookRepository;
import com.example.Bookstore.model.Category;
import com.example.Bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// Book fields: (id), author, title, isbn, publicationYear-[int], price-[double]

	@Bean
	public CommandLineRunner studentDemo(BookRepository brepository, CategoryRepository crepository,
			AppUserRepository urepository) {
		return (args) -> {
			// Alternatively: syso

			log.info("save a couple of categories");
			crepository.save(new Category("Realism"));
			crepository.save(new Category("Dystopian"));
			crepository.save(new Category("Psychological"));
			// crepository.save(new Category("Autobiography"));
			// crepository.save(new Category("Mystery"));

			log.info("save a couple of books");
			brepository.save(new Book("Ernest Hemingway", "A farewell to Arms", "1232323-21", 1929, 11.99,
					crepository.findByName("Realism").get(0)));
			brepository.save(new Book("George Orwell", "Animal Farm", "2212343-5", 1945, 6.99,
					crepository.findByName("Dystopian").get(0)));
			brepository.save(new Book("Fyodor Dostoevsky", "The Idiot", "03757033-4", 1869, 16.41,
					crepository.findByName("Psychological").get(0)));

			// Create users: admin/admin user/user
			log.info("create a couple of users");
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@email.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			// These cause errors for some reason:
			/*
			 * log.info("fetch all books"); for (Book book : brepository.findAll()) {
			 * log.info(book.toString()); } log.info("fetch all books by author"); for (Book
			 * book : brepository.findByAuthor("author")) { log.info(book.toString()); }
			 */

		};
	}

}
