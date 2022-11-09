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
}
