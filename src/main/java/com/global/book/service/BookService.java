package com.global.book.service;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.BookRepo;

@Service
@RequiredArgsConstructor
public class BookService extends BaseService<Book, Long> {

	private final BookRepo bookRepo;

	public Book findByIdWithAuthor( Long id) {
		 return bookRepo.findByIdWithAuthor(id);
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