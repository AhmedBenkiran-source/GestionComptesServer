package org.repositories;

import org.entities.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarnetRepository  extends JpaRepository<Carnet, Long> {
	 public Carnet findByName(String name);
	// public boolean DeleteContactCarnet(Long id);

}
