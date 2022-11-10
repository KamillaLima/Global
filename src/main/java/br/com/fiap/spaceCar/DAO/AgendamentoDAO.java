package br.com.fiap.spaceCar.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Agendamento;

public class AgendamentoDAO extends Repository {

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

	/** Pega todos os Agendamentos presentes na tabela agendamento
	 * 
	 * @return ArrayList com os Agendamentos
	 * @author Jefferson
	 */
	public static List<Agendamento> getAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO";
		List<Agendamento> retorno = new ArrayList<>();
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
					Agendamento agend = new Agendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio, dtFim);
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

	/** Retorna um AGENDAMENTO através do ID dado.
	 * 
	 * @param idAgendamento -- Vindo da URI da requisição
	 * @return O agendamento vindo do id referente a URI.
	 * @author Jefferson
	 * 	 */
	public static Agendamento getById(int idAgendamento) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO\r\n"
				+ "WHERE CD_AGENDAMENTO = ?";
		Agendamento retorno = null;
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
					retorno = new Agendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio, dtFim);
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

	/** Salvar um Agendamento em nosso DB
	 * 
	 * @param a -- Objeto Agendamento vindo do JSON do front.
	 * @return Agendamento salvo, com o CD_Agendamento.
	 * @author Jefferson
	 */
	public static Agendamento save (Agendamento a) {
		PreparedStatement ps = null;
		System.out.println(a);
		String dtInicio = "TO_DATE('" + a.getDtHorarioInicio().getDayOfMonth() +"/" + a.getDtHorarioInicio().getMonthValue() + "/" + a.getDtHorarioInicio().getYear()+ " " 
		+ a.getDtHorarioInicio().getHour() + ":" + a.getDtHorarioInicio().getMinute()+ "')"  ;
		String dtFim = "TO_DATE('" + a.getDtHorarioFim().getDayOfMonth() +"/" + a.getDtHorarioFim().getMonthValue() + "/" + a.getDtHorarioFim().getYear()+ " " 
				+ a.getDtHorarioFim().getHour() + ":" + a.getDtHorarioFim().getMinute()+ "')"  ;
		String sql = "\r\n"
				+ "INSERT INTO T_SPC_AGENDAMENTO (CD_AGENDAMENTO, CD_USUARIO, CD_OFICINA,\r\n"
				+ "DT_AGEND_INICIAL, DT_AGEND_FINAL)\r\n"
				+ "VALUES (?,?,?," + dtInicio + "," + dtFim + ")";
		Agendamento retorno = null;
		try {
			ps = getConnection().prepareStatement(sql);
			int cdAgendamento = retornarId();
			int cdUsuario = a.getCdUsuario();
			int cdOfocina = a.getCdOficina();
			retorno = new Agendamento(cdAgendamento, cdUsuario, cdOfocina, a.getDtHorarioInicio(), a.getDtHorarioFim());
		} catch (SQLException e) {
			System.out.println("Erro ao executar o statement: " + e.getMessage());
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
		return retorno;
	}
	
	/** Pega todos os agendamentos de um usuário.
	 * 
	 * @param idUser -- esperado que venha da URI da requisição.
	 * @return List de Agendamentos.
	 * @author Jefferson
	 */
	public static List<Agendamento> getByUserId(int idUser){
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO\r\n"
				+ "WHERE CD_USUARIO = ?";
		List<Agendamento> retorno = new ArrayList<>();
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
					Agendamento agend  = new Agendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio, dtFim);
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
	
	
	/** Pega todos os agendamentos de uma Oficina.
	 * 
	 * @param idOfc -- esperado que venha da URI da requisição.
	 * @return List de Agendamentos.
	 * @author Jefferson
	 */
	public static List<Agendamento> getByOficinaId(int idOfc){
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CD_AGENDAMENTO, CD_USUARIO,\r\n" + "CD_OFICINA,\r\n"
				+ "TO_CHAR(DT_AGEND_INICIAL, 'DD MM YYYY HH24 MM') as inicio, \r\n"
				+ "TO_CHAR(DT_AGEND_FINAL, 'DD MM YYYY HH24 MM') as fim \r\n" + "FROM T_SPC_AGENDAMENTO\r\n"
				+ "WHERE CD_OFICINA = ?";
		List<Agendamento> retorno = new ArrayList<>();
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
					Agendamento agend  = new Agendamento(cdAgendamento, cdUsuario, cdOficina, dtInicio, dtFim);
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
}
