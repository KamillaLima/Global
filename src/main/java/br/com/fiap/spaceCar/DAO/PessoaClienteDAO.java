package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.spaceCar.model.Endereco;
import br.com.fiap.spaceCar.model.PessoaCliente;

public class PessoaClienteDAO extends Repository {

	/**
	 * Método que retorna um valor que será utilizado como o id do cliente
	 * 
	 * @return número inteiro que será o id do cliente
	 */
	public static int retornarId() {
		String sql = "select SQ_SPC_USUARIO.nextval from dual";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int retorno = 0;
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				retorno = rs.getInt("NEXTVAL");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar achar a sequência" + e.getMessage());
		}
		return retorno;
	}

	/**
	 * Método para cadas um cliente * @param p - Classe cliente
	 * 
	 * @return um ojbeto cliente
	 */
	public static PessoaCliente inserirCliente(PessoaCliente p) {
		String SQL = "INSERT INTO t_spc_usuario (\n" + "    cd_usuario,\n" + "    nm_completo,\n" + "    ds_genero,\n"
				+ "    dt_nascimento,\n" + "    ds_email,\n" + "    ds_senha,\n" + "    nr_cpf,\n"
				+ "    nr_telefone,\n" + "    nr_ddd\n" + ") VALUES (\n" + "    :v0,\n" + "    :v1,\n" + "    :v2,\n"
				+ "    :v3,\n" + "    :v4,\n" + "    :v5,\n" + "    :v6,\n" + "    :v7,\n" + "    :v8\n" + ")";
		CallableStatement cs = null;
		PessoaCliente retorno = null;
		try {
			int id = retornarId();
			cs = getConnection().prepareCall(SQL);
			cs.setInt(1, id);
			cs.setString(2, p.getNome());
			cs.setString(3, p.getSexo());
			cs.setDate(4, Date.valueOf(p.getDataNasc()));
			cs.setString(5, p.getEmail());
			cs.setString(6, p.getSenha());
			cs.setString(7, p.getCpf());
			cs.setString(8, p.getTelefone());
			cs.setString(9, p.getDdd());
			cs.executeUpdate();

			retorno = new PessoaCliente(id, p.getNome(), p.getDdd(), p.getTelefone(), p.getEmail(),
					p.getSenha(), p.getSexo(),  p.getDataNasc(),p.getCpf());
		} catch (SQLException e) {
			System.out.println("Erro na execução do SQL" + e.getMessage());
		}
		finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statement " + e.getMessage());
			}
			if (Repository.connection != null) {
				Repository.closeConnection();
			}
		}
		
		if (p.getEndereco() != null) {
			Endereco ender = EnderecoDAO.inserirEndereco(p.getEndereco());
			retorno.setEndereco(ender);
			EnderecoDAO.inserirEndCliente(retorno.getEndereco().getId(), retorno.getId());
		}
		
		System.out.println(p.getId());
		return retorno;

	}

	/**
	 * Método para listar todos os clientes presentes no banco de dados
	 * 
	 * @return Lista com todos os clientes e suas devidas informações
	 */
	public static List<PessoaCliente> retornarPessoas() {
		List<PessoaCliente> lista = new ArrayList<>();
		String sql = "SELECT\n" + "    cd_usuario,\n" + "    nm_completo,\n" + "    ds_genero,\n"
				+ "    dt_nascimento,\n" + "    ds_email,\n" + "    ds_senha,\n" + "    nr_cpf,\n"
				+ "    nr_telefone,\n" + "    nr_ddd\n" + "FROM\n" + "    t_spc_usuario";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt("cd_usuario");
					String nomeCompleto = rs.getString("nm_completo");
					String tipoGenero = rs.getString("ds_genero");
					LocalDate nascimento = rs.getDate("dt_nascimento").toLocalDate();
					String email = rs.getString("ds_email");
					String senha = rs.getString("ds_senha");
					String cpf = rs.getString("nr_cpf");
					String telefone = rs.getString("nr_telefone");
					String ddd = rs.getString("nr_ddd");
					lista.add(
							new PessoaCliente(id, nomeCompleto, ddd, telefone, email, senha, tipoGenero, nascimento, cpf));
				}
			} else {
				System.out.println("Nenhum cliente foi cadastrado");
			}

			return lista;

		} catch (SQLException e) {
			System.out.println("Erro na execu��o do SQL: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
			}
			if (Repository.connection != null)
				Repository.closeConnection();
		}

		return lista;

	}
	
	public static PessoaCliente getByEmailSenha(String email, String senha) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		PessoaCliente retorno = null;
		String sql = "SELECT * FROM T_SPC_USUARIO WHERE DS_SENHA = ? AND DS_EMAIL = ?";
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, senha);
			ps.setString(2, email);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
				retorno = procurarPessoaClienteId(rs.getInt("CD_USUARIO")); 
				}
			}else {
				System.out.println("Nenhum usuário com esses dados foram encontrados.");
				return retorno;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar o comando sql: " + e.getMessage());
		}
		return retorno;
	}
	
	
	/**
	 * Método responsável por retornar todas as informações de um cliente através de seu id
	 * @param id-- id do cliente
	 * @return -- objeto cliente
	 */
	public static PessoaCliente procurarPessoaClienteId(int id) {
		String sql = "SELECT cd_usuario,nm_completo,ds_genero,dt_nascimento,ds_email,ds_senha,nr_cpf,nr_telefone,nr_ddd FROM t_spc_usuario WHERE cd_usuario =?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		PessoaCliente pessoaCliente = new PessoaCliente();
		try {

			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					pessoaCliente.setId(rs.getInt("cd_usuario"));
					pessoaCliente.setNome(rs.getString("nm_completo"));
					pessoaCliente.setSexo(rs.getString("ds_genero"));
					pessoaCliente.setDataNasc(rs.getDate("dt_nascimento").toLocalDate());
					pessoaCliente.setEmail(rs.getString("ds_email"));
					pessoaCliente.setSenha(rs.getString("ds_senha"));
					pessoaCliente.setCpf(rs.getString("nr_cpf"));
					pessoaCliente.setTelefone(rs.getString("nr_telefone"));
					pessoaCliente.setDdd(rs.getString("nr_ddd"));
					
				}
				return pessoaCliente;
			} else {
				System.out.println("Não existem cliente(s) com esse id");
			}

		} catch (SQLException e) {
			System.out.println("Erro na execu��o do SQL: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
			}
			if (Repository.connection != null)
				Repository.closeConnection();
		}
		return null;
	}
}
