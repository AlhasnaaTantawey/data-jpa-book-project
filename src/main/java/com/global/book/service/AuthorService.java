package com.global.book.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.errors.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;


@Service
@CacheConfig(cacheNames = "autherValueForAllMethods")
@RequiredArgsConstructor
@Log4j2
public class AuthorService extends BaseService<Author, Long> {

	private final AuthorRepo authorRepo;

	//cachable --> to add in cache
	@Override
	@Cacheable(value = "findAllAuthor", key = "#root.methodName")
	public List<Author> findAll() {
		return super.findAll();
	}

	@Override
	@Cacheable( key = "#id")
	public Author findById(Long id) {
		return super.findById(id);
	}

	//cacheevict to remove from cache to add changes to there are not gap betw DB and cache
	@Override
	//@CachePut(value =  "insertAuthor", key = "#root.methodName" )
	@CacheEvict(value = "findAllAuthor", key = "#root.methodName" ,allEntries = true)
//	@Caching(evict = {@CacheEvict(value = "insertAuthor") ,
//			@CacheEvict(value = "anyanothername" ,key = "#Author.id")})
	public Author insert(Author entity) {
		if(entity.getEmail() !=null && !entity.getEmail().isEmpty()) {
			Optional<Author> author= findByEmail(entity.getEmail());
			log.info("author name is {} and email is {}", entity.getName(), entity.getEmail());
			if(author.isPresent()) {
				log.error("email.message");
				throw new DuplicateRecordException("email.message");
			}
		}
		return super.insert(entity);
	}

	@Override
	@CacheEvict(value = "findAllAuther", key = "#root.methodName" ,allEntries = true)
	//@CacheEvict(value = {"findAllAuther","findByIdAuther" }, key = "#root.methodName" , allEntries = true)
	public Author update(Author entity) {
		Author author= findById(entity.getId());
		author.setName(entity.getName());
		author.setIpAdress(entity.getIpAdress());
		return super.update(author);
	}

	public List<Author>  findByAuthorSpec( AuthorSearch search){
		AuthorSpec authorSpec =new AuthorSpec(search);
		return   authorRepo.findAll(authorSpec);
	}

	 public Optional<Author> findByEmail(String email) {
		 return authorRepo.findByEmail(email);
	 }

	@Override
	@CacheEvict(value = "findAllAuther", key = "#root.methodName" ,allEntries = true)
	public void deleteById(Long aLong) {
		super.deleteById(aLong);
	}

	//	@Async(value = "taskExecutorDefault")
// public CompletableFuture<Author> findByEmail(String email) {
//		 
//		 return CompletableFuture.completedFuture(authorRepo.findByEmail(email).get())  ;
//		 
//
//	 }
	
	

}
