package br.com.fiap.spaceCar.model;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class PessoaCliente extends Usuario {
	@NotBlank(message = "É necessário informar o gênero.")
	private String sexo;
	@Past(message = "É necessário informar uma data antes da atual.")
	@NotNull(message = "É necessário informar um data.")
	private LocalDate dataNasc;
	@NotBlank(message = "É necessário informar o CPF.")
	private String cpf;

	public PessoaCliente() {
		super();
	}
	
	

	public PessoaCliente(int id, @NotBlank(message = "É necessário informar um nome.") String nome,
			@NotBlank(message = "É necessário informar um endereço.") Endereco endereco,
			@NotBlank(message = "É necessário informar o ddd.") String ddd,
			@NotBlank(message = "É necessário informar o telefone.") String telefone,
			@NotBlank(message = "É necessário informar um email.") String email,
			@NotBlank(message = "É necessário informar uma senha.") String senha,
			@NotBlank(message = "É necessário informar o gênero.") String sexo,
			@Past(message = "É necessário informar uma data antes da atual.") @NotNull(message = "É necessário informar um data.") LocalDate dataNasc,
			@NotBlank(message = "É necessário informar o CPF.") String cpf) {
		super(id, nome, endereco, ddd, telefone, email, senha);
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.cpf = cpf;
	}
	



	public PessoaCliente(@NotBlank(message = "É necessário informar um nome.") String nome,
			@NotBlank(message = "É necessário informar o ddd.") String ddd,
			@NotBlank(message = "É necessário informar o telefone.") String telefone,
			@NotBlank(message = "É necessário informar um email.") String email,
			@NotBlank(message = "É necessário informar uma senha.") String senha,
			@NotBlank(message = "É necessário informar o gênero.") String sexo,
			@Past(message = "É necessário informar uma data antes da atual.") @NotNull(message = "É necessário informar um data.") LocalDate dataNasc,
			@NotBlank(message = "É necessário informar o CPF.") String cpf) {
		super(nome, ddd, telefone, email, senha);
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.cpf = cpf;
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
		return "PessoaCliente [sexo=" + sexo + ", dataNasc=" + dataNasc + ", cpf=" + cpf + ", getNome()=" + getNome()
				+ ", getEndereco()=" + getEndereco() + ", getDdd()=" + getDdd() + ", getTelefone()=" + getTelefone()
				+ ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha() + "]";
	}

	
	
}
