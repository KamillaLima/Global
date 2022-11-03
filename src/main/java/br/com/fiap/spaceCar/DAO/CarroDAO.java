package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.spaceCar.model.Carro;

public class CarroDAO extends Repository{

	public static int retornarTamanho() {
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

public static void inserirCarro(int codigoCliente,Carro c) {
	String sql = "INSERT INTO t_spc_carros (cd_carro,cd_usuario,ds_placa,nr_ano_fabricacao,nr_ano_modelo,ds_chassi,ds_marca,nr_portas,\n"+
			"    nr_passageiros,ds_combustivel,nr_potencia,nr_cilindradas,ds_motor,nr_km_rodado,ds_cambio,ds_problema\n"+
			") VALUES (\n"+
			"    :v0,\n"+
			"    :v1,\n"+
			"    :v2,\n"+
			"    :v3,\n"+
			"    :v4,\n"+
			"    :v5,\n"+
			"    :v6,\n"+
			"    :v7,\n"+
			"    :v8,\n"+
			"    :v9,\n"+
			"    :v10,\n"+
			"    :v11,\n"+
			"    :v12,\n"+
			"    :v13,\n"+
			"    :v14,\n"+
			"    :v15\n"+
			");";
	
	CallableStatement cs = null;
	try {
        cs = getConnection().prepareCall(sql);
        cs.setInt(1, retornarTamanho() );
        cs.setInt(2,codigoCliente);
        cs.setString(3,c.getPlaca());
        cs.setDate(4, Date.valueOf(c.getAnoFabricacao()));
        cs.setDate(5,Date.valueOf(c.getAnoModelo()));
        cs.setString(6,c.getChassi());
        cs.setString(7,c.getMarca());
        cs.setInt(8,c.getPortas());
        cs.setInt(9, c.getPassageiros());
        cs.setString(10,c.getTipoCombustivel());
        cs.setInt(11,c.getPotencia());
        cs.setInt(12,c.getCilindradas());
        cs.setFloat(13,c.getMotor());
        cs.setInt(14, c.getKmRodado());
        cs.setString(15, c.getTipoCambio());
        cs.setString(16, c.getDescricaoProblema());
        cs.executeUpdate();
        
        
    }catch (SQLException e) {
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
