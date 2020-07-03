package br.com.agendaqi.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.agendaqi.dao.AgendaDAO;
import br.com.agendaqi.model.Agenda;
import br.com.agendaqi.util.Util;

public class FormAgenda extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtApelido;
	private JTextField txtDataNascimento;
	private JTextField txtBuscarNome;
	private JTextField txtId;
	int posicao = 0; // índice para o método de buscar através das setas
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Conversor de data de String para sql e vice-versa
	private AgendaDAO agendaDAO; // Variavel do tipo AgendaDAO

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAgenda frame = new FormAgenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormAgenda() {
		setResizable(false);
		setTitle("Agenda");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(29, 81, 46, 17);
		contentPane.add(lblNewLabel);

		JLabel lblApelido = new JLabel("Data Nascimento (dd/mm/aaaa):");
		lblApelido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApelido.setBounds(29, 199, 230, 17);
		contentPane.add(lblApelido);

		JLabel lblApelido_1 = new JLabel("Apelido:");
		lblApelido_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApelido_1.setBounds(29, 140, 56, 17);
		contentPane.add(lblApelido_1);

		JLabel lblLocalizarPorNome = new JLabel("Localizar por Nome:");
		lblLocalizarPorNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocalizarPorNome.setBounds(28, 315, 230, 17);
		contentPane.add(lblLocalizarPorNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(28, 109, 276, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtApelido = new JTextField();
		txtApelido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtApelido.setColumns(10);
		txtApelido.setBounds(28, 168, 276, 20);
		contentPane.add(txtApelido);

		txtDataNascimento = new JTextField();
		txtDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(28, 228, 138, 20);
		contentPane.add(txtDataNascimento);

		txtBuscarNome = new JTextField();
		txtBuscarNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtBuscarNome.setColumns(10);
		txtBuscarNome.setBounds(28, 343, 276, 20);
		contentPane.add(txtBuscarNome);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) { // Evento botão salvar
				try {
					Agenda agenda = new Agenda();
					agenda.setNome(txtNome.getText());
					agenda.setApelido(txtApelido.getText());

					agenda.setDatanascimento(sdf.parse(txtDataNascimento.getText()));

					agendaDAO = new AgendaDAO();
					agendaDAO.salvar(agenda);

					Util util = new Util(); 
					new Util().limparCampos(contentPane); //Utilitário para limpar os campos de texto
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Falha ao cadastrar o contato: " + e);
					new Util().limparCampos(contentPane);
				}
			}
		});

		btnSalvar.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/salvar.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalvar.setBounds(314, 109, 108, 23);
		contentPane.add(btnSalvar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Evento botão alterar
				try {
					Agenda agenda = new Agenda();
					agenda.setId(Integer.parseInt(txtId.getText()));
					agenda.setNome(txtNome.getText());
					agenda.setApelido(txtApelido.getText());

					agenda.setDatanascimento(sdf.parse(txtDataNascimento.getText()));

					agendaDAO = new AgendaDAO();
					agendaDAO.Alterar(agenda);

					Util util = new Util();
					new Util().limparCampos(contentPane);
				} catch (Exception f) {
					JOptionPane.showMessageDialog(null, "Falha ao cadastrar o contato: " + f);
					new Util().limparCampos(contentPane);
				}
			}
		});
		btnAlterar.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/atualizar.png")));
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAlterar.setBounds(314, 168, 108, 23);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Evento botão excluir
				try {
					Agenda agenda = new Agenda();
					agenda.setId(Integer.parseInt(txtId.getText()));

					agendaDAO = new AgendaDAO();
					agendaDAO.excluir(agenda);
					new Util().limparCampos(contentPane);
				} catch (Exception g) {
					JOptionPane.showMessageDialog(null, "Falha ao excluir: " + g);
				}
			}
		});

		btnExcluir.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/excluir.png")));
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(314, 226, 108, 23);
		contentPane.add(btnExcluir);

		JButton btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Evento botão "primeiro"
				buscarPorSeta(0); // Chamada para o método de buscar através das setas (passando parâmetro)
			}
		});
		btnPrimeiro.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/primeiro.png")));
		btnPrimeiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPrimeiro.setBounds(29, 281, 56, 23);
		contentPane.add(btnPrimeiro);

		JButton btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Evento botão "anterior"
				buscarPorSeta(1); // Chamada para o método de buscar através das setas (passando parâmetro)
			}
		});
		btnAnterior.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/anteior.png")));
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnterior.setBounds(95, 281, 56, 23);
		contentPane.add(btnAnterior);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Util().limparCampos(contentPane);
			}
		});
		btnLimpar.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/limpeza.png")));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimpar.setBounds(171, 281, 109, 23);
		contentPane.add(btnLimpar);

		JButton btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Evento botão "próximo"
				buscarPorSeta(2); // Chamada para o método de buscar através das setas (passando parâmetro)
			}
		});
		btnProximo.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/proximo.png")));
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProximo.setBounds(300, 281, 56, 23);
		contentPane.add(btnProximo);

		JButton btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Evento botão "último"
				buscarPorSeta(3); // Chamada para o método de buscar através das setas (passando parâmetro)
			}
		});
		btnUltimo.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/ultimo.png")));
		btnUltimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUltimo.setBounds(366, 281, 56, 23);
		contentPane.add(btnUltimo);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(FormAgenda.class.getResource("/br/com/agendaqi/images/buscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Evento botão buscar por nome

				try {
					AgendaDAO dao = new AgendaDAO();

					Agenda agenda = new Agenda();

					agenda = dao.buscarPorNome(txtBuscarNome.getText());

					txtId.setText(String.valueOf(agenda.getId()));
					txtNome.setText(agenda.getNome());
					txtApelido.setText(agenda.getApelido());

					txtDataNascimento.setText(sdf.format(agenda.getDatanascimento()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Nenhum contato encontrado: " + e);
					new Util().limparCampos(contentPane);
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.setBounds(314, 343, 108, 23);
		contentPane.add(btnBuscar);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(29, 22, 46, 17);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtId.setColumns(10);
		txtId.setBounds(29, 50, 138, 20);
		contentPane.add(txtId);
	}

	public void buscarPorSeta(int opcao) {
		agendaDAO = new AgendaDAO();
		ArrayList<Agenda> contatos = agendaDAO.buscaTodos(); // Lista com todos os contatos cadastrados
		
		if (contatos.isEmpty()) { // Se não existir contatos uma mensagem será exibida
			JOptionPane.showMessageDialog(null, "Não há contatos cadastrados para exibir!");
		} else { // Caso exista contatos entra na estrutura condicional para determinar a posição a ser exibida na janela
			if (opcao == 0) {
				posicao = 0;
			} else if (opcao == 3) {
				posicao = contatos.size() - 1;
			} else if (opcao == 1 && posicao > 0) {
				posicao--;
			} else if (opcao == 2 && posicao < contatos.size() - 1) {
				posicao++;
			} else {
				JOptionPane.showMessageDialog(null, "Sua lista de contatos acabou!");
			}

			if (posicao >= 0 && posicao <= contatos.size() - 1) { /* Confere se os valores da posicao estao corretos e preenche os
																	campos de texto*/
				txtId.setText(String.valueOf(contatos.get(posicao).getId()));
				txtNome.setText(contatos.get(posicao).getNome());
				txtApelido.setText(contatos.get(posicao).getApelido());
				txtDataNascimento.setText(sdf.format(contatos.get(posicao).getDatanascimento()));
			}
		}
	}
}
