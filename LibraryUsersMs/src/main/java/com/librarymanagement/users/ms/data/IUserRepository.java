package com.librarymanagement.users.ms.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
