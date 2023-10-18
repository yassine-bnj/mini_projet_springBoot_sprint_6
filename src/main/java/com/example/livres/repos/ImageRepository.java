package com.example.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livres.entities.Image;

public interface ImageRepository extends JpaRepository<Image , Long> {
}

