package com.global.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Book;

@Repository
public interface BookRepo extends BaseRepository<Book, Long> {

	  @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.id = :id")
	     Book findByIdWithAuthor( Long id);
	
	
	@Override
	@EntityGraph(value = "loadBook")
	 Optional<Book> findById(Long id) ;
	
     @Override
     @EntityGraph(attributePaths = {"author"})
    List<Book> findAll();
    
     @Transactional(readOnly = false)
     @Modifying
     @Query(value = "delete from Book book where book.author.id = :id")
     int deleteByBookAuthorId(Long id);
	
     
   
	
}
