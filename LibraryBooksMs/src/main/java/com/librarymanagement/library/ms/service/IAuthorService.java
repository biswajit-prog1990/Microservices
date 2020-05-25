package com.librarymanagement.library.ms.service;

import java.util.List;
import java.util.Optional;

import com.librarymanagement.library.ms.shared.AuthorDto;

public interface IAuthorService {
	AuthorDto createAuthor(AuthorDto authorDetails);
	List<AuthorDto> getAllAuthors();
	Optional<AuthorDto> getAuthorById(String authorId);
	AuthorDto updateAuthor(AuthorDto authorDetails, String authorId);
	Optional<AuthorDto> deleteAuthor(String authorId);
}
