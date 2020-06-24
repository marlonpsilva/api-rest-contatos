package com.marlon.email.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlon.email.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
