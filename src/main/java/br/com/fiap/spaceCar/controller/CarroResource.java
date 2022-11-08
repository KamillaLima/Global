package br.com.fiap.spaceCar.controller;

import java.util.List;

import br.com.fiap.spaceCar.DAO.CarroDAO;
import br.com.fiap.spaceCar.model.Carro;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/carro")
public class CarroResource {

	/** Método GET que retorna um JSON de todos os carros cadastrados em nosso sistema no banco de dados.
	 * 
	 * @return Resposta 200 http, JSON file contendo todas as vagas.
	 * @author Jefferson
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Carro> resp = CarroDAO.buscarCarro();
		
		for (Carro carro : resp) {
			System.out.println(carro);
		}
		
		ResponseBuilder response = Response.ok();
		response.entity(resp);

		return response.build();
	}
	
	
	
}
