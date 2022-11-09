package br.com.fiap.spaceCar.DAO;

import br.com.fiap.spaceCar.model.PessoaCliente;

public class Main {

	public static void main(String[] args) {
		

		/*
		 * CADASTRAR PESSOA
		 *  PessoaCliente p = new
		 * PessoaCliente("Kamilla Lima","11","98764565", "kamillalima@gmail.com",
		 * "1234556", "F", LocalDate.of(2004, 03, 19), "42536315279");
		 * PessoaClienteDAO.inserirCliente(p);
		 * 
		 * 
		 * LISTAR PESSOAS 
		 * List<PessoaCliente> pessoas = new ArrayList<>(); pessoas =
		 * PessoaClienteDAO.retornarPessoas(); for (int i = 0; i < pessoas.size(); i++)
		 * { System.out.println(pessoas.get(i)); }
		 */
		
		
		
		
		
		/*
		 * CADASTRAR CARRO
		 * 	Carro carro = new Carro("abc1234",LocalDate.of(2012, 10, 14),LocalDate.of(2012, 04, 12),"12345678901472583","Carro",2,4,"diesel",10,12,2.0,100000,"manual","meu carro é uma bosta");
		   CarroDAO.inserirCarro(4, carro);
		
		LISTAR CARRO
		List<Carro>carros = new ArrayList<>();
		carros = CarroDAO.buscarCarro();
		for (Carro carro2 : carros) {
			System.out.println(carro2);
		}
		 * 
		 */
	
		
		

		/*
		 * CADASTRAR OFICINA
		 * //Oficina oficina = new
		 * Oficina("oficina do marcola","11","99224578","marcolasoficina@gmai.com",
		 * "40028922","1234567879","marcolas pica das oficinas");
		 * 
		 * LISTAR OFICINAS
		 * //OficinaDAO.inserirOficina(oficina); List<Oficina> oficinas = new
		 * ArrayList<>(); oficinas = OficinaDAO.verOficinas(); for (Oficina oficina2 :
		 * oficinas) { System.out.println(oficina2); }
		 * 
		 * 
		 * PROCURAR OFICINA POR ID 
		 * 	Oficina  oficina= new Oficina();
		 *oficina = OficinaDAO.procurarOficinaId(1);
		 *System.out.println(oficina);
		
		 */
		
		/*INSERIR UM ENDEREÇO PARA UM CLIENTE
		Endereco endereco1 = new Endereco("rua marte", 233, "conceição", "diadema", "SP","bloco 3,apartamento 12");
		endereco1 = EnderecoDAO.inserirEndereco(endereco1);
		EnderecoDAO.inserirEndCliente(endereco1.getId(), 4);
		
	
		INSERIR UM ENDEREÇO PARA UMA OFICINA
		Endereco endereco = new Endereco("Rua jupiter",656,"Liberdade","São Paulo","SP","esquina 8");
		endereco = EnderecoDAO.inserirEndereco(endereco);
		EnderecoDAO.inserirEnderecoOficina(endereco.getId(), 1);
		*/
		
		/*PessoaCliente pessoa = new PessoaCliente();
		pessoa = PessoaClienteDAO.procurarPessoaClienteId(2);
		System.out.println(pessoa.getNome());*/
	
	}

}
