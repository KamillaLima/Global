package br.com.fiap.spaceCar.model;

import java.time.LocalDateTime;

public class Agendamento {

	private int cdAgendamento;
	private int cdUsuario;
	private int cdOficina;
	private LocalDateTime dtHorarioInicio;
	private LocalDateTime dtHorarioFim;

	public Agendamento() {
		super();
	}

	public Agendamento(int cdAgendamento, int cdUsuario, int cdOficina, LocalDateTime dtHorarioInicio,
			LocalDateTime dtHorarioFim) {
		super();
		this.cdAgendamento = cdAgendamento;
		this.cdUsuario = cdUsuario;
		this.cdOficina = cdOficina;
		this.dtHorarioInicio = dtHorarioInicio;
		this.dtHorarioFim = dtHorarioFim;
	}

	public int getCdAgendamento() {
		return cdAgendamento;
	}

	public int getCdUsuario() {
		return cdUsuario;
	}

	public int getCdOficina() {
		return cdOficina;
	}

	public LocalDateTime getDtHorarioInicio() {
		return dtHorarioInicio;
	}

	public LocalDateTime getDtHorarioFim() {
		return dtHorarioFim;
	}

	@Override
	public String toString() {
		return "Agendamento [cdAgendamento=" + cdAgendamento + ", cdUsuario=" + cdUsuario + ", cdOficina=" + cdOficina
				+ ", dtHorarioInicio=" + dtHorarioInicio + ", dtHorarioFim=" + dtHorarioFim + "]";
	}

	
}
