package org.repositories;

import org.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Long> {
	 public Contact findByMail(String mail);

}
