package org.repositories;

import org.entities.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository  extends JpaRepository<Mail, Long>{

}
