package com.oscar.authorms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO {
    String name;
    Date birthday;
    List<BookSummaryDTO> books;

}
