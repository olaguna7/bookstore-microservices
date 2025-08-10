package com.oscar.authorms.service;

import com.oscar.authorms.dto.AuthorCreateDTO;
import com.oscar.authorms.dto.AuthorDTO;
import com.oscar.authorms.entity.Author;
import com.oscar.authorms.mapper.AuthorCreateMapper;
import com.oscar.authorms.mapper.AuthorMapper;
import com.oscar.authorms.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorCreateMapper authorCreateMapper;

    public Page<AuthorDTO> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable).map(authorMapper::toDto);
    }

    public AuthorDTO findById(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        return authorMapper.toDto(author);
    }

    public Page<AuthorDTO> findByNameContaining(String name, Pageable pageable) {
        return authorRepository.findAllByNameContaining(name, pageable).map(authorMapper::toDto);
    }

    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO) {
        Author author = authorCreateMapper.toEntity(authorCreateDTO);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public AuthorDTO updateAuthor(Long authorId, AuthorCreateDTO authorDTO) {
        Author author = authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        author.setName(authorDTO.getName());
        author.setBirthday(authorDTO.getBirthday());
        author.setBooksIds(authorDTO.getBooksIds());
        return authorMapper.toDto(authorRepository.save(author));
    }

    public void deleteAuthor(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new EntityNotFoundException();
        }

        authorRepository.deleteById(authorId);
    }

}
