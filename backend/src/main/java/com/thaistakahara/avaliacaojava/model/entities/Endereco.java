package com.thaistakahara.avaliacaojava.model.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import utils.Util;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Column(length = 8, nullable = false)
	private String cep;
	
	@NotBlank
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank
	@Column(nullable = false)
	private String numero;
	
	@NotBlank
	@Column(nullable = false)
	private String cidade;
	
	@NotBlank
	@Column(nullable = false)
	private String uf;
	
	public Endereco() {
	}

	public Endereco(String cep, String logradouro, String numero, String cidade, String uf) {
		setCep(cep);
		setLogradouro(logradouro);
		setNumero(numero);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		try {
			Endereco novoEndereco = encontraCep(cep);
			if (novoEndereco.getCep() == null) {
				throw new RuntimeException("CEP não encontrado!");
			}
			setCidade(novoEndereco.cidade);
			setUf(novoEndereco.uf);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	private Endereco encontraCep(String cep) throws IOException {
		String enderecoURL = "https://viacep.com.br/ws/" + cep + "/json/";
		URL url = URI.create(enderecoURL).toURL();
		HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
		conexao.setRequestMethod("GET");
		conexao.setDoInput(true);
		try {
            BufferedReader buff = new BufferedReader(
            		new InputStreamReader((conexao.getInputStream()), "utf-8"));
            String convertJsonString = Util.converteJsonEmString(buff);
            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(convertJsonString, Endereco.class);
            return endereco;
        } catch (Exception msgErro) {
            throw new RuntimeException("Erro de conexão - status Code [" + conexao.getResponseCode() + "]. " + 
            		msgErro.toString()); 
        }
	}

}