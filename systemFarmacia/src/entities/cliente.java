package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DBExeception;

public class cliente {
	private int idCliente;
	private String nome; 
	
	public cliente() {
		
	}

	public cliente(int idCliente, String nome) {
		this.idCliente = idCliente;
		this.nome = nome;
	}
	
	public void adicionarcli(Connection comn) {
		PreparedStatement st = null;
		
		try {
				st = comn.prepareStatement("INSERT INTO cliente (nome) VALUES (?))");
				
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
			st = comn.prepareStatement("SELECT * FROM cliente");
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				System.out.println(" ID:" + rs.getInt(idCliente) + "Nome: " + rs.getString("nome"));
			}
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
	}
	
	public void excluitCli(Connection comn) {
		PreparedStatement st = null;
		try {
			st = comn.prepareStatement("DELETE FROM cliente WHERE id = ?");
			st.setInt(1, idCliente);
			st.executeUpdate();
		}
		catch(SQLException e){
			throw new DBExeception(e.getMessage());
		}
		
	}
	
	
}
