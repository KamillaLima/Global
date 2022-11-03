package br.com.fiap.spaceCar.model;

import java.util.ArrayList;
import jakarta.validation.constraints.NotBlank;


public class Usuario {
	
	private int id;
	@NotBlank(message = "É necessário informar um nome.")
	private String nome;

	@NotBlank(message = "É necessário informar um endereço.")
	private Endereco endereco;

	@NotBlank(message = "É necessário informar pelo menos um telefone.")
	private ArrayList<String> telefone;

	@NotBlank(message = "É necessário informar um email.")
	private String email;
	@NotBlank(message = "É necessário informar uma senha.")
	private String senha;

	public Usuario() {
		super();
	}

	public Usuario(@NotBlank(message = "É necessário informar um nome.") String nome,
			@NotBlank(message = "É necessário informar um endereço.") Endereco endereco,
			@NotBlank(message = "É necessário informar pelo menos um telefone.") ArrayList<String> telefone,
			@NotBlank(message = "É necessário informar um email.") String email,
			@NotBlank(message = "É necessário informar uma senha.") String senha) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(ArrayList<String> telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email
				+ ", senha=" + senha + "]";
	}

}
