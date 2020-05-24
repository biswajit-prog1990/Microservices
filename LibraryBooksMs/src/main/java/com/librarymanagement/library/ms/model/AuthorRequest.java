package com.librarymanagement.library.ms.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorRequest {

	@NotNull(message = "Author Name cannot be null")
	@Size(min = 8, max = 50)
	private String authorName;
	@Size(min = 3, max = 50, message = "CreatedBy should be between 3 and 50 characters")
	private String createdBy;
	@Size(min = 3, max = 50, message = "CreatedBy should be between 3 and 50 characters")
	private String updatedBy;
}
