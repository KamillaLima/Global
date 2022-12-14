package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.CarroDAO;
import br.com.fiap.spaceCar.DAO.MarcarAgendamentoDAO;
import br.com.fiap.spaceCar.DAO.PessoaClienteDAO;
import br.com.fiap.spaceCar.model.Carro;
import br.com.fiap.spaceCar.model.MarcarAgendamento;
import br.com.fiap.spaceCar.model.PessoaCliente;
import br.com.fiap.spaceCar.model.Usuario;
import jakarta.validation.Valid;
//import br.com.fiap.tads.ddd.coffe.controller.CoffeeResource;
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

@Path("/cliente")
public class PessoaClienteResource {

	
	/** Método que salva uma pessoa no banco de dados pegando a requisição do front.
	 * 
	 * @author Jefferson
	 * @param JSON que espera os atributos da classe modelo PessoaCliente
	 * @return resposta created.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save (@Valid PessoaCliente pessoa) {
		//System.out.println(pessoa);
		
		
		PessoaCliente resp = PessoaClienteDAO.inserirCliente(pessoa);
		final URI pessoaUri = UriBuilder.fromResource(PessoaClienteResource.class).path("/cliente/{id}")
				.build(resp.getId());
		ResponseBuilder response = Response.created(pessoaUri);
		response.entity(resp);
		return response.build();
	}
	
	/** Método que retorna todas as pessoas presente em nosso banco de dados.
	 * @author Jefferson
	 * @return resposta 200
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<PessoaCliente> resp = PessoaClienteDAO.retornarPessoas();
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}//TODO: Possível método que pode ser exlcuido, ele é um método teste.
	
	
	/** Método para adicionarmos um carro em nosso sistema
	 * 
	 * @param ID do usuário o qual está cadastrando o carro
	 * @param Objeto carro vindo do front
	 * @return resposta 201 (Created)
	 * @author Jefferson
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}/carros")
	public Response save(@PathParam("id") int id,Carro c) {
		System.out.println(c);
		Carro resp = CarroDAO.inserirCarro(id, c);
		System.out.println(resp);
		
		final URI carroUri = UriBuilder.fromResource(PessoaClienteResource.class).path("/carro/{id}")
				.build(resp.getId());
		ResponseBuilder response = Response.created(carroUri);
		response.entity(resp);
		return response.build();
	}
	
	/** Comando que lista todos os carros de um usuário baseado em seu ID.
	 * 
	 * @param idUser -- Parametro que vem da URI da requisição.
	 * @return HTTP RESPONSE 200 (OK), e o JSON referente a lista de carros do usuário.
	 * @author Jefferson
	 */
	@GET
	@Produces
	@Path("/{id}/carros")
	public Response getCarsByIdUser(@PathParam("id")int idUser) {
		List<Carro> resp = CarroDAO.getByUserId(idUser);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	
	/** Método que seleciona os agendamentos do usuário baseados em seu ID.
	 * 
	 * @param idUser -- Id que vem da URI da requisição
	 * @return HTTP RESPONSE 200 (ok), e um json referente aos agendamentos do usuário registrados no banco
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/agendamentos")
	public Response getAllAgendamentos(@PathParam("id") int idUser) {
		List<MarcarAgendamento> resp = MarcarAgendamentoDAO.getByUserId(idUser);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response verificar(PessoaCliente p) {
		String email = p.getEmail();
		String senha = p.getSenha();
		Usuario resp = PessoaClienteDAO.getByEmailSenha(email, senha);
		ResponseBuilder response = Response.ok();
		response.entity(resp);
		return response.build();
	}
}
