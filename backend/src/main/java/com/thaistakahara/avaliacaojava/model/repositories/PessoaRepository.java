package com.thaistakahara.avaliacaojava.model.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.thaistakahara.avaliacaojava.model.entities.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer>,
	PagingAndSortingRepository<Pessoa, Integer>{
	
	Page<Pessoa> findByNomeContainingIgnoreCaseAndCpf(String nome, String cpf, Pageable page);
	Page<Pessoa> findByNomeContainingIgnoreCase(String nome, Pageable page);
	Page<Pessoa> findByCpf(String cpf, Pageable page);
	
	public Iterable<Pessoa> findByCpfContaining(String cpf);
	
	@Query("SELECT p FROM Pessoa p WHERE p.cpf = :cpf AND p.id <> :id")
	public Iterable<Pessoa> searchByCpfAndIdNotContaining(@Param ("cpf") String cpf, @Param ("id") int id);

}
