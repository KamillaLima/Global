package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.spaceCar.model.Carro;

public class CarroDAO extends Repository {

	/**
	 * Método para retornar um id de um carro
	 * 
	 * @return número inteiro que será o id de um carro
	 */
	public static int retornarId() {
		String sql = "select SQ_SPC_CARROS.nextval from dual";
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
	 * Método para cadastrar um carro
	 * 
	 * @param codigoCliente -- código do cliente que irá adicionar um carro
	 * @param c             -- classe Carro
	 * @return objeto carro
	 */
	public static Carro inserirCarro(int codigoCliente, Carro c) {
		String sql = "INSERT INTO t_spc_carros (cd_carro,cd_usuario,ds_placa,nr_ano_fabricacao,nr_ano_modelo,ds_chassi,ds_marca,nr_portas,\n"
				+ "    nr_passageiros,ds_combustivel,nr_potencia,nr_cilindradas,ds_motor,nr_km_rodado,ds_cambio,ds_problema\n"
				+ ") VALUES (\n" + "    :v0,\n" + "    :v1,\n" + "    :v2,\n" + "    :v3,\n" + "    :v4,\n"
				+ "    :v5,\n" + "    :v6,\n" + "    :v7,\n" + "    :v8,\n" + "    :v9,\n" + "    :v10,\n"
				+ "    :v11,\n" + "    :v12,\n" + "    :v13,\n" + "    :v14,\n" + "    :v15\n" + ")";

		CallableStatement cs = null;
		Carro retorno = null;
		try {
			int id = retornarId();
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, id);
			cs.setInt(2, codigoCliente);
			cs.setString(3, c.getPlaca());
			cs.setDate(4, Date.valueOf(c.getAnoFabricacao()));
			cs.setDate(5, Date.valueOf(c.getAnoModelo()));
			cs.setString(6, c.getChassi());
			cs.setString(7, c.getMarca());
			cs.setInt(8, c.getPortas());
			cs.setInt(9, c.getPassageiros());
			cs.setString(10, c.getTipoCombustivel());
			cs.setInt(11, c.getPotencia());
			cs.setInt(12, c.getCilindradas());
			cs.setDouble(13, c.getMotor());
			cs.setInt(14, c.getKmRodado());
			cs.setString(15, c.getTipoCambio());
			cs.setString(16, c.getDescricaoProblema());
			cs.executeUpdate();

			retorno = new Carro(id, c.getPlaca(), c.getAnoFabricacao(), c.getAnoModelo(), c.getChassi(), c.getMarca(),
					c.getPortas(), c.getPassageiros(), c.getTipoCombustivel(), c.getPotencia(), c.getCilindradas(),
					c.getMotor(), c.getKmRodado(), c.getTipoCambio(), c.getDescricaoProblema());
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
		return retorno;
	}

	/**
	 * Método para listar todos os carros
	 * 
	 * @return List com todos os carros cadastrados
	 */
	public static List<Carro> buscarCarro() {
		List<Carro> carros = new ArrayList<>();
		String sql = "SELECT\n" + "    cd_carro,\n" + "    cd_usuario,\n" + "    ds_placa,\n"
				+ "    nr_ano_fabricacao,\n" + "    nr_ano_modelo,\n" + "    ds_chassi,\n" + "    ds_marca,\n"
				+ "    nr_portas,\n" + "    nr_passageiros,\n" + "    ds_combustivel,\n" + "    nr_potencia,\n"
				+ "    nr_cilindradas,\n" + "    ds_motor,\n" + "    nr_km_rodado,\n" + "    ds_cambio,\n"
				+ "    ds_problema\n" + "FROM\n" + "    t_spc_carros";

		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String placa = rs.getString("ds_placa");
					LocalDate nr_ano_fabricacao = rs.getDate("nr_ano_fabricacao").toLocalDate();
					LocalDate ano_modelo = rs.getDate("nr_ano_modelo").toLocalDate();
					String chassi = rs.getString("ds_chassi");
					String marca = rs.getString("ds_marca");
					int portas = rs.getInt("nr_portas");
					int passageiros = rs.getInt("nr_passageiros");
					String combustivel = rs.getString("ds_combustivel");
					int potencia = rs.getInt("nr_potencia");
					int cilindradas = rs.getInt("nr_cilindradas");
					double motor = rs.getDouble("ds_motor");
					int km_rodado = rs.getInt("nr_km_rodado");
					String cambio = rs.getString("ds_cambio");
					String problema = rs.getString("ds_problema");
					carros.add(new Carro(placa, nr_ano_fabricacao, ano_modelo, chassi, marca, portas, passageiros,
							combustivel, potencia, cilindradas, motor, km_rodado, cambio, problema));
				}
			} else {
				System.out.println("Nenhum carro foi cadastrado");
			}

			return carros;

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
		return carros;
	}

	/**
	 * Comando que atualiza o os dados de um carro no DB.
	 * 
	 * @param novoCarro -- Objeto carro referente a classe model Carro
	 * @return o Objeto carro salvo no DB.
	 * @author Jefferson
	 */
	public static Carro uptadeCarro(Carro novoCarro) {
		PreparedStatement ps = null;
		Carro retorno = novoCarro;
		String sql = "UPDATE t_spc_carros\r\n" + "SET\r\n" + "    DS_PLACA = ?,\r\n" + "    NR_ANO_FABRICACAO = ?,\r\n"
				+ "    NR_ANO_MODELO = ?,\r\n" + "    DS_CHASSI = ?,\r\n" + "    DS_MARCA = ?,\r\n"
				+ "    NR_PORTAS = ?,\r\n" + "    NR_PASSAGEIROS = ?,\r\n" + "    ds_combustivel = ?,\r\n"
				+ "    nr_potencia = ?,\r\n" + "    nr_cilindradas = ?,\r\n" + "    ds_motor = ?,\r\n"
				+ "    nr_km_rodado = ?,\r\n" + "    ds_cambio = ?,\r\n" + "    ds_problema = ?\r\n" + "WHERE\r\n"
				+ "        cd_carro = ?\r\n" + "";

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, novoCarro.getPlaca());
			ps.setDate(2, Date.valueOf(novoCarro.getAnoFabricacao()));
			ps.setDate(3, Date.valueOf(novoCarro.getAnoModelo()));
			ps.setString(4, novoCarro.getChassi());
			ps.setString(5, novoCarro.getMarca());
			ps.setInt(6, novoCarro.getPortas());
			ps.setInt(7, novoCarro.getPassageiros());
			ps.setString(8, novoCarro.getTipoCombustivel());
			ps.setInt(9, novoCarro.getPotencia());
			ps.setInt(10, novoCarro.getCilindradas());
			ps.setDouble(11, novoCarro.getMotor());
			ps.setInt(12, novoCarro.getKmRodado());
			ps.setString(13, novoCarro.getTipoCambio());
			ps.setString(14, novoCarro.getDescricaoProblema());
			ps.setInt(15, novoCarro.getId());
			ps.executeQuery();
			System.out.println("Uptade concluído.");
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar com o DB : " + e.getMessage());
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
		return retorno;
	}

	/**
	 * Pega o registro do carro baseado na ID do USUÁRIO!
	 * 
	 * @param idUser PK do usuário em nosso DB. (cd_usuario)
	 * @return Objeto Carro.
	 * @author Jefferson
	 */
	public static List<Carro> getByUserId(int idUser) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM T_SPC_CARROS WHERE CD_USUARIO = ?";
		List<Carro> retorno = new ArrayList<>();
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String placa = rs.getString("ds_placa");
					LocalDate nr_ano_fabricacao = rs.getDate("nr_ano_fabricacao").toLocalDate();
					LocalDate ano_modelo = rs.getDate("nr_ano_modelo").toLocalDate();
					String chassi = rs.getString("ds_chassi");
					String marca = rs.getString("ds_marca");
					int portas = rs.getInt("nr_portas");
					int passageiros = rs.getInt("nr_passageiros");
					String combustivel = rs.getString("ds_combustivel");
					int potencia = rs.getInt("nr_potencia");
					int cilindradas = rs.getInt("nr_cilindradas");
					double motor = rs.getDouble("ds_motor");
					int km_rodado = rs.getInt("nr_km_rodado");
					String cambio = rs.getString("ds_cambio");
					String problema = rs.getString("ds_problema");
					Carro car = new Carro(placa, nr_ano_fabricacao, ano_modelo, chassi, marca, portas, passageiros,
							combustivel, potencia, cilindradas, motor, km_rodado, cambio, problema);
					retorno.add(car);
				}
			} else {
				System.out.println("Não foi encontrado nenhum registro com o id dado: " + idUser);
				return retorno;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível executar o comando sql: " + e.getMessage());
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
		return retorno;
	}

	public static Carro getByCarId(int idCar) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM T_SPC_CARROS WHERE CD_CARRO = ?";
		Carro retorno = null;
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, idCar);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt("CD_CARRO");
					String placa = rs.getString("ds_placa");
					LocalDate nr_ano_fabricacao = rs.getDate("nr_ano_fabricacao").toLocalDate();
					LocalDate ano_modelo = rs.getDate("nr_ano_modelo").toLocalDate();
					String chassi = rs.getString("ds_chassi");
					String marca = rs.getString("ds_marca");
					int portas = rs.getInt("nr_portas");
					int passageiros = rs.getInt("nr_passageiros");
					String combustivel = rs.getString("ds_combustivel");
					int potencia = rs.getInt("nr_potencia");
					int cilindradas = rs.getInt("nr_cilindradas");
					double motor = rs.getDouble("ds_motor");
					int km_rodado = rs.getInt("nr_km_rodado");
					String cambio = rs.getString("ds_cambio");
					String problema = rs.getString("ds_problema");
					retorno = new Carro(placa, nr_ano_fabricacao, ano_modelo, chassi, marca, portas, passageiros,
							combustivel, potencia, cilindradas, motor, km_rodado, cambio, problema);
					retorno.setId(id);
				}
			} else {
				System.out.println("Não foi encontrado nenhum registro com o id dado: " + idCar);
				System.out.println(retorno);
				return retorno;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível executar o comando sql: " + e.getMessage());
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
		return retorno;
	}

	// É SÓ PRA TESTAR !!!
	public static void main(String[] args) {
		System.out.println(getByUserId(0));
	}
}
