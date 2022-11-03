package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			end.setId(id);
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
		return end;
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
}
