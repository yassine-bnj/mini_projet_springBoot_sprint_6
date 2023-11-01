package com.example.livres.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.livres.entities.Role;
import com.example.livres.entities.User;
import com.example.livres.repos.RoleRepository;
import com.example.livres.repos.UserRepository;
import com.example.livres.util.EmailSender;

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
	
   
    
  if(userRep.findByEmail(user.getEmail()).isPresent()
		  &&(userRep.findByEmail(user.getEmail()).get().isEmailConfirmed()
		  )){
  	  
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email existe déja");
    }
  else if(userRep.findByUsername(user.getUsername()).isPresent()
		&&(userRep.findByUsername(user.getUsername()).get().isEmailConfirmed())
		){
  	  System.out.println(userRep.findByUsername(user.getUsername()).get());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username existe déja");
    }
else if(userRep.findByEmail(user.getEmail()).isPresent()
		&&!(userRep.findByEmail(user.getEmail()).get().isEmailConfirmed())){
	User u =userRep.findByEmail(user.getEmail()).get();
	u.setCode(user.getCode());
	u.setEmail(user.getEmail());
	u.setEnabled(user.getEnabled());
	u.setUsername(user.getUsername());
	u.setEmailConfirmed(user.isEmailConfirmed());
	u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	Role r=roleRep.findByRole("USER");
    u=userRep.save(u);
	u = this.addRoleToUser(u.getUsername(), r.getRole());


	return u ;
}  
    
	
user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


User newU = userRep.save(user);
Role r=roleRep.findByRole("USER");

newU = this.addRoleToUser(newU.getUsername(), r.getRole());


return newU ;
}
@Override
public User addRoleToUser(String username, String rolename) {
User usr = userRep.findByUsername(username).get();
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
return userRep.findByUsername(username).get();
}
@Override
public User updateUser(User user) {
	/*  User existingUser = userRep.getById(user.getUser_id());
	  
	  if (existingUser == null) {
	        throw new IllegalArgumentException("user '" + user.getUsername() + "' does not exist.");
	    }
	
	
	    
	  if (userRep.findByUsername(user.getUsername()) != null && !user.getUsername().equals(existingUser.getUsername()) ) {
	        throw new IllegalArgumentException("Username '" + user.getUsername() + "' already exists.");
	    }
	    
	   existingUser.setUsername(user.getUsername());

return userRep.save(existingUser);*/
	
	//ajouter le user
			User newU  = userRep.save(new User(user.getUser_id(),user.getUsername()
					,user.getEmail(),user.getCode(),user.isEmailConfirmed(),user.getPassword(),user.getEnabled(),null));
			
			//ajouter les rôles au user	
		
		     newU.setRoles(null);
			if (user.getRoles() != null) {
				
		        for (Role r : user.getRoles()) {
		            newU = this.addRoleToUser(newU.getUsername(), r.getRole());
		        }
		    }
		
		
		
		return newU;
	
	
	
	
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
@Override
public User verifEmail(String email, String code) {
	
	
	
User u = 	userRep.findByEmail(email).get();



	if(u.getCode().equals(code)) {
		u.setCode("");
		u.setEmailConfirmed(true);
		u.setEnabled(true);
		u = userRep.save(u);
		
	}

	return u;
	

	
}
@Override
public User registerUser(User u) {
	System.out.println("register");
	 Random random = new Random();
    int code = 100000 + random.nextInt(900000); 
	//ajouter le user
	User newU  = this.saveUser(new User(null,u.getUsername(),u.getEmail(),String.valueOf(code),false,u.getPassword(),false,null));
		System.out.println(newU);
		
	            
	           
	
	  EmailSender es = new EmailSender();
	  
	  String email =""
	  		+ "\n"
	  		+ "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='table-layout:fixed;background-color:#f9f9f9' id='bodyTable'>\n"
	  		+ "	<tbody>\n"
	  		+ "		<tr>\n"
	  		+ "			<td style='padding-right:10px;padding-left:10px;' align='center' valign='top' id='bodyCell'>\n"
	  		+ "				<table border='0' cellpadding='0' cellspacing='0' width='100%' class='wrapperWebview' style='max-width:600px'>\n"
	  		+ "					<tbody>\n"
	  		+ "						<tr>\n"
	  		+ "							<td align='center' valign='top'>\n"
	  		+ "								<table border='0' cellpadding='0' cellspacing='0' width='100%'>\n"
	  		+ "									<tbody>\n"
	  		+ "									\n"
	  		+ "									</tbody>\n"
	  		+ "								</table>\n"
	  		+ "							</td>\n"
	  		+ "						</tr>\n"
	  		+ "					</tbody>\n"
	  		+ "				</table>\n"
	  		+ "				<table border='0' cellpadding='0' cellspacing='0' width='100%' class='wrapperBody' style='max-width:600px'>\n"
	  		+ "					<tbody>\n"
	  		+ "						<tr>\n"
	  		+ "							<td align='center' valign='top'>\n"
	  		+ "								<table border='0' cellpadding='0' cellspacing='0' width='100%' class='tableCard' style='background-color:#fff;border-color:#e5e5e5;border-style:solid;border-width:0 1px 1px 1px;'>\n"
	  		+ "									<tbody>\n"
	  		+ "										<tr>\n"
	  		+ "											<td style='background-color:#00d2f4;font-size:1px;line-height:3px' class='topBorder' height='3'>&nbsp;</td>\n"
	  		+ "										</tr>\n"
	  		+ "										\n"
	  		+ "										\n"
	  		+ "										<tr>\n"
	  		+ "											<td style='padding-bottom: 5px; padding-left: 20px; padding-right: 20px;' align='center' valign='top' class='mainTitle'>\n"
	  		+ "												<h2 class='text' style='color:#000;font-family:Poppins,Helvetica,Arial,sans-serif;font-size:28px;font-weight:500;font-style:normal;letter-spacing:normal;line-height:36px;text-transform:none;text-align:center;padding:0;margin:0'>Hi '"+ u.getUsername() +"'</h2>\n"
	  		+ "											</td>\n"
	  		+ "										</tr>\n"
	  		+ "										<tr>\n"
	  		+ "											<td style='padding-bottom: 30px; padding-left: 20px; padding-right: 20px;' align='center' valign='top' class='subTitle'>\n"
	  		+ "												<h4 class='text' style='color:#999;font-family:Poppins,Helvetica,Arial,sans-serif;font-size:16px;font-weight:500;font-style:normal;letter-spacing:normal;line-height:24px;text-transform:none;text-align:center;padding:0;margin:0'>Verify Your Email Account</h4>\n"
	  		+ "											</td>\n"
	  		+ "										</tr>\n"
	  		+ "										<tr>\n"
	  		+ "											<td style='padding-left:20px;padding-right:20px' align='center' valign='top' class='containtTable ui-sortable'>\n"
	  		+ "												<table border='0' cellpadding='0' cellspacing='0' width='100%' class='tableDescription' style=''>\n"
	  		+ "													<tbody>\n"
	  		+ "														<tr>\n"
	  		+ "															<td style='padding-bottom: 20px;' align='center' valign='top' class='description'>\n"
	  		+ "																<p class='text' style='color:#666;font-family:'Open Sans',Helvetica,Arial,sans-serif;font-size:14px;font-weight:400;font-style:normal;letter-spacing:normal;line-height:22px;text-transform:none;text-align:center;padding:0;margin:0'>Thanks for joining us. Please enter the code below to   confirm button for your email.</p>\n"
	  		+ "															</td>\n"
	  		+ "														</tr>\n"
	  		+ "													</tbody>\n"
	  		+ "												</table>\n"
	  		+ "												<table border='0' cellpadding='0' cellspacing='0' width='100%' class='tableButton' style=''>\n"
	  		+ "													<tbody>\n"
	  		+ "														<tr>\n"
	  		+ "															<td style='padding-top:20px;padding-bottom:20px' align='center' valign='top'>\n"
	  		+ "																<table border='0' cellpadding='0' cellspacing='0' align='center'>\n"
	  		+ "																	<tbody>\n"
	  		+ "																		<tr>\n"
	  		+ "																			<td style='background-color: rgb(0, 210, 244); padding: 12px 35px; border-radius: 50px;' align='center' class='ctaButton'> <a  style='color:#fff;font-family:Poppins,Helvetica,Arial,sans-serif;font-size:13px;font-weight:600;font-style:normal;letter-spacing:1px;line-height:20px;text-transform:uppercase;text-decoration:none;display:block'  class='text'>"+code+"</a>\n"
	  		+ "																			</td>\n"
	  		+ "																		</tr>\n"
	  		+ "																	</tbody>\n"
	  		+ "																</table>\n"
	  		+ "															</td>\n"
	  		+ "														</tr>\n"
	  		+ "													</tbody>\n"
	  		+ "												</table>\n"
	  		+ "											</td>\n"
	  		+ "										</tr>\n"
	  		+ "									\n"
	  		+ "									\n"
	  		+ "									</tbody>\n"
	  		+ "								</table>\n"
	  		+ "						\n"
	  		+ "							</td>\n"
	  		+ "						</tr>\n"
	  		+ "					</tbody>\n"
	  		+ "				</table>\n"
	  		+ "			\n"
	  		+ "			</td>\n"
	  		+ "		</tr>\n"
	  		+ "	</tbody>\n"
	  		+ "</table>";
	  		
	  
	  
	  
	  
	  es.sendEmail(u.getEmail(), "email confirmation", email);
	
	
	return newU;
}






}