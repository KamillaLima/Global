package br.com.fiap.spaceCar.model;

import java.time.LocalDateTime;

public class MarcarAgendamento {
	private int cdAgendamento;
	private int cdUsuario;
	private int cdOficina;
	private LocalDateTime dtHorarioInicio;
	private LocalDateTime dtHorarioFim;

	public MarcarAgendamento(int cdAgendamento, int cdUsuario, int cdOficina, LocalDateTime dtHorarioInicio,
			LocalDateTime dtHorarioFim) {
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
