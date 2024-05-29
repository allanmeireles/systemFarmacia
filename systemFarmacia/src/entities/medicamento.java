package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DBExeception;

public class medicamento {
	private int idMedicamento;
	private int matriculaFunc;
	private String nome;
	private int quantidade;
	
	public medicamento() {
		
	}

	public medicamento(int idMedicamento, int matriculaFunc, String nome, int quantidade) {
		this.idMedicamento = idMedicamento;
		this.matriculaFunc = matriculaFunc;
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public void adicionarMed(Connection comn) {
		PreparedStatement st = null; 
		
		try {
			st = comn.prepareStatement("INSERT INTO medicamento (nome,quantidade,matriculaFunc) VALUES (?,?,?)");
			
			st.setString(1, nome);
			st.setInt(2, quantidade);
			st.setInt(3, matriculaFunc);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public void apresentarMed(Connection comn) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = comn.prepareStatement("SELECT medicamento.nome, medicamento.quantidade, medicamento.matriculaFunc AS funcionario " +
										"FROM medicamento " + 
										"LEFT JOIN funcionario ON medicamento.matriculaFunc = funcionario.matriculaFunc");
			while(rs.next()) {
				String func = rs.getString("funcionario");
				System.out.println("ID:" + rs.getInt(idMedicamento) + 
								   "Nome:" + rs.getString(nome) + 
								   "Quantidade:" + rs.getInt(quantidade) + "Funcionário cadastrado: " );
				if (func != null) {
					System.out.println(func);
				} else {
					System.out.println("Deletado");
				}
			} 
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public void excluirMed(Connection comn) {
		PreparedStatement st = null;
		try {
			st = comn.prepareStatement("DELETE FROM medicamento WHERE idMedicamento = ?");
			st.setInt(1, idMedicamento);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public double valorMed(Connection comn) {
		double valor = 0.0;
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			st  = comn.prepareStatement("SELECT quantidade FROM medicamentos WHERE idMedicamento = ? ");
			
			st.setInt(1, idMedicamento);
			
			st.executeUpdate();
			
			while(rs.next()) {
				valor = rs.getDouble(quantidade);
			}
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		return valor;
	}
	
	

}
