package br.com.fiap.spaceCar.model;

import java.time.LocalDate;

public class Agendamento {

	private int cdAgendamento;
	private int cdUsuario;
	private int cdOficina;
	private LocalDate dtHorarioInicio;
	private LocalDate dtHorarioFim;

	public Agendamento(int cdAgendamento, int cdUsuario, int cdOficina, LocalDate dtHorarioInicio,
			LocalDate dtHorarioFim) {
		super();
		this.cdAgendamento = cdAgendamento;
		this.cdUsuario = cdUsuario;
		this.cdOficina = cdOficina;
		this.dtHorarioInicio = dtHorarioInicio;
		this.dtHorarioFim = dtHorarioFim;
	}

	public Agendamento() {
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

	public LocalDate getDtHorarioInicio() {
		return dtHorarioInicio;
	}

	public void setDtHorarioInicio(LocalDate dtHorarioInicio) {
		this.dtHorarioInicio = dtHorarioInicio;
	}

	public LocalDate getDtHorarioFim() {
		return dtHorarioFim;
	}

	public void setDtHorarioFim(LocalDate dtHorarioFim) {
		this.dtHorarioFim = dtHorarioFim;
	}

	@Override
	public String toString() {
		return "Agendamento [cdAgendamento=" + cdAgendamento + ", cdUsuario=" + cdUsuario + ", cdOficina=" + cdOficina
				+ ", dtHorarioInicio=" + dtHorarioInicio + ", dtHorarioFim=" + dtHorarioFim + "]";
	}

}
