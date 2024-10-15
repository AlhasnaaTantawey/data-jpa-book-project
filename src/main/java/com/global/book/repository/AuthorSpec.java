package com.global.book.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.entity.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AuthorSpec implements Specification<Author> {

	private AuthorSearch search;

	public AuthorSpec(AuthorSearch search) {
		super();
		this.search = search;
	}

	@Override
	public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		
		
		Join<Author, Book> joinBook = root.join("books", JoinType.INNER);


		List<Predicate> predicates = new ArrayList<>();

		// AuthorName
		if (search.getAuthorName() != null && !search.getAuthorName().isEmpty()) {

			predicates.add(cb.like(root.get("name"), search.getAuthorName()));
		}

		// email
		if (search.getEmail() != null && !search.getEmail().isEmpty()) {

			predicates.add(cb.like(root.get("email"), search.getEmail()));
		}

		// ipAdress
		if (search.getIpAdress() != null && !search.getIpAdress().isEmpty()) {

			predicates.add(cb.like(root.get("ipAdress"), search.getIpAdress() ));
		}

		// bookName
		if (search.getBookName() != null && !search.getBookName().isEmpty()) {

			predicates.add(cb.like(joinBook.get("name") ,  search.getBookName() ));
		}

		// price
		if (search.getPrice() != null ) {

			predicates.add(cb.greaterThanOrEqualTo(joinBook.get("price"), search.getPrice()));
		}
		
		
		
		  query.distinct(true);

		return cb.and(predicates.toArray(new Predicate[0]));

	}

}
