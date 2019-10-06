package org.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Contact")
public class Contact {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//@ManyToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "contacts")
	
	//private Set<Carnet> carnets = new HashSet<>();
	/*@OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)

	private Set<Carnet_Contact> carnet_contact = new HashSet<>();
*/
	@Transient
	private Long idcarnet;

	@Column(name = "mail")
	private String mail;

	public Contact() {

	}
/*
	public Set<Carnet> getCarnets() {
		return carnets;
	}

	public void setCarnets(Set<Carnet> carnets) {
		this.carnets = carnets;
	}
*/
	public Long getIdcarnet() {
		return idcarnet;
	}

	public void setIdcarnet(Long idcarnet) {
		this.idcarnet = idcarnet;
	}

	public Contact(Long idcarnet, String mail) {
		super();
		this.idcarnet = idcarnet;
		this.mail = mail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
