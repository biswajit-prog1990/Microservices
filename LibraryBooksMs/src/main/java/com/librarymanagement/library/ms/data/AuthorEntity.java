package com.librarymanagement.library.ms.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthorEntity implements Serializable {

	private static final long serialVersionUID = 4211386623311420979L;
	@Id
	@SequenceGenerator(name = "author_seq", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "author_seq")
	@Column(name = "author_id")
	private String authorId;
	@Column(nullable = false, unique = true)
	private String authorName;
	@Column(columnDefinition = "varchar(300) default admin")
	private String createdBy;
	@Column
	@CreationTimestamp
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(columnDefinition = "varchar(300) default admin")
	private String updatedBy;
	@Column
	@UpdateTimestamp
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedDate;
}
