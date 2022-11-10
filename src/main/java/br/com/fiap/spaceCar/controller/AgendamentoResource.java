package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.AgendamentoDAO;
import br.com.fiap.spaceCar.model.Agendamento;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/agendamento")
public class AgendamentoResource {

	/** Salva um agendamento em nosso sistema.
	 * 
	 * @param a -- Objeto Agenda chega através do JSON do Front
	 * @return HTTP RESPONSE 201 (CREATED), e o objeto salvo no DB.
	 * @author Jefferson
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Agendamento a) {
		Agendamento resp = AgendamentoDAO.save(a);
		
		final URI agendamentoUri = UriBuilder.fromResource(AgendamentoResource.class).path("/agendamento/{id}").build(resp.getCdAgendamento());
		
		ResponseBuilder response = Response.created(agendamentoUri);
		response.entity(resp);
		
		return response.build();
	}
	
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
	
	/** Retora um único objeto Agendamento baseado no ID passado na URI
	 * 
	 * @param id -- vindo da URL da requisição, serve para filtrar qual objeto pegaremos
	 * @return HTTP RESPONSE 200 (ok) , e retorna também o objeto criado.
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById (@PathParam("id") int id) {
		Agendamento resp = AgendamentoDAO.getById(id);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	
}
