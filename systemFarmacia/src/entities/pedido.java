package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DBExeception;

public class pedido {
	private int idCliente;
	
	public pedido() {
		
	}

	public pedido(int idCliente) {
		this.idCliente = idCliente;
	}

	public void adicionarPed(Connection comn) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = comn.prepareStatement("INSERT INTO pedido (idCliente) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, idCliente);
			st.executeUpdate(); 
				
			rs = st.getGeneratedKeys();
			
			if(rs.next()) {
				 int idPedidoGerado = rs.getInt(1);
	             System.out.println("\nID do pedido: " + idPedidoGerado); 
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
