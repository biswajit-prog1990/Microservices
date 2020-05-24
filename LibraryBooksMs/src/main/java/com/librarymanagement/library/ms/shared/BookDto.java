package com.librarymanagement.library.ms.shared;

import java.io.Serializable;
import java.util.Date;

import com.librarymanagement.library.ms.data.AuthorEntity;

public class BookDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bookBarcode;
	private String bookName;
	private String bookMsdn;
	private int bookPrice;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private AuthorEntity author;

	public String getBookBarcode() {
		return bookBarcode;
	}

	public void setBookBarcode(String bookBarcode) {
		this.bookBarcode = bookBarcode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookMsdn() {
		return bookMsdn;
	}

	public void setBookMsdn(String bookMsdn) {
		this.bookMsdn = bookMsdn;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}
}
