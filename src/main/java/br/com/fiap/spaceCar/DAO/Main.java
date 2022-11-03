package br.com.fiap.spaceCar.DAO;

import java.time.LocalDate;
import java.util.ArrayList;


import br.com.fiap.spaceCar.model.Endereco;
import br.com.fiap.spaceCar.model.PessoaCliente;

public class Main {

	public static void main(String[] args) {
		Endereco endereco = new Endereco("rua jupiter",23,"serraria","diadema","SP");
		
		ArrayList<String> telefones = new ArrayList<>();
		telefones.add("78945614");
		telefones.add("135543546");
		PessoaCliente p = new PessoaCliente("aaaaaaaa", endereco,telefones, "aaaaaa@gmail.com", "7777777", "M", LocalDate.of(2004, 04, 19), "888888888");
		PessoaClienteDAO pc = new PessoaClienteDAO();
		pc.inserirCliente(p);
		
		
		

	}

}
