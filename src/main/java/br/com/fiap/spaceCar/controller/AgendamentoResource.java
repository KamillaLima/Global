package br.com.fiap.spaceCar.controller;

import java.util.List;

import br.com.fiap.spaceCar.DAO.AgendamentoDAO;
import br.com.fiap.spaceCar.model.Agendamento;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/agendamento")
public class AgendamentoResource {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	private Response getAll() {
		List<Agendamento> resp = AgendamentoDAO.getAll();
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
}
