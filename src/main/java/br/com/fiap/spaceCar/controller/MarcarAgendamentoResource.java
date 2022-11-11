package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.MarcarAgendamentoDAO;
import br.com.fiap.spaceCar.model.MarcarAgendamento;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/marcarAgendamento")
public class MarcarAgendamentoResource {

	/** Função que pega todos os agendamentos presente no nosso banco de dados.
	 * 
	 * @return HTTP RESPONSE 200 OK. Json referente aos registros de agendamento no DB
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<MarcarAgendamento> resp = MarcarAgendamentoDAO.getAll();
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	/** Registrar um agendamento no sistema.
	 * 
	 * @param a -- Json contendo coisas sobre agendamento
	 * @return HTTP RESPONSE 201 (CREATED) E O OBJETO CRIADO EM FORMATO JSON
	 * @author Jefferson
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(MarcarAgendamento a) {
		MarcarAgendamento resp = MarcarAgendamentoDAO.save(a);
		System.out.println(resp);
		final URI marcarAgUri = UriBuilder.fromResource(MarcarAgendamentoResource.class).path("/marcarAgendamento/{id}").build(resp.getCdAgendamento());
		ResponseBuilder response = Response.created(marcarAgUri);
		response.entity(resp);
		return response.build();
	}
	
	
	/** Exclui um agendamento do sistema
	 * 
	 * @param id -- Id do agendamento que deseja excluir vindo da URI da requisição
	 * @return HTTP RESPONSE 204 (No Content) caso exclua, ou HTTP RESPONSE 404 (NotFound) caso não exclua.
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response delete(@PathParam("id")int id) {
		if(MarcarAgendamentoDAO.excluir(id)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}else {
			System.out.println("Não foi possível remover o Agendamento: " + id);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}
	}
	
	
	/** Seleciona um agendamento pelo ID
	 * 
	 * @param id -- vindo da requisição
	 * @return HTTP RESPONSE 200 (OK)
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response listById(@PathParam("id") int id) {
		MarcarAgendamento resp = MarcarAgendamentoDAO.getById(id);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		
		return response.build();
	}
}
