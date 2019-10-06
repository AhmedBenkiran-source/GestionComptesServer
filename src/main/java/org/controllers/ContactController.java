package org.controllers;

import java.util.List;


import org.entities.Contact;
import org.repositories.ContactRepository;
import org.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")

public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;

	
	
	@Autowired
	private GroupeService groupeService;
	
	@GetMapping("/contacts")
	public  List<Contact> getContacts() {
		return contactRepository.findAll();
	}
	@DeleteMapping("/contact/{id}")
	public boolean deleteContact(@PathVariable Long id) {
		groupeService.getContactByIdCarnet(id);
		return true;
	}
	@PutMapping("/contact")
	public Contact updateContact(@RequestBody Contact contact) {
		return contactRepository.save(contact);
	}
	
	@PostMapping("/contact")
	public Contact createContact(@RequestBody Contact contact ) {	
		return groupeService.saveCarnet(contact);
	}
	
}
