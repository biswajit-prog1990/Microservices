package com.librarymanagement.library.ms.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.librarymanagement.library.ms.shared.BookDto;

public interface IBookService {
	BookDto createBooks(BookDto bookDetails);

	Page<BookDto> getBooksByAuthor(String authorId, Pageable pageable);
}
