package com.oscar.authorms.mapper;

import com.oscar.authorms.dto.AuthorCreateDTO;
import com.oscar.authorms.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorCreateMapper {

    AuthorCreateDTO toDto(Author author);

    Author toEntity(AuthorCreateDTO authorCreateDTO);

}
