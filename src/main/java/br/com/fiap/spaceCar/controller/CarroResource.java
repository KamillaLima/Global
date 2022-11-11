package br.com.fiap.spaceCar.controller;

import java.util.List;

import br.com.fiap.spaceCar.DAO.CarroDAO;
import br.com.fiap.spaceCar.model.Carro;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
	
	
	/** Função para atualizar os dados de um carro no DB
	 * 
	 * @param id -- Do carro que você quer mudar
	 * @param c -- Objeto carro (é o que vem do JSON)
	 * @return HTTP RESPONSE 200, e o JSON referente ao registro no banco de dados.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response save(@PathParam("id") int id, @Valid Carro c){
		Carro velho = c;
		System.out.println(velho.getId());
		Carro novo = null;
		novo = CarroDAO.uptadeCarro(c);
		return Response.ok(novo).build();
	}
	
	
	/*** Pega o registro de carro referente ao id dado.
	 * 
	 * @param id referente ao PK do carro
	 * @return HTTP RESPONSE OK (200), E JSON Referente ao registro do carro com a ID data no param1
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) {
		Carro resp = CarroDAO.getByCarId(id);
		
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
}
