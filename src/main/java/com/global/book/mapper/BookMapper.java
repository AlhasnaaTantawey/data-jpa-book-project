package com.global.book.mapper;

import com.global.book.dto.AuthorDto;
import com.global.book.dto.BookDto;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {

   private final   ModelMapper modelMapper ;

    public Author authorDtoToEntity(AuthorDto dto){
        Author entity=new Author();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
      //  entity.setIpAdress(dto.getIpAdress());
       // entity.setImagePath(dto.getImagePath());
       // entity.setBooks(dto.getBooks());
        return entity;
    }

//    public Author AuthorDtoToEntity(AuthorDto dto){
//        return  modelMapper.map(dto ,Author.class);
//    }

    public AuthorDto authorEntityToDto(Author entity){
        AuthorDto dto=new AuthorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
       // dto.setIpAdress(entity.getIpAdress());
        //dto.setImagePath(entity.getImagePath());
       // dto.setBooks(entity.getBooks());
        return dto;
    }

    public Book bookDtoToEntity(BookDto dto){
        Book entity=new Book();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
       // entity.setAuthor(dto.getAuthor());
        return entity;
    }

//    public Book BookDtoToEntity(BookDto dto){
//        return modelMapper.map(dto ,Book.class);
//    }

    public BookDto bookEntityToDto(Book entity){
        BookDto dto=new BookDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
       // dto.setAuthor(entity.getAuthor());
        return dto;
    }

    public List<Book>convertBookDtoListToEntityList(List<BookDto> dtoList){
        ModelMapper modelMapper=new ModelMapper();
       return dtoList.stream()
               .map( bookDto -> modelMapper.map(bookDto , Book.class))
               .collect(Collectors.toList());
    }

    public List<BookDto>convertBookEntityListToDtoList(List<Book> entityList){
        ModelMapper modelMapper=new ModelMapper();
        return entityList.stream()
                .map( entityBook -> modelMapper.map(entityBook , BookDto.class))
                .toList();
    }

    public List<AuthorDto>convertAuthorEntityListToDtoList(List<Author> entityList){
        ModelMapper modelMapper=new ModelMapper();
        return entityList.stream()
                .map( entityAuthor ->
                        modelMapper.map(entityAuthor , AuthorDto.class)
                )
                .toList();
    }

    public List<Author>convertAuthorDtoListToEntityList(List<AuthorDto> dtoList){
        ModelMapper modelMapper=new ModelMapper();
        return dtoList.stream()
                .map( dtoAuthor -> modelMapper.map(dtoAuthor , Author.class))
                .collect(Collectors.toList());
    }

}
