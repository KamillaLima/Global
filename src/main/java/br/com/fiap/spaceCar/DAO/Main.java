package br.com.fiap.spaceCar.DAO;


import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Carro;

public class Main {

	public static void main(String[] args) {
//		Endereco endereco = new Endereco("rua jupiter",23,"serraria","diadema","SP");
//		
//		ArrayList<String> telefones = new ArrayList<>();
//		telefones.add("78945614");
//		telefones.add("135543546");
//		PessoaCliente p = new PessoaCliente("aaaaaaaa", endereco,telefones, "aaaaaa@gmail.com", "7777777", "M", LocalDate.of(2004, 04, 19), "888888888");
//		PessoaClienteDAO pc = new PessoaClienteDAO();
//		pc.inserirCliente(p);
//		
		
//		Carro carro = new Carro("abc1234",LocalDate.of(2012, 10, 14),LocalDate.of(2012, 04, 12),"12345678901472583","Carro",2,4,"diesel",10,12,2.0,100000,"manual","meu carro é uma bosta");
//		CarroDAO.inserirCarro(17, carro);
		List<Carro>carros = new ArrayList<>();
		carros = CarroDAO.buscarCarro();
		for (Carro carro2 : carros) {
			System.out.println(carro2);
		}
	}

}
