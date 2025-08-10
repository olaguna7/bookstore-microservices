package com.oscar.authorms.mapper;

import com.oscar.authorms.dto.AuthorDTO;
import com.oscar.authorms.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
    AuthorDTO toDto(Author author);

    List<AuthorDTO> toDtoList(List<Author> authors);

}
