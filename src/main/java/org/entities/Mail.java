package org.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Mail")
public class Mail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long user_id;
	@Column(name = "mail_recepteur")
	private String mail_recepteur;

	@Column(name = "mail_emetteur")
	private String mail_emetteur;

	@Column(name = "object")
	private String object;

	@Column(name = "contents")
	private String contents;

	@Column(name = "type")
	private String type;

	@Column(name = "date")
	private Date date;

	@Column(name = "Lu")
	private boolean lu;

	public boolean isLu() {
		return lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail_recepteur() {
		return mail_recepteur;
	}

	public void setMail_recepteur(String mail_recepteur) {
		this.mail_recepteur = mail_recepteur;
	}

	public String getMail_emetteur() {
		return mail_emetteur;
	}

	public void setMail_emetteur(String mail_emetteur) {
		this.mail_emetteur = mail_emetteur;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Mail() {
		super();
	}

}
