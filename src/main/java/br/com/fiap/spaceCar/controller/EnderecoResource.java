package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.EnderecoDAO;
import br.com.fiap.spaceCar.model.Endereco;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/endereco")
public class EnderecoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Endereco> resp = EnderecoDAO.getAllEnderecos();
		
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Endereco e) {
		Endereco resp = EnderecoDAO.inserirEndereco(e);
		
		final URI enderecoUri =  UriBuilder.fromResource(EnderecoResource.class).path("/endereco/{id}").build(resp.getId());
		
		ResponseBuilder response = Response.created(enderecoUri);
		response.entity(resp);
		
		return response.build();
	}
	
	
}
