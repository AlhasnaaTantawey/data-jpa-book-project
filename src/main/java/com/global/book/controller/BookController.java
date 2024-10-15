package com.global.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Book;
import com.global.book.dto.BookDto;
import com.global.book.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	@GetMapping("/custom/{id}")
	 public ResponseEntity<Book>  findByIdWithAuthor(@PathVariable Long id) {
		 return ResponseEntity.ok(bookService.findByIdWithAuthor(id));
	 }
	
	
	private BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		Book book = bookService.findById(id);

		BookDto bookDto = new BookDto();

		bookDto.setId(book.getId());
		bookDto.setName(book.getName());
		bookDto.setPrice(book.getPrice());
		bookDto.setAuthor(book.getAuthor());
		return ResponseEntity.ok(bookDto);
	}

	@GetMapping
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(bookService.findAll());
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid  BookDto dto) {

		Book book =new Book();
		book.setPrice(dto.getPrice());
		book.setName(dto.getName());
		book.setAuthor(dto.getAuthor());
		
		
		return ResponseEntity.ok(bookService.insert(book));
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid  Book book) {

		return ResponseEntity.ok(bookService.update(book));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {

		bookService.deleteById(id);
		return ResponseEntity.ok(null);

	}

	@DeleteMapping("/author/{id}")
	public ResponseEntity<?> deleteByAuthorId(@PathVariable Long id) {

		return ResponseEntity.ok(bookService.deleteByAuthorId(id)); 

	}
	
	

}
