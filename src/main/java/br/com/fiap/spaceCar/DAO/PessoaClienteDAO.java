package br.com.fiap.spaceCar.DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.spaceCar.model.PessoaCliente;

public class PessoaClienteDAO extends Repository {
	public static int retornarTamanho() {
        String sql = "select SQ_SPC_USUARIO.nextval from dual";
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
	
	public static void inserirCliente(PessoaCliente p){
		String SQL = "INSERT INTO T_SPC_USUARIO (cd_usuario,nm_completo,ds_genero,dt_nascimento,ds_email,ds_senha,nr_cpf) values (?,?,?,?,?,?,?)";
		CallableStatement cs = null;
		try {
            cs = getConnection().prepareCall(SQL);
            cs.setInt(1, retornarTamanho() );
            cs.setString(2,p.getNome());
            cs.setString(3,p.getSexo());
            cs.setDate(4, Date.valueOf(p.getDataNasc()));
            cs.setString(5,p.getEmail() );
            cs.setString(6,p.getSenha());
            cs.setString(7,p.getCpf());
           
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
	
	
//	public static List<PessoaCliente> retornarPessoas(){
//		List<PessoaCliente> lista = new ArrayList<>();
//		String sql = "SELECT\n"+
//				"    cd_usuario,\n"+
//				"    nm_completo,\n"+
//				"    ds_genero,\n"+
//				"    dt_nascimento,\n"+
//				"    ds_email,\n"+
//				"    ds_senha,\n"+
//				"    nr_cpf\n"+
//				"FROM\n"+
//				"    t_spc_usuario;";
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//		try {
//
//			ps = getConnection().prepareStatement(sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				String nomeCompleto = rs.getString("nm_completo");
//				String tipoGenero = rs.getString("ds_genero");
//				Date nascimento = rs.getDate("dt_nascimento");
//				String email = rs.getString("ds_email");
//				String senha = rs.getString("ds_senha");
//				String cpf = rs.getString("nr_cpf");
//				lista.add(new PessoaCliente(nomeCompleto,tipoGenero,nascimento,email,senha,cpf));
//			}
//
//			return lista;
//
//		} catch (SQLException e) {
//			System.out.println("Erro na execu��o do SQL: " + e.getMessage());
//		} finally {
//			try {
//				if (ps != null)
//					ps.close();
//				if (rs != null)
//					rs.close();
//			} catch (SQLException e) {
//				System.out.println("Erro ao tentar fechar o Statment ou o ResultSet");
//			}
//			if (Repository.connection != null)
//				Repository.closeConnection();
//		}
//
//		return lista;
//	
//	}
	
}
