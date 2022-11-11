package br.com.fiap.spaceCar.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MarcarAgendamento {
	private int cdAgendamento;
	@NotBlank(message = "é necessário informar um código para o usuário.")
	private int cdUsuario;
	@NotBlank(message = "é necessário informar um código para a oficina.")
	private int cdOficina;
	@NotNull(message = "é necessário informar uma data de inicio.")
	@FutureOrPresent
	private LocalDateTime dtHorarioInicio;
	@NotNull(message = "é necessário informar uma data para o termino.")
	@Future
	private LocalDateTime dtHorarioFim;

	public MarcarAgendamento(int cdAgendamento,
			@NotBlank(message = "é necessário informar um código para o usuário.") int cdUsuario,
			@NotBlank(message = "é necessário informar um código para a oficina.") int cdOficina,
			@NotNull(message = "é necessário informar uma data de inicio.") @FutureOrPresent LocalDateTime dtHorarioInicio,
			@NotNull(message = "é necessário informar uma data para o termino.") @Future LocalDateTime dtHorarioFim) {
		super();
		this.cdAgendamento = cdAgendamento;
		this.cdUsuario = cdUsuario;
		this.cdOficina = cdOficina;
		this.dtHorarioInicio = dtHorarioInicio;
		this.dtHorarioFim = dtHorarioFim;
	}

	public MarcarAgendamento() {
		super();
	}

	public int getCdAgendamento() {
		return cdAgendamento;
	}

	public void setCdAgendamento(int cdAgendamento) {
		this.cdAgendamento = cdAgendamento;
	}

	public int getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public int getCdOficina() {
		return cdOficina;
	}

	public void setCdOficina(int cdOficina) {
		this.cdOficina = cdOficina;
	}

	public LocalDateTime getDtHorarioInicio() {
		return dtHorarioInicio;
	}

	public void setDtHorarioInicio(LocalDateTime dtHorarioInicio) {
		this.dtHorarioInicio = dtHorarioInicio;
	}

	public LocalDateTime getDtHorarioFim() {
		return dtHorarioFim;
	}

	public void setDtHorarioFim(LocalDateTime dtHorarioFim) {
		this.dtHorarioFim = dtHorarioFim;
	}

	@Override
	public String toString() {
		return "MarcarAgendamento [cdAgendamento=" + cdAgendamento + ", cdUsuario=" + cdUsuario + ", cdOficina="
				+ cdOficina + ", dtHorarioInicio=" + dtHorarioInicio + ", dtHorarioFim=" + dtHorarioFim + "]";
	}

}
