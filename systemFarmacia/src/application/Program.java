package application;
import entities.cliente; 
import entities.conta;
import entities.funcionario;
import entities.medicamento;
import entities.pedido;
import entities.pedidoMedicamento;
import java.sql.Connection;
import java.util.Scanner;

import db.DB;
import entities.cliente;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
	
		Connection comn = DB.getConnection();
		
		try {
			while(true) {
				System.out.println(
						           "1. Cadastrar um cliente\n" +
					                "2. Ver lista de clientes\n" +
					                "3. Deletar um cliente\n" +
					                "4. Cadastrar um funcionário\n" +
					                "5. Ver lista de funcionários\n" +
					                "6. Deletar um funcionário\n" +
					                "7. Cadastrar um medicamento\n" +
					                "8. Ver lista de medicamentos\n" +
					                "9. Deletar um medicamento\n" +
					                "10. Pedir um medicamento\n" +
					                "11. Pagar uma conta\n" +
					                "12. Imprimir uma conta\n" +
					                "13. Sair"
						          );
			}
		}
	}

}
