package org.service;

import org.entities.Contact;

import java.util.List;
import java.util.Set;

import org.controllers.CarnetController;
import org.entities.Carnet;
import org.repositories.ContactRepository;
import org.repositories.CarnetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private CarnetRepository groupRepository;
	@Autowired
	private CarnetRepository carnetRepository;
	
	@Autowired
	private CarnetController carnetController;

	@Override
	public Contact saveCarnet(Contact contact) {
		

		contactRepository.save(contact);
		addContactToGroup(contact.getMail(), contact.getIdcarnet());
		return contact;

	}

	@Override
	public void addContactToGroup(String mail, Long id) {
		Contact contact = contactRepository.findByMail(mail);
		Carnet groupe = groupRepository.findOne(id);
		groupe.getContacts().add(contact);
	}

	@Override
	public Contact loadContactByUsername(String username) {
		return contactRepository.findByMail(username);

	}

	@Override
	public boolean getContactByIdCarnet(Long id) {
		
		List<Carnet> ListeCarnet = carnetRepository.findAll();
		for (Carnet carnet : ListeCarnet) {
			Set<Contact> ListeContact = carnet.getContacts();
			// ListeContact.remove();
			for (Contact contact : ListeContact) {
				if (contact.getId() == id) {
					System.out.println("----------ture-------------");
					System.out.println(contact.getMail());
					ListeContact.remove(contact);
					carnetController.updateCarnet(carnet);
					return true;
				}
				
			}

		}
		
		 
		return false;
	}

}
