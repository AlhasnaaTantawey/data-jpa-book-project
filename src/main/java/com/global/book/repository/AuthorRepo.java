package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Author;
import com.global.book.entity.Book;

@Repository
public interface AuthorRepo extends BaseRepository<Author, Long> ,JpaSpecificationExecutor<Author> {

	
	@Override
	@EntityGraph(value = "loadAuthor")
	 Optional<Author> findById(Long id) ;
	
     @Override
     @EntityGraph(attributePaths = {"books"})
    List<Author> findAll();
	
     
     Optional<Author> findByEmail(String email) ;
}
