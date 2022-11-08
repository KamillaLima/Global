package br.com.fiap.spaceCar.model;

import java.time.LocalDate;

public class Agendamento {

	private int idUser;
	private int idOfc;
	private LocalDate horario;

	public Agendamento(int idUser, int idOfc, LocalDate horario) {
		this.idUser = idUser;
		this.idOfc = idOfc;
		this.horario = horario;
	}

	public Agendamento() {
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdOfc() {
		return idOfc;
	}

	public void setIdOfc(int idOfc) {
		this.idOfc = idOfc;
	}

	public LocalDate getHorario() {
		return horario;
	}

	public void setHorario(LocalDate horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Agendamento [idUser=" + idUser + ", idOfc=" + idOfc + ", horario=" + horario + "]";
	}

}
