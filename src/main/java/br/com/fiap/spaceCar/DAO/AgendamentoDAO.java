package br.com.fiap.spaceCar.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Agendamento;

public class AgendamentoDAO extends Repository {

	
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
			while (rs.next()) {
				int idUser = rs.getInt("CD_USUARIO");
				int idOfc = rs.getInt("CD_OFICINA");
				LocalDate horario = rs.getDate("dt_agendamento").toLocalDate();

				Agendamento agend = new Agendamento(idUser, idOfc, horario);
				retorno.add(agend);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao pegar os agendamentos do programa: " + e.getMessage());
		} finally {
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
}
