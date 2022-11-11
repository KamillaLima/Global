package br.com.fiap.spaceCar.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.MarcarAgendamento;

public class MarcarAgendamentoDAO extends Repository {

	
	/** Pega uma string e a transforma em data
	 * 
	 * @param data -- String no formato: DD MM YYYY HH24 MI
	 * @return LocalDateTime
	 * @author Jefferson
	 */
	public static LocalDateTime converterData(String data) {
		String[] arrayListStr = data.split(" ");
		int dia = Integer.parseInt(arrayListStr[0]);
		int mes = Integer.parseInt(arrayListStr[1]);
		int ano = Integer.parseInt(arrayListStr[2]);
		int hora = Integer.parseInt(arrayListStr[3]);
		int minuto = Integer.parseInt(arrayListStr[4]);
		LocalDateTime dt = LocalDateTime.of(ano, mes, dia, hora, minuto);
		return dt;
	}

	/**
	 * Retorna um número inteiro o qual será id de um Agendamento
	 * 
	 * @return -- número inteiro
	 * @author Jefferson
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

	/**
	 * Pega todos os Agendamentos presentes na tabela agendamento
	 * 
	 * @return ArrayList com os Agendamentos
	 * @author Jefferson
	 */
	public static List<MarcarAgendamento> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO";
		List<MarcarAgendamento> retorno = new ArrayList<>();
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int cdAgendamento = rs.getInt("CD_AGENDAMENTO");
					int cdUsuario = rs.getInt("CD_USUARIO");
					int cdOficina = rs.getInt("CD_OFICINA");
					LocalDateTime dtInicio = converterData(rs.getString("inicio"));
					LocalDateTime dtFim = converterData(rs.getString("fim"));
					MarcarAgendamento agend = new MarcarAgendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio,
							dtFim);
					retorno.add(agend);
				}
			} else {
				System.out.println("Não foi encontrado nenhum dado na tabela Agendamento.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível executar o Statement: " + e.getMessage());
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

	/**
	 * Retorna um AGENDAMENTO através do ID dado.
	 * 
	 * @param idAgendamento -- Vindo da URI da requisição
	 * @return O agendamento vindo do id referente a URI.
	 * @author Jefferson
	 */
	public static MarcarAgendamento getById(int idAgendamento) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO\r\n"
				+ "WHERE CD_AGENDAMENTO = ?";
		MarcarAgendamento retorno = null;
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, idAgendamento);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int cdAgendamento = rs.getInt("CD_AGENDAMENTO");
					int cdUsuario = rs.getInt("CD_USUARIO");
					int cdOficina = rs.getInt("CD_OFICINA");
					LocalDateTime dtInicio = converterData(rs.getString("inicio"));
					LocalDateTime dtFim = converterData(rs.getString("fim"));
					retorno = new MarcarAgendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio, dtFim);
				}
			} else {
				System.out.println("Não foi encontrado nenhum agendamento com esse ID");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível executar o statement: " + e.getMessage());
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

	/**
	 * Salvar um Agendamento em nosso DB
	 * 
	 * @param a -- Objeto Agendamento vindo do JSON do front.
	 * @return Agendamento salvo, com o CD_Agendamento.
	 * @author Jefferson
	 */
	public static MarcarAgendamento save(MarcarAgendamento a) {
		PreparedStatement ps = null;
		System.out.println(a);
		int cdAgendamento = retornarId();
		String dtInicio = "TO_DATE('" + a.getDtHorarioInicio().getDayOfMonth() + "/"
				+ a.getDtHorarioInicio().getMonthValue() + "/" + a.getDtHorarioInicio().getYear() + " "
				+ a.getDtHorarioInicio().getHour() + ":" + a.getDtHorarioInicio().getMinute()
				+ "', 'DD/MM/YYYY HH24:MI')";
		String dtFim = "TO_DATE('" + a.getDtHorarioFim().getDayOfMonth() + "/" + a.getDtHorarioFim().getMonthValue()
				+ "/" + a.getDtHorarioFim().getYear() + " " + a.getDtHorarioFim().getHour() + ":"
				+ a.getDtHorarioFim().getMinute() + "', 'DD/MM/YYYY HH24:MI')";
		String sql = "\r\n" + "INSERT INTO T_SPC_AGENDAMENTO (CD_AGENDAMENTO, CD_USUARIO, CD_OFICINA,\r\n"
				+ "DT_AGEND_INICIAL, DT_AGEND_FINAL)\r\n" + "VALUES (" + cdAgendamento + "," + a.getCdUsuario() + ","
				+ a.getCdOficina() + "," + dtInicio + "," + dtFim + ")";
		MarcarAgendamento retorno = null;
		try {
			Statement st = getConnection().createStatement();
			st.executeUpdate(sql);
			int cdUsuario = a.getCdUsuario();
			int cdOfocina = a.getCdOficina();
			retorno = new MarcarAgendamento(cdAgendamento, cdUsuario, cdOfocina, a.getDtHorarioInicio(),
					a.getDtHorarioFim());
		} catch (SQLException e) {
			System.out.println("Erro ao executar o statement: " + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
			}
			if (Repository.connection != null)
				Repository.closeConnection();
		}
		return retorno;
	}

	/**
	 * Pega todos os agendamentos de um usuário.
	 * 
	 * @param idUser -- esperado que venha da URI da requisição.
	 * @return List de Agendamentos.
	 * @author Jefferson
	 */
	public static List<MarcarAgendamento> getByUserId(int idUser) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO\r\n"
				+ "WHERE CD_USUARIO = ?";
		List<MarcarAgendamento> retorno = new ArrayList<>();
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int cdAgendamento = rs.getInt("CD_AGENDAMENTO");
					int cdUsuario = rs.getInt("CD_USUARIO");
					int cdOficina = rs.getInt("CD_OFICINA");
					LocalDateTime dtInicio = converterData(rs.getString("inicio"));
					LocalDateTime dtFim = converterData(rs.getString("fim"));
					MarcarAgendamento agend = new MarcarAgendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio,
							dtFim);
					retorno.add(agend);
				}
			} else {
				System.out.println("Não foi encontrado nenhum agendamento com esse ID");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível executar o statement: " + e.getMessage());
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

	/**
	 * Pega todos os agendamentos de uma Oficina.
	 * 
	 * @param idOfc -- esperado que venha da URI da requisição.
	 * @return List de Agendamentos.
	 * @author Jefferson
	 */
	public static List<MarcarAgendamento> getByOficinaId(int idOfc) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO\r\n"
				+ "WHERE CD_OFICINA = ?";
		List<MarcarAgendamento> retorno = new ArrayList<>();
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, idOfc);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int cdAgendamento = rs.getInt("CD_AGENDAMENTO");
					int cdUsuario = rs.getInt("CD_USUARIO");
					int cdOficina = rs.getInt("CD_OFICINA");
					LocalDateTime dtInicio = converterData(rs.getString("inicio"));
					LocalDateTime dtFim = converterData(rs.getString("fim"));
					MarcarAgendamento agend = new MarcarAgendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio,
							dtFim);
					retorno.add(agend);
				}
			} else {
				System.out.println("Não foi encontrado nenhum agendamento com esse ID");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível executar o statement: " + e.getMessage());
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

	
	/** Exclui um agendamento no Banco de dados;
	 * 
	 * @param id -- Vindo da requisição da URI
	 * @return boolean: True se encontrar algo com esse ID e false caso não encontre nada.
	 * @author Jefferson
	 */
	public static boolean excluir(int id) {
		boolean retorno = false;
		PreparedStatement ps = null;
		MarcarAgendamento agend = MarcarAgendamentoDAO.getById(id);
		String sql = "DELETE FROM T_SPC_AGENDAMENTO WHERE CD_AGENDAMENTO = ?";
		if (agend == null) {
			System.out.println("Nenhum agendamento encontrado com esse ID");
			return retorno;
		} else {
			try {
				ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
				retorno = true;
			} catch (SQLException e) {
				System.out.println("Não foi possível executar o statement: " + e.getMessage());
			} finally {
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println("Erro ao fechar o Prepared Statement: " + e.getMessage());
					}
			}

		}
		return retorno;
	}
}
