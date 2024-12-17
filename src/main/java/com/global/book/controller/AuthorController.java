package com.global.book.controller;

import java.util.List;

import com.global.book.dto.AuthorDto;
import com.global.book.mapper.AuthorMapper;
import com.global.book.mapper.BookAuthorMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Author;
import com.global.book.entity.AuthorSearch;
import com.global.book.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Tag(name = "web service auther ")
@Validated
@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;
	private  final BookAuthorMapper bookAuthorMapper;
	private final AuthorMapper authorMapper;

    @Operation(summary = "get an auther by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved an auther"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = " an auther is Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "an auther not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> findById( @Parameter(description = "auther id" , example = "id from 1 : 12") @PathVariable @Min(value = 1) @Max(value = 50) Long id) {
		Author entity=authorService.findById(id);
		AuthorDto dto= authorMapper.map(entity);
//		AuthorDto dto=bookAuthorMapper.authorEntityToDto(entity);
		return ResponseEntity.ok(dto);
	}
     @Operation(summary = "get all authers")
	@GetMapping
	public ResponseEntity<?> findAll() {
	List<Author> entityList	= authorService.findAll();
	List<AuthorDto> dtoList    = authorMapper.maptoList(entityList);
	//List<AuthorDto> dtoList= bookAuthorMapper.convertAuthorEntityListToDtoList(entityList);
		return ResponseEntity.ok(dtoList);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid AuthorDto authorDto) {
		Author entity = authorMapper.unmap(authorDto);
		Author entityauthor = authorService.insert(entity);
		AuthorDto dto = authorMapper.map(entityauthor);
		//AuthorDto dto= bookAuthorMapper.authorEntityToDto(entityauthor);
		return ResponseEntity.ok(dto);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid AuthorDto authorDto) {
		Author entity = authorMapper.unmap(authorDto);
		Author entityauthor =authorService.update(entity);
		AuthorDto dto= authorMapper.map(entityauthor);
//		AuthorDto dto= bookAuthorMapper.authorEntityToDto(entityauthor);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		authorService.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/spec")
	public ResponseEntity<?> findByAuthorSpec(  @RequestBody AuthorSearch search) {
		return ResponseEntity.ok(authorService.findByAuthorSpec(search));

	}
	@GetMapping("/email/{email}")
	public ResponseEntity<AuthorDto> findByEmail( @PathVariable String email) {
	Author entity=authorService.findByEmail(email).get();
		AuthorDto dto= authorMapper.map(entity);
		return ResponseEntity.ok(dto);
	}

}
