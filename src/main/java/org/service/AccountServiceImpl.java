package org.service;

import org.repositories.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepository appUserRepository;

	@Autowired
	private RoleRepository appRoleRepository;



	@Override
	public AppUser saveUser(String username, String password) throws NoSuchAlgorithmException {

		AppUser user = appUserRepository.findByUsername(username);
		if (user != null)
			throw new RuntimeException("User Already Exists");

		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		// appUser.setPassword(bCryptPasswordEncoder.encode(password));
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
		appUser.setPassword(myHash);
		appUser.setActived(true);

		appUserRepository.save(appUser);

		addRoleToUser(username, "USER");
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		

		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRolename(rolename);
		appUser.getRoles().add(appRole);

	}
}
