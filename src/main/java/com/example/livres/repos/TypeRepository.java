package com.example.livres.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livres.entities.Type;



public interface TypeRepository extends JpaRepository<Type,Long>{
	
}
