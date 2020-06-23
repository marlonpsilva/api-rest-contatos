package com.marlon.email.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marlon.email.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	Page<Contato> findByNome(String nome, Pageable paginacao);

}