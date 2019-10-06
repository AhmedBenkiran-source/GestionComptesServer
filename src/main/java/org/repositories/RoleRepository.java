package org.repositories;

import java.io.Serializable;

import org.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long>,Serializable {
	 public AppRole findByRolename(String rolename);

}
