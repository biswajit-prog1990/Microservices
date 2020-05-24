package com.librarymanagement.library.ms.controller;

import java.util.function.Function;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.library.ms.model.BookRequest;
import com.librarymanagement.library.ms.model.BookResponse;
import com.librarymanagement.library.ms.service.IBookService;
import com.librarymanagement.library.ms.shared.BookDto;

@RestController
@RequestMapping("books")
public class BookController {

	IBookService iBookService;
	ModelMapper modelMapper;
	
	@Autowired
	public BookController(IBookService iBookService, ModelMapper modelMapper) {
		this.iBookService = iBookService;
		this.modelMapper = modelMapper;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@GetMapping("/book/status")
	public HttpStatus getStatus() {
		return HttpStatus.OK;
	}

	@GetMapping("/book/{authId}/books")
	public Page<BookResponse> getBooksByAuthorId(@PathVariable("authId") String authorId, Pageable pageable) {
		Page<BookDto> bookDetail = iBookService.getBooksByAuthor(authorId, pageable);
		return bookDetail.map(new Function<BookDto, BookResponse>() {

			@Override
			public BookResponse apply(BookDto t) {
				return modelMapper.map(t, BookResponse.class);
			}

		});
	}

	@PostMapping
	public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookDetails) {
		BookDto bookDto = modelMapper.map(bookDetails, BookDto.class);
		BookDto createdBooks = iBookService.createBooks(bookDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(createdBooks, BookResponse.class));
	}
}
