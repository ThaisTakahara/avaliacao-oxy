package com.thaistakahara.avaliacaojava.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PessoaTeste {
	
	private Pessoa pessoa;
	
//	@BeforeEach
//	void inicializar() {
//		
//	}
	
	@Test
	public void testeNovaPessoa() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse("23/09/1991");
		
		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(new Endereco("16240000", "Rua Clarinda Rosa Neto", "588", "Santópolis", "SP"));
		enderecos.add(new Endereco("16240000", "Rua Manoel Bento Neto", "121B", "Santópolis", "SP"));
		
		pessoa = new Pessoa(
				"Thais Takahara", 
				dataFormatada, 
				"05462686099", 
				"18999999999", 
				32, 
				enderecos);
		
		assertEquals(pessoa.getNome()          , "Thais Takahara");
		assertEquals(pessoa.getDataNascimento(), dataFormatada);
		assertEquals(pessoa.getCpf()           , "05462686099");
		assertEquals(pessoa.getTelefone()      , "(18)99999-9999");
		assertEquals(pessoa.getIdade()         , 32);
		assertEquals(pessoa.getEnderecos()     , enderecos);
	}
	
	@Test
	public void testeNovaPessoaDefault() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse("23/09/1991");
		
		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(new Endereco("16240000", "Rua Clarinda Rosa Neto", "588", "Santópolis", "SP"));
		enderecos.add(new Endereco("16240000", "Rua Manoel Bento Neto", "121B", "Santópolis", "SP"));
		
		pessoa = new Pessoa();
		
		pessoa.setId(1);
		pessoa.setNome("Thais Takahara");
		pessoa.setDataNascimento(dataFormatada);
		pessoa.setCpf("05462686099");
		pessoa.setTelefone("18999999999");
		pessoa.setIdade(32);
		pessoa.setEnderecos(enderecos);
		
		assertEquals(pessoa.getId()           , 1);
		assertEquals(pessoa.getNome()          , "Thais Takahara");
		assertEquals(pessoa.getDataNascimento(), dataFormatada);
		assertEquals(pessoa.getCpf()           , "05462686099");
		assertEquals(pessoa.getTelefone()      , "(18)99999-9999");
		assertEquals(pessoa.getIdade()         , 32);
		assertEquals(pessoa.getEnderecos()     , enderecos);
	}
	
	@Test
	public void telefoneInvalido() {
		pessoa = new Pessoa();
		assertThrows(RuntimeException.class, () -> {
			pessoa.setTelefone("189999999");
		});
	}
	
	@Test void cpfInvalido() {
		pessoa = new Pessoa();
		assertThrows(RuntimeException.class, () -> {
			pessoa.setCpf("05462686098");
		});
	}
	
	@Test void dataNascimentoInvalida() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse("22/02/2024");
		
		pessoa = new Pessoa();
		assertThrows(RuntimeException.class, () -> {
			pessoa.setDataNascimento(dataFormatada);
		});
	}

}
