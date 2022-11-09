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

@Path("/agenda")
public class AgendamentoResource {

	
	
	/** Função que pega todos os agendamentos presente no nosso banco de dados.
	 * 
	 * @return HTTP RESPONSE 200 OK. Json referente aos registros de agendamento no DB
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Agendamento> resp = AgendamentoDAO.getAll();
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
}
