package com.marlon.email.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marlon.email.controller.dto.ContatoDto;
import com.marlon.email.controller.form.ContatoForm;
import com.marlon.email.model.Contato;
import com.marlon.email.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	@GetMapping
	public List<ContatoDto> lista(String nome) {
		if (nome == null) {
			List<Contato> contatos = contatoRepository.findAll();
			return ContatoDto.converter(contatos);
		} else {
			List<Contato> contatos = contatoRepository.findByNome(nome);
			return ContatoDto.converter(contatos);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ContatoDto> cadastrar(@RequestBody @Valid ContatoForm form, UriComponentsBuilder uriBuilder) {
		Contato contato = form.converter();
		contatoRepository.save(contato);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(contato.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContatoDto(contato));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContatoDto> detalhar(@PathVariable Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		if (contato.isPresent()) {
			return ResponseEntity.ok(new ContatoDto(contato.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContatoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoForm form) {
		Optional<Contato> optional = contatoRepository.findById(id);
		if (optional.isPresent()) {
			Contato contato = form.atualiza(id, contatoRepository);
			return ResponseEntity.ok(new ContatoDto(contato));
		}
		return ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Contato> optional = contatoRepository.findById(id);
		if(optional.isPresent()) {
			contatoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
