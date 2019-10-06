package org.service;

import org.entities.Mail;

public interface EmailService {
	public void sendmail(Mail mail);
	public void receivemail(Long id) ;
}
