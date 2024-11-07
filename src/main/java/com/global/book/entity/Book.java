package com.global.book.entity;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.global.book.base.BaseEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@NamedEntityGraph(name = "loadBook" ,attributeNodes = @NamedAttributeNode(value = "author"))
@Entity
@Table(name="books")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends BaseEntity<Long> implements Serializable {


	@NotNull(message = "Should be enter name book ")
	@Column(name = "book_name")
	private String name ;
	
	@Min(value = 5)
	@Max(value = 1000)
	private double price;
	
//	@Formula("(select count(*) from  books)")
//	private double bookCount;
	
	
	@Transient
	private double discounted ;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
    @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Author author;



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public double getDiscounted() {
		return discounted;
	}


	public void setDiscounted(double discounted) {
		this.discounted = discounted;
	}
	
	//execute before load entity and execute only one
	@PostLoad
	public void calcDiscount() {
		this.setDiscounted(price * .25);
	}


//	public double getBookCount() {
//		return bookCount;
//	}
//
//
//	public void setBookCount(double bookCount) {
//		this.bookCount = bookCount;
//	}
	
	
}
