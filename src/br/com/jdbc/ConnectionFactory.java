package br.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory { // Classe que cria a conexão com o banco
	public Connection getConnection() { // Método pegar conexão
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agendaqi", "root", "Maicon30051996"); // Passa os dados referentes a conexão
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao conectar" + e);
		}
		return null;
	}
}
