package com.thaistakahara.avaliacaojava.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EnderecoTeste {
	
	private Endereco endereco;
	
	@Test
	void testeNovoEndereco() {
		endereco = new Endereco(
				"16240000", 
				"Rua Clarinda Rosa Neto", 
				"588", 
				"Santópolis", 
				"SP");
		
		assertEquals(endereco.getCep()       , "16240000");
		assertEquals(endereco.getLogradouro(), "Rua Clarinda Rosa Neto");
		assertEquals(endereco.getNumero()    , "588");
		assertEquals(endereco.getCidade()    , "Santópolis do Aguapeí");
		assertEquals(endereco.getUf()        , "SP");
	}
	
	@Test
	void testeNovoEnderecoDefault() {
		endereco = new Endereco();
		
		endereco.setId(1);
		endereco.setCep("16240000");
		endereco.setLogradouro("Rua Clarinda Rosa Neto"); 
		endereco.setNumero("588");
		
		assertEquals(endereco.getId()        , 1);
		assertEquals(endereco.getCep()       , "16240000");
		assertEquals(endereco.getLogradouro(), "Rua Clarinda Rosa Neto");
		assertEquals(endereco.getNumero()    , "588");
		assertEquals(endereco.getCidade()    , "Santópolis do Aguapeí");
		assertEquals(endereco.getUf()        , "SP");
	}
	
	@Test
	void testeCepInvalido() {
		endereco = new Endereco();
		assertThrows(RuntimeException.class, () -> {
			endereco.setCep("16240001");
		});
	}

}
