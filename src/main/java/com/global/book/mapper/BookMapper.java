package com.global.book.mapper;

import com.global.book.dto.AuthorDto;
import com.global.book.dto.BookDto;
import com.global.book.entity.Author;
import com.global.book.entity.Book;
import org.mapstruct.*;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class}, imports = {LocaleContextHolder.class})
public interface BookMapper {
    //map from book entity to dto
    @Mapping(source = "name", target = "fullName")
//    @Mapping(source = "author.name",target = "authorName")
//    @Mapping(source = "author.ipAdress", target = "authorIpAdress")
//    @Mapping(source = "author.email", target = "authorEmail")
    BookDto map(Book entity);


    @BeforeMapping
    default void mapName(Author entity, @MappingTarget AuthorDto dto) {
    }


    //map from  dto to book entity
    @Mapping(source = "fullName", target = "name")
    Book unmap(BookDto dto);

    //map from list<entity> to list<dto>
    @Mapping(source = "name", target = "fullName")
    List<BookDto> maptoList(List<Book> entityList);

    //map from list<dto> to list<entity>
    @Mapping(source = "fullName", target = "name")
    List<Book> unmaptoList(List<BookDto> dtoList);
}

//    @BeforeMapping
//    default void mapName(Author entity ,@MappingTarget AuthorDto dto){}


//    @Mapping(target = "author.ipAdress", defaultValue = "192.168.1.1")
//    @Mapping(target = "fullName", expression = "java(mapFullName(entity))")
//    BookDto map(Book entity);
//
//    default String mapFullName(Book entity) {
//        return getFullNameFromLocaleContext().getLanguage().equals("ar")
//                ? entity.getName()  // Use entity.getName() since fullName doesn't exist
//                : entity.getName(); // Same logic for now; adjust as needed
//    }
//
//    default Locale getFullNameFromLocaleContext() {
//        return LocaleContextHolder.getLocale();
//    }

// @Mapping(target = "fullName", expression = "java(LocaleContextHolder.getLocale().getLanguage().equals(\"ar\") ? entity.getName() : entity.getName())")
