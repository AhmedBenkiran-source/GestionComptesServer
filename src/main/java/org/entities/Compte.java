package org.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Compte")
public class Compte {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	private Long user_id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "mail")
	private String mail;

	@Column(name = "smtpServer")
	private String smtpServer;

	@Column(name = "smtpPort")
	private Integer smtpPort;

	@Column(name = "imapServer")
	private String imapServer;

	@Column(name = "mtp")
	private String mtp;

	public Compte() {
	}

	public Compte(String name, String mail, String smtpServer, Integer smtpPort, String imapServer, String mtp) {
		super();
		this.name = name;
		this.mail = mail;
		this.smtpServer = smtpServer;
		this.smtpPort = smtpPort;
		this.imapServer = imapServer;
		this.mtp = mtp;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getMtp() {
		return mtp;
	}

	public void setMtp(String mtp) {
		this.mtp = mtp;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getImapServer() {
		return imapServer;
	}

	public void setImapServer(String imapServer) {
		this.imapServer = imapServer;
	}

}
