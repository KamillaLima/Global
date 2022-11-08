package br.com.fiap.spaceCar.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.spaceCar.DAO.CarroDAO;
import br.com.fiap.spaceCar.DAO.EnderecoDAO;
import br.com.fiap.spaceCar.DAO.PessoaClienteDAO;
import br.com.fiap.spaceCar.model.Agendamento;
import br.com.fiap.spaceCar.model.Carro;
import br.com.fiap.spaceCar.model.Endereco;
import br.com.fiap.spaceCar.model.PessoaCliente;
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
	public Response save (PessoaCliente pessoa) {
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
	@Path("/{id}")
	public Response save(@PathParam("id") int id,Carro c) {
		Carro resp = CarroDAO.inserirCarro(id, c);
		
		final URI carroUri = UriBuilder.fromResource(PessoaClienteResource.class).path("/carro/{id}")
				.build(resp.getId());
		ResponseBuilder response = Response.created(carroUri);
		response.entity(resp);
		return response.build();
	}
	
	
	
	
	/** Método para salvar um endereço dentro do nosso programa, recebe um JSON referente a classe model endereço.
	 * 
	 * @param e - Json referente ao endereço do que deseja salvar.
	 * @return HTTP 201 (created) e a entidade que foi salva no DB.
	 * @author Jefferson.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/endereco")
	// TODO: TALVEZ SEJA EXCLUÍDO  -- JEFF
	public Response saveEnd(Endereco e) {
		Endereco resp = EnderecoDAO.inserirEndereco(e);
		final URI enderecoUri =  UriBuilder.fromResource(EnderecoResource.class).path("/endereco/{id}").build(resp.getId());
		ResponseBuilder response = Response.created(enderecoUri);
		response.entity(resp);
		
		return response.build();
	}
	

	
	/**
	 * Método o que faz o registro de um agendamento.
	 * @param idPes id do usuário vindo através de sua URL.
	 * @param JSON agendamento que vem do front
	 * @return HTTP resp 201 (created), e a um json referente a classe Agendamento.
	 * @author Jefferson
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/agendar")
	public Response fazerAgendamento (@PathParam("id") int idPes, Agendamento agenda) {
		Agendamento agend = agenda;
		agend.setIdUser(idPes);
		Agendamento resp  = PessoaClienteDAO.fazerAngendamento(agend);
		String idUStr = Integer.toString(agenda.getIdUser());
		String idOf = Integer.toString(agenda.getIdOfc());
		String idFinal = idUStr + idOf;
		
		final URI agendamentoUri = UriBuilder.fromResource(Agendamento.class).path("agentamendo/{id}").build(idFinal);
		System.out.println(agendamentoUri);
		ResponseBuilder response = Response.created(agendamentoUri);
		response.entity(resp);

		return response.build();
	}
	
}
