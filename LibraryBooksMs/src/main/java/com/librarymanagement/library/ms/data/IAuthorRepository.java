package com.librarymanagement.library.ms.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<AuthorEntity, String> {

}
