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
	private String name ;
	private double price;
//	private Author author;

}
