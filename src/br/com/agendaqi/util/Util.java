package br.com.agendaqi.util;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Util {
	public void limparCampos(JPanel contentPane) { // Utilitário para limpar os campos de textos
		Component componentes[] = contentPane.getComponents();
        for (Component component : componentes) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
	}

}
