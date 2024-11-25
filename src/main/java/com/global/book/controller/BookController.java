package com.global.book.controller;

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
	private  final BookMapper bookMapper;

	@GetMapping("/custom/{id}")
	 public ResponseEntity<?>  findByIdWithAuthor(@PathVariable Long id) {
		Book entity=bookService.findByIdWithAuthor(id);
		 return ResponseEntity.ok(bookMapper.bookEntityToDto(entity));
	 }

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Book book = bookService.findById(id);
		 BookDto dto=bookMapper.bookEntityToDto(book);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/findall")
	public ResponseEntity<?> findAll() {
	List<Book> entityList=bookService.findAll();
		return ResponseEntity.ok(bookMapper.convertBookEntityListToDtoList(entityList));
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid  BookDto dto) {
		return ResponseEntity.ok(bookMapper.bookEntityToDto(bookService.insert(bookMapper.bookDtoToEntity(dto))));
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid  BookDto dto) {
		return ResponseEntity.ok(bookMapper.bookEntityToDto(bookService.update(bookMapper.bookDtoToEntity(dto))));
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
