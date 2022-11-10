package br.com.fiap.spaceCar.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Agendamento;
import br.com.fiap.spaceCar.model.PessoaCliente;

public class AgendamentoDAO extends Repository {

	
	/**
	 * Método para retornar um id de um agendamento
	 * 
	 * @return número inteiro que será o id de um agendamento
	 */
	public static int retornarId() {
		String sql = "select SQ_SPC_AGENDAMENTO.nextval from dual";
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
	
	/** Pega todos os registros de agendamento do nosso sistema
	 * 
	 * @return uma arrayList de Agendamento, que representa todos os agendamentos do nosso BD.
	 * @author Jefferson
	 */
	public static List<Agendamento> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM T_SPC_AGENDAMENTO";
		List<Agendamento> retorno = new ArrayList<>();
			
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while(rs.next()) {
					System.out.println("TABELA TABELA AGENDAMENTO ENCONTRADA.");
					int cdUser = rs.getInt("CD_USUARIO");
					int cdOficina = rs.getInt("CD_OFICINA");
					int cdAgendamento = rs.getInt("CD_AGENDAMENTO");
					LocalDate dataInicio = rs.getDate("DT_AGEND_INICIAL").toLocalDate();
					LocalDate dataFim = rs.getDate("DT_AGEND_FINAL").toLocalDate();
					
					Agendamento agend = new Agendamento(cdAgendamento, cdUser, cdOficina, dataInicio, dataFim);
					retorno.add(agend);
					
				}
			}else {
				System.out.println("Não existem itens nessa tabela");
				return retorno;
			}
		} catch (SQLException e) {
			System.out.println("Houve um erro ao se conectar com o banco: " + e.getMessage());
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statement " + e.getMessage());
			}
			if (Repository.connection != null) {
				Repository.closeConnection();
			}
		}
		
		for (Agendamento agendamento : retorno) {
			System.out.println(agendamento);
		}
		
		return retorno;
	}	
	
	
	/**
	 * Método capaz de puxar as informações de um agendamento através de um id
	 * @param id
	 * @return objeto agendamento
	 */
	public static Agendamento procurarAgendamentoById(int id) {
		String sql = "SELECT\n"+
				"    cd_agendamento,\n"+
				"    cd_usuario,\n"+
				"    cd_oficina,\n"+
				"    dt_agend_inicial,\n"+
				"    dt_agend_final\n"+
				"FROM\n"+
				"    t_spc_agendamento Where cd_agendamento=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Agendamento agendamento = new Agendamento();
		try {

			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					agendamento.setCdAgendamento(rs.getInt("cd_agendamento"));
					agendamento.setCdUsuario(rs.getInt("cd_usuario"));
					agendamento.setCdOficina(rs.getInt("cd_oficina"));
					agendamento.setDtHorarioInicio(rs.getDate("dt_agend_inicial").toLocalDate());
					agendamento.setDtHorarioFim(rs.getDate("dt_agend_final").toLocalDate());
					
				}
				return agendamento;
			} else {
				System.out.println("Não existem agendamento(s) com esse id");
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
	
	/**
	 * Método para buscar todos os agendamentos de uma oficina
	 * @param id -- id da oficina
	 * @return Lista com todas as informações dos agendamentos de uma oficina
	 */
	public static List<Agendamento> procurarAgendamentoByIdOficina(int id) {
		String sql = "SELECT\n"+
				"    cd_agendamento,\n"+
				"    cd_usuario,\n"+
				"    cd_oficina,\n"+
				"    dt_agend_inicial,\n"+
				"    dt_agend_final\n"+
				"FROM\n"+
				"    t_spc_agendamento where cd_oficina=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Agendamento> agendamentos = new ArrayList<>();
		try {

			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					agendamentos.add(new Agendamento(rs.getInt("cd_agendamento"),rs.getInt("cd_usuario"),rs.getInt("cd_oficina"),rs.getDate("dt_agend_inicial").toLocalDate(),rs.getDate("dt_agend_final").toLocalDate()));
					
				}
				return agendamentos;
			} else {
				System.out.println("Não existem agendamento(s) com esse id");
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
		return agendamentos;
	}
	
	
	/**
	 * Método para listar todos os agendamentos de um cliente atráves do id dele
	 * @param id -- id do cliente
	 * @return lista com todos os agendamentos que um cliente tem
	 */
	public static List<Agendamento> procurarAgendamentoByIdPessoaCliente(int id) {
		String sql = "SELECT\n"+
				"    cd_agendamento,\n"+
				"    cd_usuario,\n"+
				"    cd_oficina,\n"+
				"    dt_agend_inicial,\n"+
				"    dt_agend_final\n"+
				"FROM\n"+
				"    t_spc_agendamento where cd_usuario=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Agendamento> agendamentos = new ArrayList<>();
		try {

			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					agendamentos.add(new Agendamento(rs.getInt("cd_agendamento"),rs.getInt("cd_usuario"),rs.getInt("cd_oficina"),rs.getDate("dt_agend_inicial").toLocalDate(),rs.getDate("dt_agend_final").toLocalDate()));
					
				}
				return agendamentos;
			} else {
				System.out.println("Não existem agendamento(s) com esse id");
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
		return agendamentos;
	}
	
	
	/** Método que salva um agendamento no banco de dados, retorna o objeto salvo no banco de dados.
	 * 
	 * @param a -- Objeto agendamento o qual será salvo em nosso DB.
	 * @return objeto salvo no DB, com o ID setado.
	 * @author Jefferson
	 */
	public static Agendamento save(Agendamento a) {
		PreparedStatement ps = null;
		Agendamento resp = null;
		String sql = "INSERT INTO T_SPC_AGENDAMENTO (CD_AGENDAMENTO, CD_USUARIO, CD_OFICINA, DT_AGEND_INICIAL, DT_AGEND_FINAL)"
				+ "VALUES (?,?,?,?,?)";
		try {
			ps = getConnection().prepareStatement(sql);
			int id = retornarId();
			ps.setInt(1, id);
			ps.setInt(2, a.getCdUsuario());
			ps.setInt(3, a.getCdOficina());
			ps.setDate(4, Date.valueOf(a.getDtHorarioInicio()));
			ps.setDate(5, Date.valueOf(a.getDtHorarioFim()));
			ps.executeQuery();
			resp = new Agendamento(id, a.getCdUsuario(), a.getCdOficina(), a.getDtHorarioInicio(), a.getDtHorarioFim());
		} catch (SQLException e) {
			System.out.println("Falha ao executar o statement: " + e.getMessage());
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
			}
			if (Repository.connection != null)
				Repository.closeConnection();
		}
		return resp;
	}
	
}
