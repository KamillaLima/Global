package br.com.fiap.spaceCar.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public class Carro {
	
	
	private int id;
	@NotBlank(message="É necessário informar a placa do automóvel")
	private String placa;
	
	@PastOrPresent(message = "A data de fabricação deve ser menor ou igual a hoje")
	private LocalDate anoFabricacao;
	
	@PastOrPresent(message = "A data do modelo deve ser menor ou igual a hoje")
	private LocalDate anoModelo;
	
	@NotBlank(message = "É necessário informar o chassi do automóvel")
	private String Chassi;
	
	@NotBlank(message = "É necessário informar a marca do automóvel")
	private String marca;
	
	@NotNull(message= "É necessário informar a quantidade de portas")
	@PositiveOrZero(message = "A quantidade de portas deve ser maior do que zero")
	private int portas;
	
	@NotNull(message= "É necessário informar a quantidade de passageiro que o automóvel comporta")
	@PositiveOrZero(message = "A quantidade de passageiros que o automóvel leva deve ser maior do que zero")
	private int passageiros;
	
	@NotBlank(message = "É necessário informar o tipo do combustível do automóvel")
	private String tipoCombustivel;
	
	@NotNull(message= "É necessário informar a potência do automóvel")
	@PositiveOrZero(message = "A potência do automóvel deve ser maior do que zero")
	private int potencia;
	
	@NotNull(message= "É necessário informar a quantidade de cilindradas do automóvel")
	@PositiveOrZero(message = "A quantidade de cilindradas do automóvel deve ser maior do que zero")
	private int cilindradas;
	
	@NotNull(message= "É necessário informar o motor do automóvel")
	@PositiveOrZero(message = "O valor do motor deve ser maior do que zero")
	private double motor;
	
	@NotNull(message= "É necessário informar os quilômetros rodados do automóvel")
	private int kmRodado;
	
	@NotBlank(message = "É necessário informar o tipo do câmbio do automóvel")
	private String tipoCambio;
	
	@NotBlank(message = "É necessário informar a descrição do problema do automóvel")
	private String descricaoProblema;

	
	public Carro(@NotBlank(message = "É necessário informar a placa do automóvel") String placa,
			@PastOrPresent(message = "A data de fabricação deve ser menor ou igual a hoje") LocalDate anoFabricacao,
			@PastOrPresent(message = "A data do modelo deve ser menor ou igual a hoje") LocalDate anoModelo,
			@NotBlank(message = "É necessário informar o chassi do automóvel") String chassi,
			@NotBlank(message = "É necessário informar a marca do automóvel") String marca,
			@NotNull(message = "É necessário informar a quantidade de portas") @PositiveOrZero(message = "A quantidade de portas deve ser maior do que zero") int portas,
			@NotNull(message = "É necessário informar a quantidade de passageiro que o automóvel comporta") @PositiveOrZero(message = "A quantidade de passageiros que o automóvel leva deve ser maior do que zero") int passageiros,
			@NotBlank(message = "É necessário informar o tipo do combustível do automóvel") String tipoCombustivel,
			@NotNull(message = "É necessário informar a potência do automóvel") @PositiveOrZero(message = "A potência do automóvel deve ser maior do que zero") int potencia,
			@NotNull(message = "É necessário informar a quantidade de cilindradas do automóvel") @PositiveOrZero(message = "A quantidade de cilindradas do automóvel deve ser maior do que zero") int cilindradas,
			@NotNull(message = "É necessário informar o motor do automóvel") @PositiveOrZero(message = "O valor do motor deve ser maior do que zero") double motor,
			@NotNull(message = "É necessário informar os quilômetros rodados do automóvel") int kmRodado,
			@NotBlank(message = "É necessário informar o tipo do câmbio do automóvel") String tipoCambio,
			@NotBlank(message = "É necessário informar a descrição do problema do automóvel") String descricaoProblema) {
		super();
		this.placa = placa;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		Chassi = chassi;
		this.marca = marca;
		this.portas = portas;
		this.passageiros = passageiros;
		this.tipoCombustivel = tipoCombustivel;
		this.potencia = potencia;
		this.cilindradas = cilindradas;
		this.motor = motor;
		this.kmRodado = kmRodado;
		this.tipoCambio = tipoCambio;
		this.descricaoProblema = descricaoProblema;
	}

	public Carro() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDate getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(LocalDate anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public LocalDate getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(LocalDate anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getChassi() {
		return Chassi;
	}

	public void setChassi(String chassi) {
		Chassi = chassi;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getPortas() {
		return portas;
	}

	public void setPortas(int portas) {
		this.portas = portas;
	}

	public int getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(int passageiros) {
		this.passageiros = passageiros;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(int cilindradas) {
		this.cilindradas = cilindradas;
	}

	public double getMotor() {
		return motor;
	}

	public void setMotor(double motor) {
		this.motor = motor;
	}

	public int getKmRodado() {
		return kmRodado;
	}

	public void setKmRodado(int kmRodado) {
		this.kmRodado = kmRodado;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", placa=" + placa + ", anoFabricacao=" + anoFabricacao + ", anoModelo=" + anoModelo
				+ ", Chassi=" + Chassi + ", marca=" + marca + ", portas=" + portas + ", passageiros=" + passageiros
				+ ", tipoCombustivel=" + tipoCombustivel + ", potencia=" + potencia + ", cilindradas=" + cilindradas
				+ ", motor=" + motor + ", kmRodado=" + kmRodado + ", tipoCambio=" + tipoCambio + ", descricaoProblema="
				+ descricaoProblema + "]";
	}


	
	
	
	
	
	
}
