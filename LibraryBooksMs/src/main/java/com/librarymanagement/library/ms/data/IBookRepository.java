package com.librarymanagement.library.ms.data;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<BookEntity, String> {
	Page<BookEntity> findByAuthorId(String authorId, Pageable pageable);
}
