package com.global.book.dto;

import com.global.book.entity.Author;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {

	private Long id ;
	
     @NotBlank
	private String name ;
	
     @Min(value = 5)
 	@Max(value = 1000)
	private double price;
	
	@NotNull
	private Author author;

}
