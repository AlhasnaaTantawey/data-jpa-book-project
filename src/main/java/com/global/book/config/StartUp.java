package com.global.book.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.service.AuthorService;
import com.global.book.service.BookService;

@Component
public class StartUp implements CommandLineRunner {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		if(bookService.findAll().isEmpty()) {
		
		//adding some data for authors
		Author author1 =new Author();
		author1.setName("ali");
	
	
		Author author2 =new Author();
		author2.setName("Alhasnaa");
		
		Author author3 =new Author();
		author3.setName("Mohamed");
		
		authorService.insert(Arrays.asList(author1,author2,author3));
		
		
		
		//adding some  data for books
		Book book1 =new Book();
		book1.setName("Java JPA");
		book1.setPrice(200);
		book1.setAuthor(authorService.getReferenceById(1L));
	
	
		Book book2 =new Book();
		book2.setName("Data Base Mysql");
		book2.setPrice(120);
		book2.setAuthor(authorService.getReferenceById(2L));
		
		Book book3 =new Book();
		book3.setName("python");
		book3.setPrice(700);
		book3.setAuthor(authorService.getReferenceById(3L));
		
		bookService.insert(Arrays.asList(book1,book2,book3));
		}
	}

}
