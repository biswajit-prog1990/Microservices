package com.librarymanagement.library.ms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping
	public ResponseEntity<?> getAllAuthors() {
		List<AuthorDto> authorDetails = iAuthorService.getAllAuthors();
		List<AuthorResponse> authorResponse = modelMapper.map(authorDetails, new TypeToken<List<AuthorResponse>>() {
		}.getType());
		return new ResponseEntity<>(authorResponse, HttpStatus.OK);
	}

	@GetMapping("/author/{authorId}")
	public ResponseEntity<?> getAuthorById(@PathVariable("authorId") String authorId) {
		Optional<AuthorDto> authorDetails = iAuthorService.getAuthorById(authorId);
		Optional<AuthorResponse> authorResponse = modelMapper.map(authorDetails,
				new TypeToken<Optional<AuthorResponse>>() {
				}.getType());
		return new ResponseEntity<>(authorResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
		AuthorDto authorDetails = modelMapper.map(authorRequest, AuthorDto.class);
		AuthorDto createdAuthor = iAuthorService.createAuthor(authorDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(createdAuthor, AuthorResponse.class));
	}

	@PutMapping("/author/{authorId}")
	public ResponseEntity<AuthorResponse> updateAuthor(@Valid @RequestBody AuthorRequest authorRequest,
			@PathVariable("authorId") String authorId) {
		AuthorDto authorDetails = modelMapper.map(authorRequest, AuthorDto.class);
		AuthorDto updatedAuthor = iAuthorService.updateAuthor(authorDetails, authorId);
		if(updatedAuthor == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(updatedAuthor, AuthorResponse.class));
	}
	
	@DeleteMapping("/author/{authorId}")
	public ResponseEntity<?> deleteAuthor(@PathVariable("authorId") String authorId) {
		Optional<AuthorDto> deletedAuthor = iAuthorService.deleteAuthor(authorId);
		if(deletedAuthor == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(deletedAuthor, 
				new TypeToken<Optional<AuthorResponse>>() {}.getType()));
	}

}
