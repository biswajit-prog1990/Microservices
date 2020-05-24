package com.librarymanagement.library.ms.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class BookEntity implements Serializable {
	private static final long serialVersionUID = -9123626716693766066L;
	@Id
	@Column(name = "book_id")
	private String bookBarcode;
	@Column(nullable = false, unique = true)
	private String bookName;
	@Column(nullable = false)
	private int bookPrice;
	@Column
	private String bookMsdn;
	@Column(columnDefinition = "varchar(255) default 'admin'")
	private String createdBy;
	@Column
	@CreationTimestamp
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(columnDefinition = "varchar(255) default 'admin'")
	private String updatedBy;
	@Column
	@UpdateTimestamp
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private AuthorEntity author;
}
