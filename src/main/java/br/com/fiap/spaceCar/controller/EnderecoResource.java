package br.com.fiap.spaceCar.controller;


import java.util.List;

import br.com.fiap.spaceCar.DAO.EnderecoDAO;
import br.com.fiap.spaceCar.model.Endereco;

import jakarta.ws.rs.GET;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;


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
	
}
