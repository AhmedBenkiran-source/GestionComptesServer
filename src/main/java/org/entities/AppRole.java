package org.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class AppRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String rolename;

	@ManyToMany(mappedBy = "roles")
	private Set<AppUser> users;

	public AppRole() {
	}

	public AppRole(String rolename) {
		super();

		this.rolename = rolename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Set<AppUser> getUsers() { return users; }
	 * 
	 * public void setUsers(Set<AppUser> users) { this.users = users; }
	 */
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
