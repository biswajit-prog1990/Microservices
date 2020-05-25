package com.librarymanagement.library.ms.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.librarymanagement.library.ms.shared.BookDto;

public interface IBookService {
	BookDto createBooks(BookDto bookDetails);
	Page<BookDto> getBooksByAuthor(String authorId, Pageable pageable);
	Optional<BookDto> getBookByBarcode(String bookBarcode);
	List<BookDto> getAllBooks();
	BookDto updateBook(BookDto bookDetails, String bookBarcode);
	Optional<BookDto> deleteBook(String bookBarcode);
}
