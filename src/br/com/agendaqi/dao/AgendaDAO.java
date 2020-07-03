package br.com.agendaqi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import br.com.agendaqi.model.Agenda;
import br.com.jdbc.ConnectionFactory;

public class AgendaDAO {
	
	private Connection connection; // Cria uma v�ri�vel do tipo connection
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Pega a data e transforma para o formato do banco de dados

	public AgendaDAO() {
		this.connection = new ConnectionFactory().getConnection(); // Inicia uma conex�o atrav�s do construtor padr�o da classe
	}

	public void salvar(Agenda agenda) { // M�todo  para salvar no banco de dados
		try {
			

			String sql = "insert into agenda values(null,'" + agenda.getNome() + "','" + agenda.getApelido() + "','"
					+ sdf.format(agenda.getDatanascimento()) + "')"; // String com os valores para serem inseridos no banco de dados
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql); // Prepara uma conex�o
			ps.execute(sql); // Executa o sql
			ps.close(); // Fecha a PreparedStatament
			connection.close(); // Fecha a conex�o
			JOptionPane.showMessageDialog(null, "Contato salvo com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao salvar o contato" + e);
		}
	}

	public void Alterar(Agenda agenda) { // M�todo para alterar contato no banco de dados
		try {
			String sql = "update agenda set nome ='" + agenda.getNome() + "', apelido = '" + agenda.getApelido()
					+ "', datanascimento = '" + sdf.format(agenda.getDatanascimento()) + "' where id = '"
					+ agenda.getId() + "';"; // String com os valores que ir�o sofrer um update
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql); //Prepara uma conex�o
			ps.execute(sql); // Executa o sql
			ps.close(); /// Fecha a PreparedStatament
			connection.close(); // Fecha a conex�o
			JOptionPane.showMessageDialog(null, "Contato alterado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao alterar o contato" + e);
		}
	}

	public void excluir(Agenda agenda) { // M�todo para excluir contato do banco de dados
		try {
			String sql = "delete from agenda where id=" + agenda.getId() + ";"; // String para deletar o contato
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql); // Prepara uma conex�o
			ps.execute(); // Executa o sql
			ps.close(); // Fecha a PreparedStatament
			connection.close(); // Fecha a conex�o
			JOptionPane.showMessageDialog(null, "Contato exclu�do com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao excluir: " + e);
		}
	}

	public Agenda buscarPorNome(String nome) {// M�todo para buscar por nome (Recebendo o nome)
		try {
			String sql = "select * from agenda where nome = '" + nome + "';"; // String com uma cl�usula where para selecionar o nome
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql); // Prepara uma conex�o

			ResultSet rs = ps.executeQuery(); // O ResultSet armazena os dados vindos do banco de dados
			Agenda agenda = new Agenda();

			while (rs.next()) { // O ResultSet � percorrido e atribui seus valores a um objeto do tipo agenda
				agenda.setId(rs.getInt("id")); 
				agenda.setNome(rs.getString("nome"));
				agenda.setApelido(rs.getString("apelido"));
				agenda.setDatanascimento(rs.getDate("datanascimento"));
			}
			rs.close(); // Fecha o ResultSet
			ps.close(); // Fecha a PreparedStatament
			connection.close(); // Fecha a conex�o
			return agenda; // Retorna um obejeto agenda com os dados do nome que foi consultado
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nome n�o encontrado!");
		}
		return null;
	}

	public ArrayList<Agenda> buscaTodos() { // M�todo que retorna todos os contatos do banco de dados
		try {
			String sql = "select * from agenda;"; // sql com a instru��o para selecionar todos os contatos
			
			ArrayList<Agenda> contatos = new ArrayList<>(); // Cria um array para objetos do tipo agenda
			
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql); // Prepara a conex�o
			ResultSet rs = ps.executeQuery(); // Executa a busca e a armazena todos os dados encontrados no banco de dados
			
			while (rs.next()) { // Percorre os resultados
				Agenda agenda = new Agenda(); // Cria objetos do tipo agenda e atribui a esses objetos os resultados encontrados na busca
				
				agenda.setId(rs.getInt("id"));
				agenda.setNome(rs.getString("nome"));
				agenda.setApelido(rs.getString("apelido"));
				agenda.setDatanascimento(rs.getDate("datanascimento"));
				contatos.add(agenda);
			}
			rs.close(); // Fecha o ResultSet
			ps.close(); // Fecha a PreparedStatament
			connection.close(); // Fecha a conex�o
			return contatos;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao busca contatos: " + e);
		}
		return null;
	}
}
