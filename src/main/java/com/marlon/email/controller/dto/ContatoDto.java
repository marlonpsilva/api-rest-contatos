package com.marlon.email.controller.dto;

import org.springframework.data.domain.Page;

import com.marlon.email.model.Contato;

public class ContatoDto {

	private String nome;
	private String email;
	private String campanha;

	public ContatoDto(Contato contato) {
		this.nome = contato.getNome();
		this.email = contato.getEmail();
		this.setCampanha(contato.getCampanha());
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCampanha() {
		return campanha;
	}

	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	public static Page<ContatoDto> converter(Page<Contato> contatos) {
		return contatos.map(ContatoDto::new);
	}
}
