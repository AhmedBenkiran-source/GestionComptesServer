package org.controllers;

import java.util.List;
import java.util.Set;
import java.util.Date;

import org.entities.Carnet;
import org.entities.Contact;
import org.entities.Mail;
import org.repositories.CarnetRepository;
import org.repositories.MailRepository;
import org.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class EmailController {

	@Autowired
	private MailRepository mailRepository;

	@Autowired
	private EmailService mailService;

	@Autowired
	private CarnetRepository carnetRepository;

	@GetMapping("/mails-send/{id}")
	public List<Mail> getMailsSend(@PathVariable Long id) {
		System.out.println("aaaaaaaaaaaaa "+id);
		mailService.receivemail(id);
		return mailRepository.findAll();
	}
	@GetMapping("/mails")
	public List<Mail> getMails() {
		return mailRepository.findAll();
	}
	
	@PutMapping("/mail-lu")
	public Mail updateMail(@RequestBody  Mail mail) {
		mail.setLu(true);
		return mailRepository.save(mail);
	}
	@PostMapping("/send-mail")
	public void createMail(@RequestBody Mail mail) throws Exception {
		List<Carnet> ListeCarnet = carnetRepository.findAll();
		int t = 0;
		for (Carnet carnet : ListeCarnet) {
			if (carnet.getName().equals(mail.getMail_recepteur()) ) {
				Set<Contact> ListeContact = carnet.getContacts();
				for (Contact contact : ListeContact) {
					Mail ma = new Mail();
					ma.setType("send");
					ma.setDate(new Date());
					ma.setContents(mail.getContents());
					ma.setObject(mail.getObject());
					ma.setMail_emetteur(mail.getMail_emetteur());
					ma.setMail_recepteur(contact.getMail());
					mailService.sendmail(ma);
					mailRepository.save(ma);
				//	mailService.receivemail();
				}
				t = -1 ;
			}
			

		}
		if(t == 0) {
			mail.setType("send");
			mail.setDate(new Date());
			mailService.sendmail(mail);
			mailRepository.save(mail);
		//	mailService.receivemail();
		}
		// receivemail(mail);
	}

	@PostMapping("/receiv-mail")
	public void SaveMail(@RequestBody Mail mail) throws Exception {
		// sendmail(mail);
		// mail.setType("send");
		// mailRepository.save(mail);
	//	mailService.receivemail(mail);
	}

}
