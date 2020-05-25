package com.librarymanagement.library.ms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
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
	ModelMapper modelMapper;

	@Autowired
	public AuthorService(IAuthorRepository iAuthorRepository, Util util, ModelMapper modelMapper) {
		this.iAuthorRepository = iAuthorRepository;
		this.util = util;
		this.modelMapper = modelMapper;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}	

	@Override
	public AuthorDto createAuthor(AuthorDto authorDetails) {
		authorDetails.setAuthorId(util.getRandomId());
		AuthorEntity authorEntity = modelMapper.map(authorDetails, AuthorEntity.class);
		iAuthorRepository.save(authorEntity);
		return modelMapper.map(authorEntity, AuthorDto.class);
	}

	@Override
	public List<AuthorDto> getAllAuthors() {
		List<AuthorEntity> authorEntity = iAuthorRepository.findAll();
		List<AuthorDto> authorDto = modelMapper.map(authorEntity, new TypeToken<List<AuthorDto>>() {}.getType());
		return authorDto;
	}

	@Override
	public Optional<AuthorDto> getAuthorById(String authorId) {
		Optional<AuthorEntity> authorEntity = iAuthorRepository.findById(authorId);
		Optional<AuthorDto> authorDto = modelMapper.map(authorEntity, new TypeToken<Optional<AuthorDto>>() {}.getType());
		return authorDto;
	}

	@Override
	public AuthorDto updateAuthor(AuthorDto authorDetails, String authorId) {
		Optional<AuthorEntity> authorEntity = iAuthorRepository.findById(authorId);
		if(!authorEntity.isPresent())
			return new AuthorDto();
		authorDetails.setAuthorId(authorId);
		AuthorEntity updatedAuthor = modelMapper.map(authorDetails, AuthorEntity.class);
		iAuthorRepository.save(updatedAuthor);
		return modelMapper.map(updatedAuthor, AuthorDto.class);
	}

	@Override
	public Optional<AuthorDto> deleteAuthor(String authorId) {
		Optional<AuthorEntity> authorEntity = iAuthorRepository.findById(authorId);
		if(!authorEntity.isPresent())
			return null;
		iAuthorRepository.deleteById(authorId);
		return modelMapper.map(authorEntity, new TypeToken<Optional<AuthorDto>>() {}.getType());
	}

}
