package com.example.livres;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.livres.service.UserService;



@SpringBootApplication
public class GestionLivresApplication {

	@Autowired
	UserService userService;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionLivresApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
	return new ModelMapper();
	}
	
	
	/*@PostConstruct
	void init_users() {
	//ajouter les rôles
	//	userService.addRole(new Role(null,"SUPERADMIN"));
		
	//userService.addRole(new Role(null,"ADMIN"));
	//userService.addRole(new Role(null,"USER"));
	//ajouter les users
	userService.saveUser(new User(null,"superadmin","123",true,null));
	//userService.saveUser(new User(null,"nadhem","123",true,null));
	//userService.saveUser(new User(null,"yassine","123",true,null));
	//ajouter les rôles aux users
	userService.addRoleToUser("superadmin", "SUPERADMIN");
	//userService.addRoleToUser("admin", "USER");
	//userService.addRoleToUser("nadhem", "USER");
	//userService.addRoleToUser("yassine", "USER");
	} */
	
	
	 @Bean
	  public BCryptPasswordEncoder passwordEncoder () {
	  return new BCryptPasswordEncoder();
	  }
	
	
	  
	  
	
}
