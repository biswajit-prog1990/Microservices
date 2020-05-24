package com.librarymanagement.library.ms.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import java.util.function.Function;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.ms.data.BookEntity;
import com.librarymanagement.library.ms.data.IBookRepository;
import com.librarymanagement.library.ms.shared.BookDto;
import com.librarymanagement.library.ms.shared.Util;

@Service
public class BookService implements IBookService {

	IBookRepository iBookRepository;
	Util util;

	@Autowired
	public BookService(IBookRepository iBookRepository, Util util) {
		this.iBookRepository = iBookRepository;
		this.util = util;
	}

	@Override
	public BookDto createBooks(BookDto bookDetails) {
		bookDetails.setBookBarcode(util.getRandomId());
		BookEntity bookEntity = new ModelMapper().map(bookDetails, BookEntity.class);
		iBookRepository.save(bookEntity);
		return new ModelMapper().map(bookEntity, BookDto.class);
	}

	@Override
	public Page<BookDto> getBooksByAuthor(String authorId, Pageable pageable) {
		Page<BookEntity> bookEntity = iBookRepository.findByAuthorId(authorId, pageable);
		return bookEntity.map(new Function<BookEntity, BookDto>() {

			@Override
			public BookDto apply(BookEntity t) {
				return new ModelMapper().map(t, BookDto.class);
			}

		});
	}

}
