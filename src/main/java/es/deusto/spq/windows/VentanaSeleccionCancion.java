package main.java.es.deusto.spq.windows;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import main.java.es.deusto.spq.utils.BD;

/**
 * Ventana para elegir la canci�n que se va a jugar
 * @author Alvaro e I�aki
 */
public class VentanaSeleccionCancion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField tf; // campo de texto en el que aparecer� el nombre elegido
	private JComboBox<String> combo; // desplegable con las opciones a elegir
	private JFrame v; // frame de la ventana
	private JButton btnEmpezar; // bot�n para empezar a jugar
	private ArrayList<String> titulos; // array de t�tulos de canciones disponibles

	/**
	 * Constructor de la ventana de selecci�n de canci�n
	 */
	public VentanaSeleccionCancion() {
		// Creaci�n del JTextField
		tf = new JTextField(20);
		tf.setText("");
		titulos = BD.sacarTitulos();
		
		// Creaci�n del JComboBox y a�adir los items.
		combo = new JComboBox<String>();
		combo.addItem("");
		for (String s : titulos) {
			combo.addItem(s);
		}

		// Acci�n a realizar cuando el JComboBox cambia de item seleccionado.
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(combo.getSelectedItem().toString());
			}
		});

		btnEmpezar = new JButton("Empezar");
		btnEmpezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Elige una canci�n.");
				else {
					v.dispose();
					VentanaJuego tarea = new VentanaJuego(tf.getText());
					tarea.setVisible(true);
				}
			}
		});

		// Creacion de la ventana con los componentes
		v = new JFrame();
		v.setResizable(false);
		v.setLocationRelativeTo(null);
		v.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEditor.class.getResource("/main/java/es/deusto/spq/img/icon.png")));
		v.setTitle("Elegir Canci�n - Audiosurf");
		v.getContentPane().setLayout(new FlowLayout());
		v.getContentPane().add(combo);
		v.getContentPane().add(tf);
		v.getContentPane().add(btnEmpezar);
		v.pack();
		v.setVisible(true);
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
