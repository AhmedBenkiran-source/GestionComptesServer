package org.service;

import org.entities.Contact;

public interface GroupeService {
	public Contact saveCarnet(Contact contact);

	public void addContactToGroup(String groupe, Long contact);

	public Contact loadContactByUsername(String username);

	public boolean getContactByIdCarnet(Long id);
}
