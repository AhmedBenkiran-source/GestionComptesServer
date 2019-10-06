package org.service;

import java.security.NoSuchAlgorithmException;

import org.entities.AppRole;
import org.entities.AppUser;

public interface AccountService {
	
    public AppUser saveUser( String username,String password) throws NoSuchAlgorithmException;
    public AppRole saveRole(AppRole role);
    public AppUser loadUserByUsername(String username);
    public  void addRoleToUser(String username, String rolename);
}
