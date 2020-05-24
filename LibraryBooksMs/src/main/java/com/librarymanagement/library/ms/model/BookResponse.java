package com.librarymanagement.library.ms.model;

import java.util.Date;

import lombok.Getter;

public class BookResponse {

	@Getter
	private String bookBarcode;
	@Getter
	private String bookName;
	@Getter
	private String bookMsdn;
	@Getter
	private int bookPrice;
	@Getter
	private String createdBy;
	@Getter
	private Date createdDate;
	@Getter
	private String updatedBy;
	@Getter
	private Date updatedDate;
}
