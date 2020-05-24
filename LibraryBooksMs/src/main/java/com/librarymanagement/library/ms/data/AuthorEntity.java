package com.librarymanagement.library.ms.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
public class AuthorEntity implements Serializable {

	private static final long serialVersionUID = 4211386623311420979L;
	@Id
	@Column(name = "author_id")
	private String authorId;
	@Column(nullable = false, unique = true)
	private String authorName;
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
}
