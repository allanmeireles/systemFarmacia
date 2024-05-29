package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DBExeception;

public class funcionario {
	private int matriculaFunc;
	private String nome;
	
	public funcionario () {
		
	}

	public funcionario(int matriculaFunc, String nome) {
		this.matriculaFunc = matriculaFunc;
		this.nome = nome;
	}
	
	public void adicionarcli(Connection comn) {
		PreparedStatement st = null;
		
		try {
				st = comn.prepareStatement("INSERT INTO funcionario (nome) VALUES (?))");
				
				st.setString(1, nome);
				st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public void apresentarCli(Connection comn) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = comn.prepareStatement("SELECT * FROM funcionario");
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				System.out.println("Registro: " + rs.getInt(matriculaFunc) + "Nome: " + rs.getString("nome"));
			}
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
	}
	
	public void excluirCli(Connection comn) {
		PreparedStatement st = null;
		try {
			st = comn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
			st.setInt(1, matriculaFunc);
			st.executeUpdate();
		}
		catch(SQLException e){
			throw new DBExeception(e.getMessage());
		}
		
	}
	
	

}
