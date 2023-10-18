package com.example.livres.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.livres.entities.Role;
import com.example.livres.entities.User;
import com.example.livres.repos.RoleRepository;
import com.example.livres.repos.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService{
@Autowired
UserRepository userRep;
@Autowired
RoleRepository roleRep;

@Autowired
BCryptPasswordEncoder bCryptPasswordEncoder;

@Override
public User saveUser(User user) {
	
	// Check if the username already exists
    User existingUser = userRep.findByUsername(user.getUsername());
    if (existingUser != null) {
        throw new IllegalArgumentException("Username '" + user.getUsername() + "' already exists.");
    }
	
user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));





return userRep.save(user);
}
@Override
public User addRoleToUser(String username, String rolename) {
User usr = userRep.findByUsername(username);
Role r = roleRep.findByRole(rolename);


System.out.println(r);
if (usr.getRoles() == null) {
    usr.setRoles(new ArrayList<>()); // Initialize the roles list
}
if (!usr.getRoles().contains(r)) {
	usr.getRoles().add(r);
}





return usr;
}
@Override
public Role addRole(Role role) {
return roleRep.save(role);
}
@Override
public User findUserByUsername(String username) {
return userRep.findByUsername(username);
}
@Override
public User updateUser(User user) {
	  User existingUser = userRep.getById(user.getUser_id());
	  
	  if (existingUser == null) {
	        throw new IllegalArgumentException("user '" + user.getUsername() + "' does not exist.");
	    }
	
	
	    
	  if (userRep.findByUsername(user.getUsername()) != null && !user.getUsername().equals(existingUser.getUsername()) ) {
	        throw new IllegalArgumentException("Username '" + user.getUsername() + "' already exists.");
	    }
	    
	   existingUser.setUsername(user.getUsername());

return userRep.save(existingUser);
}
@Override
public void deleteUserById(Long id) {
	User user = userRep.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist."));

    // Remove the association with roles
    user.getRoles().clear();
	userRep.deleteById(id);
	
}
@Override
public User getUserById(Long id) {
	return userRep.getById(id);
}
@Override
public User ChangePassword(String oldPass ,String newPass, Long id) {
	User existingUser = userRep.getById(id);
	  
	  if (existingUser == null) {
	        throw new IllegalArgumentException("user '" + existingUser.getUsername() + "' does not exist.");
	    }
	  
	  // Verify the old password
	
			if (!bCryptPasswordEncoder.matches(oldPass, existingUser.getPassword())) {
			  throw new IllegalArgumentException("Incorrect old password.");
			}
	  
	  
	  
	  existingUser.setPassword(bCryptPasswordEncoder.encode(newPass));
	  
	  return userRep.save(existingUser);
	
	
}






}