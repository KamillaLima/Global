package br.com.fiap.spaceCar.model;

import jakarta.validation.constraints.NotBlank;

public class Oficina extends Usuario {

	@NotBlank(message = "É necessário informar um CNPJ.")
	private String cnpj;
	@NotBlank(message = "É necessário informar uma descrição.")
	private String descricao;
	

	public Oficina(@NotBlank(message = "É necessário informar um nome.") String nome,
			@NotBlank(message = "É necessário informar o ddd.") String ddd,
			@NotBlank(message = "É necessário informar o telefone.") String telefone,
			@NotBlank(message = "É necessário informar um email.") String email,
			@NotBlank(message = "É necessário informar uma senha.") String senha,
			@NotBlank(message = "É necessário informar um CNPJ.") String cnpj,
			@NotBlank(message = "É necessário informar uma descrição.") String descricao) {
		super(nome, ddd, telefone, email, senha);
		this.cnpj = cnpj;
		this.descricao = descricao;
	}
	
	

	public Oficina(int id, @NotBlank(message = "É necessário informar um nome.") String nome,
			@NotBlank(message = "É necessário informar um endereço.") Endereco endereco,
			@NotBlank(message = "É necessário informar o ddd.") String ddd,
			@NotBlank(message = "É necessário informar o telefone.") String telefone,
			@NotBlank(message = "É necessário informar um email.") String email,
			@NotBlank(message = "É necessário informar uma senha.") String senha,
			@NotBlank(message = "É necessário informar um CNPJ.") String cnpj,
			@NotBlank(message = "É necessário informar uma descrição.") String descricao) {
		super(id, nome, endereco, ddd, telefone, email, senha);
		this.cnpj = cnpj;
		this.descricao = descricao;
	}



	public Oficina() {
		super();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	@Override
	public String toString() {
		return "Oficina [cnpj=" + cnpj + ", descricao=" + descricao + ", getNome()=" + getNome() + ", getEndereco()="
				+ getEndereco() + ", getDdd()=" + getDdd() + ", getTelefone()=" + getTelefone() + ", getEmail()="
				+ getEmail() + ", getSenha()=" + getSenha() + "]";
	}


}
