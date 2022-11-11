package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.MarcarAgendamentoDAO;
import br.com.fiap.spaceCar.DAO.OficinaDAO;
import br.com.fiap.spaceCar.model.MarcarAgendamento;
import br.com.fiap.spaceCar.model.Oficina;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/oficina")
public class OficinaResource {

	
	
	/** Método que retorna todas as oficinas registradas no BD
	 * 
	 * @return RESPOSTA 200
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Oficina> resp = OficinaDAO.verOficinas();
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	/** Retorna a pessoa baseada no id vindo do link.
	 * 
	 * @param id -- id que será pego pelo link
	 * @return resposta 201 created
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		Oficina resp = OficinaDAO.procurarOficinaId(id);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}

	/** Método que consulta os agendamentos de uma determinada oficina através do ID dado.
	 * 
	 * @param id -- id dá oficina que deseja consultar vindo da URI da requisição
	 * @return HTTP RESPONSE 200 (OK) - E os agendamentos do banco em JSON.
	 * @author Jefferson
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/agendamentos")
	public Response getAgendamentosById(@PathParam("id") int id) {
		List<MarcarAgendamento> resp = MarcarAgendamentoDAO.getByOficinaId(id);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	/** Cria uma oficina dentro do BD baseado no json vindo do front.
	 * 
	 * 
	 * @param o -- JSON que espera um objeto com os parametros de Oficina
	 * @return resposta 201 (created)
	 * @author Jefferson
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Valid Oficina o) {
		Oficina resp = OficinaDAO.inserirOficina(o);

		final URI ofcinaUri = UriBuilder.fromResource(OficinaResource.class).path("/oficina/{id}").build(resp.getId());

		ResponseBuilder response = Response.created(ofcinaUri);
		response.entity(resp);

		return response.build();
	}
	
	/** Verifica 
	 * 
	 * @param o -- JSON vindo do front contendo apenas EMAIL e SENHA para testar o login
	 * @return HTTP RESPONSE 200 (OK) , e o JSON referente a pessoa no DB.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response verificar (Oficina o) {
		String email = o.getEmail();
		String senha = o.getSenha();
		Oficina resp = OficinaDAO.getByEmailSenha(email, senha);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response atualizar(@PathParam("id") int id, Oficina o) {
		Oficina velha = OficinaDAO.procurarOficinaId(id);
		Oficina nova = null;
		if (velha == null || velha.getId() != o.getId()) {
			nova = OficinaDAO.inserirOficina(o);
			final URI oficinaUri = UriBuilder.fromResource(OficinaResource.class).path("/oficina/{id}")
					.build(nova.getId());
			ResponseBuilder response = Response.created(oficinaUri);
			response.entity(nova);
			return response.build();
		}
		nova = OficinaDAO.alterarInfosOficina(o);
		return Response.ok(nova).build();
		
	}
}
