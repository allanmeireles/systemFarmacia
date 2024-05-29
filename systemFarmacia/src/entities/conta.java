package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DBExeception;

public class conta {
	private int idConta;
	private int idCliente;
	private int idPedido;
	private int matricula;
	private double valor;
	
	public conta() {
		
	}

	public conta(int idPedido, double valor) {
		this.idPedido = idPedido;
		this.valor = valor;
	}

	public conta(int idConta, int idCliente) {
		this.idConta = idConta;
		this.idCliente = idCliente;
	}
	
	public void gerarConta(Connection comn) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			st = comn.prepareStatement("INSERT INTO (idPedido, valor) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, idPedido);
			st.setDouble(2, valor);
			st.executeUpdate();
			
			rs = st.getGeneratedKeys();
			
			if(rs.next()) {
				int idPedidoGerado = rs.getInt(1);
				System.out.println("\nId conta:" + idPedidoGerado);
			}
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public void pagarConta(Connection comn) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = comn.prepareStatement("UPDATE conta idCliente = ?, Status = 'pago' WHERE idConta");
			st.setInt(1, idCliente);
			st.setInt(2, idConta);
			st.executeUpdate(); 
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public void mostrarConta(Connection comn) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			st = comn.prepareStatement("UPDATE conta matriculaFunc = ? WHERE idConta = ?");
			st.setInt(1, matricula);
			st.setInt(2, idConta);
			st.executeUpdate();
			
			st = comn.prepareStatement("SELECT * FROM conta WHERE idConta = ?");
			st.setInt(1, idConta);
			st.executeUpdate();
			
			while(rs.next()) {
				System.out.println("\n ID: " + rs.getInt(idConta) + 
									"\n Clienete: " + rs.getInt(idCliente) + 
									"\n Status: "  + rs.getString("Status"));
				
			}
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
}
