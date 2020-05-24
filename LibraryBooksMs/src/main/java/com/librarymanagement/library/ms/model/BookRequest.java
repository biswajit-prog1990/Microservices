package com.librarymanagement.library.ms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.librarymanagement.library.ms.data.AuthorEntity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class BookRequest {

	@NotNull(message = "Book name cannot be null")
	@Size(min = 5, max = 75)
	private String bookName;
	@NotNull(message = "Book msdn cannot be null")
	@Size(min = 5, max = 100)
	private String bookMsdn;
	@NotNull(message = "Book price cannot be null")
	private int bookPrice;
	@Size(min = 5, max = 50)
	private String createdBy;
	@Size(min = 5, max = 50)
	private String updatedBy;
	@Getter
	@Setter
	private AuthorEntity author;
}
