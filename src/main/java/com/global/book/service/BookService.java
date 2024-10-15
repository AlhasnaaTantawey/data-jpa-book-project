package com.global.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.BookRepo;

@Service
public class BookService extends BaseService<Book, Long> {


	  public Book findByIdWithAuthor( Long id) {
		 return bookRepo.findByIdWithAuthor(id);
	 }
	
	
	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	
	
	@Override
	public Book update(Book entity) {
		Book book = findById(entity.getId());

		book.setName(entity.getName());
		return super.update(book);
	}
	

	
	public int deleteByAuthorId(Long id) {
		
		return bookRepo.deleteByBookAuthorId(id);

	}
	
	
	
}
