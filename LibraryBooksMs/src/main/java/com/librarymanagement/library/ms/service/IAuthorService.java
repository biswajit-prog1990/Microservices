package com.librarymanagement.library.ms.service;

import com.librarymanagement.library.ms.shared.AuthorDto;

public interface IAuthorService {
	AuthorDto createAuthor(AuthorDto authorDetails);
}
