package com.marlon.email.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.marlon.email.model.Contato;
import com.marlon.email.repository.ContatoRepository;

public class ContatoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String email;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String campanha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCampanha() {
		return campanha;
	}

	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	public Contato converter() {
		return new Contato(nome, email, campanha);
	}

	public Contato atualiza(Long id, ContatoRepository contatoRepository) {
		Contato contato = contatoRepository.getOne(id);
		contato.setNome(this.nome);
		contato.setEmail(this.email);
		contato.setCampanha(this.campanha);
		return contato;
	}
}
