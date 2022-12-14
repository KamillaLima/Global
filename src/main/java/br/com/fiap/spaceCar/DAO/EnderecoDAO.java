package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Endereco;

public class EnderecoDAO extends Repository {

	/**
	 * Retorna um número inteiro o qual será id de um endereço
	 * 
	 * @return -- número inteiro
	 */
	public static int retornarId() {
		String sql = "select SQ_SPC_ENDERECO.nextval from dual";
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
	 * Método para inserir um endereço
	 * 
	 * @param end -- classe Endereço
	 * @return id do endereço cadastrado
	 */
	public static Endereco inserirEndereco(Endereco end) {
		String sql = "INSERT INTO t_spc_endereco (cd_endereco,ds_logradouro,ds_bairro,sg_estado,ds_cidade,ds_complemento,nr_numero\n"
				+ ") VALUES (\n" + "    :v0,\n" + "    :v1,\n" + "    :v2,\n" + "    :v3,\n" + "    :v4,\n"
				+ "    :v5,\n" + "    :v6\n" + ")";

		CallableStatement cs = null;
		Endereco ende = null;
		try {
			int id = retornarId();
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, end.getLogradouro());
			cs.setString(3, end.getBairro());
			cs.setString(4, end.getEstado());
			cs.setString(5, end.getCidade());
			cs.setString(6, end.getComplemento());
			cs.setInt(7, end.getNumero());
			cs.executeUpdate();
			ende = new Endereco(end.getLogradouro(), end.getNumero(),
					end.getBairro(), end.getCidade(), end.getEstado());
			ende.setId(id);
		} catch (SQLException e) {
			System.out.println("Erro na execução do SQL" + e.getMessage());
		} finally {
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
		return ende;
	}

	/**
	 * Relacionar um endereço com um cliente
	 * 
	 * @param codigoEndereco
	 * @param codigoCliente
	 */
	public static void inserirEndCliente(int codigoEndereco, int codigoCliente) {
		String sql = "INSERT INTO t_spc_endereco_usuario (\n" + "    cd_usuario,\n" + "    cd_endereco\n"
				+ ") VALUES (\n" + "    :v0,\n" + "    :v1\n" + ")";
		CallableStatement cs = null;
		try {
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, codigoCliente);
			cs.setInt(2, codigoEndereco);
			cs.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro na execução do SQL" + e.getMessage());
		} finally {
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

	}

	/**
	 * Relacionar um endereço com uma oficina
	 * 
	 * @param codigoEndereco
	 * @param codigoOficina
	 */
	public static void inserirEnderecoOficina(int codigoEndereco, int codigoOficina) {
		String sql = "INSERT INTO t_spc_endereco_oficina (\n" + "    cd_oficina,\n" + "    cd_endereco\n"
				+ ") VALUES (\n" + "    :v0,\n" + "    :v1\n" + ")";
		CallableStatement cs = null;
		try {
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, codigoOficina);
			cs.setInt(2, codigoEndereco);
			cs.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro na execução do SQL" + e.getMessage());
		} finally {
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

	}

	public static List<Endereco> getAllEnderecos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Endereco> retorno = new ArrayList<>();
		String sql = "SELECT * FROM T_SPC_ENDERECO";
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt("cd_endereco");
					String logra = rs.getString("ds_logradouro");
					String bairro = rs.getString("ds_bairro");
					String estado = rs.getString("sg_estado");
					String cidade = rs.getString("ds_cidade");
					String complemento = rs.getString("ds_complemento");
					int numero = rs.getInt("nr_numero");

					Endereco end = new Endereco(logra, numero, bairro, cidade, estado);
					end.setComplemento(complemento);
					end.setId(id);
					System.out.println(end);
					retorno.add(end);
				}
			} else {
				System.out.println("Não há usuários cadastrados.");
			}
		} catch (SQLException e) {
			System.out.println("ERRO AO EXECUTAR O SQL: " + e.getMessage());
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
		return retorno;
	}
}
