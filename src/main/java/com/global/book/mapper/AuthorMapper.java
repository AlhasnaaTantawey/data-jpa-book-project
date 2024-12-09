package com.global.book.mapper;

import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    //map from entity to dto
  @Mapping(source = "name",target = "fullName")
    AuthorDto map(Author entity);

    //map from dto to entity
    @Mapping(source = "fullName",target = "name")
    Author unmap(AuthorDto dto);

    //map from list<entity> to list<dto>
    @Mapping(source = "name",target = "fullName")
    List<AuthorDto> maptoList(List<Author> entityList);

    //map from list<dto> to list<entity>
    @Mapping(source = "fullName",target = "name")
    List<Author> unmaptoList(List<AuthorDto> dtoList);
}
