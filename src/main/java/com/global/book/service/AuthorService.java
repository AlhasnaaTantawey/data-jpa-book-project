package com.global.book.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.errors.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;


@Service
public class AuthorService extends BaseService<Author, Long> {
     
	@Autowired
	private AuthorRepo authorRepo;
	

	Logger logger =LoggerFactory.getLogger(AuthorService.class);

	@Override
	public Author update(Author entity) {
		
		Author author= findById(entity.getId());
		
		author.setName(author.getName());
		
		return super.update(author);
	}
	
	
	public List<Author>  findByAuthorSpec( AuthorSearch search){
		
		AuthorSpec authorSpec =new AuthorSpec(search);
		
		
		return   authorRepo.findAll(authorSpec);
		
	}

	
	@Override
	public Author insert(Author entity) {
		 if(entity.getEmail() !=null && !entity.getEmail().isEmpty()) {
			 
			 Optional<Author> author= findByEmail(entity.getEmail());
			 
			 logger.info("author name is {} and email is {}", entity.getName(), entity.getEmail());
			 
			 if(author.isPresent()) {
			     logger.error("email.message");
				 throw new DuplicateRecordException("email.message");
			 }
		 }
		
		
		return super.insert(entity);
	}
	
	 public Optional<Author> findByEmail(String email) {
		 
		 return authorRepo.findByEmail(email);
		 

	 }
	
//	@Async(value = "taskExecutorDefault")
// public CompletableFuture<Author> findByEmail(String email) {
//		 
//		 return CompletableFuture.completedFuture(authorRepo.findByEmail(email).get())  ;
//		 
//
//	 }
	
	

}
