package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DBExeception;

public class pedidoMedicamento {
	private int idMedicamento;
	private int idPedido;
	private int quantidade; 
	
	public pedidoMedicamento() {
		
	}

	public pedidoMedicamento(int idMedicamento, int idPedido, int quantidade) {
		this.idMedicamento = idMedicamento;
		this.idPedido = idPedido;
		this.quantidade = quantidade;
	}

	public void addPedidoMedicamento(Connection comn) {
		PreparedStatement st = null;
		
		try {
			st = comn.prepareStatement("INSERT INTO pedmedicamento (idMedicamento,idPedido,quantidade) VALUES (?,?,?)");
			st.setInt(1, idMedicamento);
			st.setInt(2, idPedido);
			st.setInt(3, quantidade);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBExeception(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
