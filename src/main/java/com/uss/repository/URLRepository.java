package com.uss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uss.domain.URL;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
	
	Optional<URL> findByLongURL(String longURL);
}
