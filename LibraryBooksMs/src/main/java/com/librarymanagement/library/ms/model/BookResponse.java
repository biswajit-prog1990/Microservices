package com.librarymanagement.library.ms.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponse {

	private String bookBarcode;
	private String bookName;
	private String bookMsdn;
	private int bookPrice;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
}
