package com.librarymanagement.library.ms.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.library.ms.model.AuthorRequest;
import com.librarymanagement.library.ms.model.AuthorResponse;
import com.librarymanagement.library.ms.service.IAuthorService;
import com.librarymanagement.library.ms.shared.AuthorDto;

@RestController
@RequestMapping("authors")
public class AuthorController {

	IAuthorService iAuthorService;
	ModelMapper modelMapper;

	@Autowired
	public AuthorController(IAuthorService iAuthorService, ModelMapper modelMapper) {
		this.iAuthorService = iAuthorService;
		this.modelMapper = modelMapper;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@GetMapping("/author/checkStatus")
	public HttpStatus checkStatus() {
		return HttpStatus.OK;
	}

	@PostMapping
	public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
		AuthorDto authorDetails = modelMapper.map(authorRequest, AuthorDto.class);
		AuthorDto createdAuthor = iAuthorService.createAuthor(authorDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(createdAuthor, AuthorResponse.class));
	}

}
