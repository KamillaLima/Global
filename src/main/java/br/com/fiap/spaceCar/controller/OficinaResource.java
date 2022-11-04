package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.OficinaDAO;
import br.com.fiap.spaceCar.model.Oficina;
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
	public Response save(Oficina o) {
		Oficina resp = OficinaDAO.inserirOficina(o);

		final URI ofcinaUri = UriBuilder.fromResource(OficinaResource.class).path("/oficina/{id}").build(resp.getId());

		ResponseBuilder response = Response.created(ofcinaUri);
		response.entity(resp);

		return response.build();
	}
}
