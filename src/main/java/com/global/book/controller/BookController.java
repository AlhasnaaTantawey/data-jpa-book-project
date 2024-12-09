package com.global.book.controller;

import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;
import com.global.book.mapper.BookAuthorMapper;
import com.global.book.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	private  final BookAuthorMapper bookAuthorMapper;
	private  final BookMapper bookMapper;

	@GetMapping("/custom/{id}")
	 public ResponseEntity<?>  findByIdWithAuthor(@PathVariable Long id) {
		Book entity=bookService.findByIdWithAuthor(id);
		//BookDto dto=  bookAuthorMapper.bookEntityToDto(entity);
		BookDto dto=  bookMapper.map(entity);
		 return ResponseEntity.ok(dto);
	 }

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Book book = bookService.findById(id);
		BookDto dto=  bookMapper.map(book);
		// BookDto dto= bookAuthorMapper.bookEntityToDto(book);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/findall")
	public ResponseEntity<?> findAll() {
	List<Book> entityList=bookService.findAll();
	List<BookDto> dtoList=	bookMapper.maptoList(entityList);
	//List<BookDto> dtoList=	bookAuthorMapper.convertBookEntityListToDtoList(entityList);
		return ResponseEntity.ok(dtoList);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid BookDto bookDto) {
		Book book = bookMapper.unmap(bookDto);
		Book bookentity = bookService.insert(book);
		BookDto dto = bookMapper.map(bookentity);
		return ResponseEntity.ok(dto);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid  BookDto bookDto) {
		Book entity = bookMapper.unmap(bookDto);
		Book bookentity=	bookService.update(entity);
		BookDto dto=  bookMapper.map(bookentity);
		return ResponseEntity.ok(dto);
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
