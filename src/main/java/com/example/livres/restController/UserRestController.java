package com.example.livres.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.livres.repos.UserRepository;
import com.example.livres.service.UserService;
import com.example.livres.entities.Role;
import com.example.livres.entities.User;
@RestController
@RequestMapping("/Userapi")
public class UserRestController {

	
	
	@Autowired
	UserRepository userRep;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/users/all",method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRep.findAll();
	 }
	
	

	@RequestMapping(value = "/users/{id}" ,method = RequestMethod.GET)
	public Optional<User> getUserById(@PathVariable("id") Long id) {
	return userRep.findById(id);
	 }
	
	@RequestMapping(value = "/users/getByusername/{username}" ,method = RequestMethod.GET)
	public User getUserByUsername(@PathVariable("username") String username) {
	return userService.findUserByUsername(username);
	 }
	
	
	
	
	@RequestMapping(value = "/users",method = RequestMethod.POST)
	public User createUser(@RequestBody User u) {
		
		
		
		//ajouter le user
			User newU  = userService.saveUser(new User(null,u.getUsername(),u.getPassword(),true,null));
			
			//ajouter les rôles au user	
		
		
			if (u.getRoles() != null) {
		        for (Role r : u.getRoles()) {
		            newU = userService.addRoleToUser(newU.getUsername(), r.getRole());
		        }
		    }
		
		
		
		return newU;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User u) {

		//ajouter le user
		User newU  = userService.updateUser(new User(u.getUser_id(),u.getUsername(),u.getPassword(),true,null));
		
		//ajouter les rôles au user	
	
	     newU.setRoles(null);
		if (u.getRoles() != null) {
			
	        for (Role r : u.getRoles()) {
	            newU = userService.addRoleToUser(newU.getUsername(), r.getRole());
	        }
	    }
	
	
	
	return newU;
		
		
	}
	
	@RequestMapping(value="/users/{id}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long id)
	{
		//System.out.println("delete");
		userService.deleteUserById(id);
	}
	
	
	@RequestMapping(value = "/users/register",method = RequestMethod.POST)
	public User registerUser(@RequestBody User u) {
		
		System.out.println("register");
		
		//ajouter le user
			User newU  = userService.saveUser(new User(null,u.getUsername(),u.getPassword(),true,null));
			
			//ajouter les rôles au user	
		
		
			//if (u.getRoles() != null) {
		      //  for (Role r : u.getRoles()) {
		            newU = userService.addRoleToUser(newU.getUsername(), "USER");
		      //  }
		   // }
		
		
		
		return newU;
	}
	
	
	@RequestMapping(value = "/users/changePassword/{id}",method = RequestMethod.PUT)
	public User ChangePassword(@RequestParam("oldPass") String oldPass,@RequestParam("newPass") 
	String newPass,@PathVariable("id") Long id) {
		
		System.out.println("old = "+oldPass + " new = "+newPass);
		
			return  userService.ChangePassword(oldPass,newPass,id);
			
	
		
		
	
		
		
		
	}
	
	
	
	
	
	
}
