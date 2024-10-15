package com.global.book.dto;

import com.global.book.entity.Author;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDto {

	
	private Long id ;
	
     @NotBlank
	private String name ;
	
     @Min(value = 5)
 	@Max(value = 1000)
	private double price;
	
	@NotNull
	private Author author;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	
	
	
	
}
