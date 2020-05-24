package com.librarymanagement.library.ms.service;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.library.ms.data.BookEntity;
import com.librarymanagement.library.ms.data.IBookRepository;
import com.librarymanagement.library.ms.shared.BookDto;
import com.librarymanagement.library.ms.shared.Util;

@Service
public class BookService implements IBookService {

	IBookRepository iBookRepository;
	Util util;
	ModelMapper modelMapper;

	@Autowired
	public BookService(IBookRepository iBookRepository, Util util, ModelMapper modelMapper) {
		this.iBookRepository = iBookRepository;
		this.util = util;
		this.modelMapper = modelMapper;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public BookDto createBooks(BookDto bookDetails) {
		bookDetails.setBookBarcode(util.getRandomId());
		BookEntity bookEntity = modelMapper.map(bookDetails, BookEntity.class);
		iBookRepository.save(bookEntity);
		return modelMapper.map(bookEntity, BookDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<BookDto> getBooksByAuthor(String authorId, Pageable pageable) {
		Page<BookEntity> bookEntity = iBookRepository.findByAuthorAuthorId(authorId, pageable);
		return bookEntity.map(new Function<BookEntity, BookDto>() {

			@Override
			public BookDto apply(BookEntity t) {
				return modelMapper.map(t, BookDto.class);
			}

		});
	}

}
