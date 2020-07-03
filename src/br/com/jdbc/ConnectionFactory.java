package br.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory { // Classe que cria a conex�o com o banco
	public Connection getConnection() { // M�todo pegar conex�o
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agendaqi", "root", "Maicon30051996"); // Passa os dados referentes a conex�o
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao conectar" + e);
		}
		return null;
	}
}
