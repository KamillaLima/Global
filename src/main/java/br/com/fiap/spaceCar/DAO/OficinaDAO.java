package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Endereco;
import br.com.fiap.spaceCar.model.Oficina;

public class OficinaDAO extends Repository {

	/**
	 * Método para retornar o valor que será o id da oficina
	 * 
	 * @return valor inteiro que será o id
	 */
	public static int retornarId() {
		String sql = "select SQ_SPC_OFICINA.nextval from dual";
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
	 * Método para inserir uma nova oficina
	 * 
	 * @param o - Classe oficina com todos os atributos devidamente preenchidos
	 * @return objeto oficina
	 */
	public static Oficina inserirOficina(Oficina o) {
		String sql = "INSERT INTO t_spc_oficina (cd_oficina,nm_oficina,ds_email,ds_senha,nr_cnpj,ds_oficina,nr_ddd,nr_telefone\n"
				+ ") VALUES (\n" + "    :v0,\n" + "    :v1,\n" + "    :v2,\n" + "    :v3,\n" + "    :v4,\n"
				+ "    :v5,\n" + "    :v6,\n" + "    :v7\n" + ")";

		CallableStatement cs = null;
		System.out.println(o);
		
		Oficina retorno = null;
		try {
			int id = retornarId();
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, o.getNome());
			cs.setString(3, o.getEmail());
			cs.setString(4, o.getSenha());
			cs.setString(5, o.getCnpj());
			cs.setString(6, o.getDescricao());
			cs.setString(7, o.getDdd());
			cs.setString(8, o.getTelefone());
			cs.executeUpdate();

			retorno = new Oficina(id, o.getNome(), o.getEndereco(), 
					o.getDdd(), o.getTelefone(), 
					o.getEmail(), o.getSenha(), 
					o.getCnpj(), o.getDescricao());
			

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
		
		if (o.getEndereco() != null) {
			Endereco ende = EnderecoDAO.inserirEndereco(o.getEndereco());
			retorno.setEndereco(ende);
			EnderecoDAO.inserirEnderecoOficina(retorno.getEndereco().getId(), retorno.getId());
		}
		return retorno;

	}

	/**
	 * Método para listar todas as oficinas existentes
	 * 
	 * @return lista com todas as oficinas existentes
	 */
	public static List<Oficina> verOficinas() {
		List<Oficina> oficinas = new ArrayList<>();
		String sql = "SELECT\n" + "    cd_oficina,\n" + "    nm_oficina,\n" + "    ds_email,\n" + "    ds_senha,\n"
				+ "    nr_cnpj,\n" + "    ds_oficina,\n" + "    nr_ddd,\n" + "    nr_telefone\n" + "FROM\n"
				+ "    t_spc_oficina";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt("cd_oficina");
					String nome = rs.getString("nm_oficina");
					String email = rs.getString("ds_email");
					String senha = rs.getString("ds_senha");
					String cnpj = rs.getString("nr_cnpj");
					String descricao = rs.getString("ds_oficina");
					String ddd = rs.getString("nr_ddd");
					String telefone = rs.getString("nr_telefone");
					
					
					oficinas.add(new Oficina(id, nome, null, ddd, telefone, email, senha, cnpj, descricao));
				}
			} else {
				System.out.println("Nennhuma oficina foi cadastrada");
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

		return oficinas;

	}

	/**
	 * Puxa todas as informações de uma oficina através do id da mesma
	 * 
	 * @param id -- Id da oficina que você gostaria das informações
	 * @return objeto oficina preenchido
	 */
	public static Oficina procurarOficinaId(int id) {
		String sql = "SELECT cd_oficina,nm_oficina,ds_email,ds_senha,nr_cnpj,ds_oficina,nr_ddd,nr_telefone FROM t_spc_oficina WHERE cd_oficina = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Oficina oficina = new Oficina();
		try {

			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					oficina.setId(rs.getInt("cd_oficina"));
					oficina.setNome(rs.getString("nm_oficina"));
					oficina.setEmail(rs.getString("ds_email"));
					oficina.setSenha(rs.getString("ds_senha"));
					oficina.setCnpj(rs.getString("nr_cnpj"));
					oficina.setDescricao(rs.getString("ds_oficina"));
					oficina.setDdd(rs.getString("nr_ddd"));
					oficina.setTelefone(rs.getString("nr_telefone"));
				}
				return oficina;
			} else {
				System.out.println("Não existem oficina(s) com esse id");
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
