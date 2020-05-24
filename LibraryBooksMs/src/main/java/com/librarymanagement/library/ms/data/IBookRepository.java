package com.librarymanagement.library.ms.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, String> {
	Page<BookEntity> findByAuthorAuthorId(String authorId, Pageable pageable);
}
