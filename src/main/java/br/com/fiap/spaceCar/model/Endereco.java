package br.com.fiap.spaceCar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class Endereco {
	
	@NotBlank(message = "É necessário informar um logradouro")
	private String logradouro;
	@NotNull(message = "É necessário informar o número da residência")
	@PositiveOrZero(message = "O número deve ser maior do que 0")
	private int numero;
	@NotBlank(message = "É necessário informar o bairro")
	private String bairro;
	@NotBlank(message = "É necessário informar a cidade")
	private String cidade;
	@NotBlank(message = "É necessário informar o estado")
	private String estado;
	public Endereco(@NotBlank(message = "É necessário informar um logradouro") String logradouro,
			@NotNull(message = "É necessário informar o número da residência") @PositiveOrZero(message = "O número deve ser maior do que 0") int numero,
			@NotBlank(message = "É necessário informar o bairro") String bairro,
			@NotBlank(message = "É necessário informar a cidade") String cidade,
			@NotBlank(message = "É necessário informar o estado") String estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	public Endereco() {
		super();
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + "]";
	}

	

}
