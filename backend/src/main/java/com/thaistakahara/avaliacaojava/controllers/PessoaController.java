package com.thaistakahara.avaliacaojava.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thaistakahara.avaliacaojava.model.entities.Pessoa;
import com.thaistakahara.avaliacaojava.model.repositories.PessoaRepository;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping("/{id}")
	public Optional<Pessoa> getPessoaById(@PathVariable int id) {
		return (Optional<Pessoa>) pessoaRepository.findById(id);
	}
	
	@GetMapping(path = "/pessoas/{numeroPagina}/{qtdPagina}")
	public Iterable<Pessoa> getPessoasPaginada(
			@PathVariable int numeroPagina, @PathVariable int qtdPagina,
			@RequestParam(name = "nome", defaultValue = "") String nome, 
			@RequestParam(name = "cpf", defaultValue = "") String cpf) {
		if(qtdPagina > 10) qtdPagina = 10;
		Pageable page = PageRequest.of(numeroPagina, qtdPagina);
		System.out.println(nome);
		if (!nome.isEmpty() && !cpf.isEmpty()) {
			return pessoaRepository.findByNomeContainingIgnoreCaseAndCpf(nome, cpf, page);
		} else if (!nome.isEmpty() && cpf.isEmpty()) {
			return pessoaRepository.findByNomeContainingIgnoreCase(nome, page);
		}
		else if (nome.isEmpty() && !cpf.isEmpty()) {
			return pessoaRepository.findByCpf(cpf, page);
		}
		return pessoaRepository.findAll(page);
	} 
	
	@GetMapping(path = "/quantidadePessoas")
	public long getQuantidadePessoas() {
		return pessoaRepository.count();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserirPessoa(@RequestBody Pessoa pessoa) {		
		Iterable<Pessoa> iterable = pessoaRepository.findByCpfContaining(pessoa.getCpf());
		iterable.forEach(p -> {
			if (p.getId() != 0) {
				throw new RuntimeException("Já existe pessoa cadastrada com o CPF " +
						p.getCpf() + "!");
			}
		});
		
		pessoa = pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> alterarPessoa(@RequestBody Pessoa pessoa) {
		Iterable<Pessoa> iterable = pessoaRepository.
				searchByCpfAndIdNotContaining(pessoa.getCpf(), pessoa.getId());
		iterable.forEach(p -> {
			if (p.getId() != 0) {
				throw new RuntimeException("Já existe pessoa cadastrada com o CPF " +
						p.getCpf() + "!");
			}
		});
		
		pessoa = pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluirPessoa(@PathVariable int id) {
		pessoaRepository.deleteById(id);
	}

}
