package br.com.fiap.spaceCar.model;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class PessoaCliente extends Usuario {
	@NotBlank(message = "É necessário informar o sexo.")
	private String sexo;

	@Past(message = "É necessário informar uma data antes da atual.")
	@NotNull(message = "É necessário informar um data.")
	private LocalDate dataNasc;

	@NotBlank(message = "É necessário informar o CPF.")
	private String cpf;

	public PessoaCliente(@NotBlank(message = "É necessário informar um nome.") String nome,
			@NotBlank(message = "É necessário informar um endereço.") Endereco endereco,
			@NotBlank(message = "É necessário informar pelo menos um telefone.") ArrayList<String> telefone,
			@NotBlank(message = "É necessário informar um email.") String email,
			@NotBlank(message = "É necessário informar uma senha.") String senha,
			@NotBlank(message = "É necessário informar o sexo.") String sexo,
			@Past(message = "É necessário informar uma data antes da atual.") @NotNull(message = "É necessário informar um data.") LocalDate dataNasc,
			@NotBlank(message = "É necessário informar o CPF.") String CPF) {
		super(nome, endereco, telefone, email, senha);
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.cpf = CPF;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaCliente [sexo=" + sexo + ", dataNasc=" + dataNasc + ", cpf=" + cpf + "]";
	}
	
}
