package com.marlon.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlon.email.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	List<Contato> findByNome(String nome);

}