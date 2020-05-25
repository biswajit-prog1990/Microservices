package com.librarymanagement.library.ms.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping
	public ResponseEntity<?> getAllBooks() {
		List<BookDto> bookDetails = iBookService.getAllBooks();
		List<BookResponse> bookResponse = modelMapper.map(bookDetails, new TypeToken<List<BookResponse>>() {
		}.getType());
		return new ResponseEntity<>(bookResponse, HttpStatus.OK);
	}

	@GetMapping("/book/{bookBarcode}")
	public ResponseEntity<?> getBookByBarcode(@PathVariable("bookBarcode") String bookBarcode) {
		Optional<BookDto> bookDetails = iBookService.getBookByBarcode(bookBarcode);
		Optional<BookResponse> bookResponse = modelMapper.map(bookDetails,
				new TypeToken<Optional<BookResponse>>() {
				}.getType());
		return new ResponseEntity<>(bookResponse, HttpStatus.OK);
	}

	@GetMapping("/book/author/{authorId}")
	public Page<BookResponse> getBooksByAuthorId(@PathVariable("authorId") String authorId, Pageable pageable) {
		Page<BookDto> bookDetail = iBookService.getBooksByAuthor(authorId, pageable);
		return bookDetail.map(new Function<BookDto, BookResponse>() {

			@Override
			public BookResponse apply(BookDto t) {
				return modelMapper.map(t, BookResponse.class);
			}

		});
	}

	@PostMapping
	public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
		BookDto bookDetails = modelMapper.map(bookRequest, BookDto.class);
		BookDto createdBooks = iBookService.createBooks(bookDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(createdBooks, BookResponse.class));
	}
	
	@PutMapping("/book/{bookBarcode}")
	public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookRequest bookRequest,
			@PathVariable("bookBarcode") String bookBarcode) {
		BookDto bookDetails = modelMapper.map(bookRequest, BookDto.class);
		BookDto updatedBook = iBookService.updateBook(bookDetails, bookBarcode);
		if(updatedBook == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(updatedBook, BookResponse.class));
	}
	
	@DeleteMapping("/book/{bookBarcode}")
	public ResponseEntity<?> deleteBook(@PathVariable("bookBarcode") String bookBarcode) {
		Optional<BookDto> deletedBook = iBookService.deleteBook(bookBarcode);
		if(deletedBook == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(deletedBook, 
				new TypeToken<Optional<BookResponse>>() {}.getType()));
	}
}
