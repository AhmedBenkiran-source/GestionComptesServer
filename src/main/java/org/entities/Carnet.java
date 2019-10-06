package org.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Carnet")
public class Carnet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "date")
	private Date date;
	
	private Long user_id;
	
	//@ManyToMany(fetch =FetchType.LAZY)
	/*@JoinTable(joinColumns =  {@JoinColumn(name = "id_carnet")},
			inverseJoinColumns = { @JoinColumn(name = "id_contact") })
	*/
	@OneToMany
	//		private ArrayList<Contact> contacts = new ArrayList<>();

		private Set<Contact> contacts = new HashSet<Contact>();

	public Carnet(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}



	public Long getUser_id() {
		return user_id;
	}



	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}



	public Set<Contact> getContacts() {
		return contacts;
	}
   
	

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}



	public Carnet() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
