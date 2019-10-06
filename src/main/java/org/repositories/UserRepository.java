package org.repositories;

import java.io.Serializable;

import org.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>,Serializable {
	 public AppUser findByUsername(String username);
}
