package com.thaistakahara.avaliacaojava.model.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Date dataNascimento;

	@Column(length = 11, nullable = false)
	private String cpf;

	@NotBlank
	@Column(length = 11, nullable = false)
	private String telefone;

	@Min(0)
	@Column(nullable = false)
	private int idade;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "pessoa_id")
	private List<Endereco> enderecos;

	public Pessoa() {
	}

	public Pessoa(String nome, Date dataNascimento, String cpf, String telefone, int idade, List<Endereco> enderecos) {
		setNome(nome);
		setDataNascimento(dataNascimento);
		setCpf(cpf);
		setTelefone(telefone);
		setIdade(idade);
		setEnderecos(enderecos);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		validaDataNascimento(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		validaCPF(cpf);
		this.cpf = cpf;
	}

	public String getTelefone() {
		return String.format("(%s)%s-%s", 
				telefone.substring(0, 2), telefone.substring(2, 7), telefone.substring(7));
	}

	public void setTelefone(String telefone) {
		validaTelefone(telefone);
		this.telefone = telefone;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void validaTelefone(String telefone) {
		if (telefone.length() != 11) {
			throw new RuntimeException("Telefone deve possuir 11 dígitos!");
		}
	}

	private void validaCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		boolean valido = cpfValidator.isValid(cpf, null);

		if (!valido) {
			throw new RuntimeException("CPF inválido!");
		}
	}

	private void validaDataNascimento(Date dataNascimento) {
		Date dataAtual = new Date();
		if (dataAtual.before(dataNascimento)) {
			throw new RuntimeException("Data de Nascimento não pode ser maior que a data atual!");
		}
	}

}
