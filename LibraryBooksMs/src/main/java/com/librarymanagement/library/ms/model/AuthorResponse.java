package com.librarymanagement.library.ms.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorResponse {

	private String authorId;
	private String authorName;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
}
