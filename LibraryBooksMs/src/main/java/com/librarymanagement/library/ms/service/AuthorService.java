package com.librarymanagement.library.ms.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.ms.data.AuthorEntity;
import com.librarymanagement.library.ms.data.IAuthorRepository;
import com.librarymanagement.library.ms.shared.AuthorDto;
import com.librarymanagement.library.ms.shared.Util;

@Service
public class AuthorService implements IAuthorService {

	IAuthorRepository iAuthorRepository;
	Util util;

	@Autowired
	public AuthorService(IAuthorRepository iAuthorRepository, Util util) {
		this.iAuthorRepository = iAuthorRepository;
		this.util = util;
	}

	@Override
	public AuthorDto createAuthor(AuthorDto authorDetails) {
		authorDetails.setAuthorId(util.getRandomId());
		AuthorEntity authorEntity = new ModelMapper().map(authorDetails, AuthorEntity.class);
		iAuthorRepository.save(authorEntity);
		return new ModelMapper().map(authorEntity, AuthorDto.class);
	}

}
