package main.java.es.deusto.spq.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import main.java.es.deusto.spq.data.ReproducirCanciones;
import main.java.es.deusto.spq.utils.BD;
import main.java.es.deusto.spq.utils.FileManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Clase que crea la ventana del menu de juego
 * @author 001
 *
 */
@SuppressWarnings("unused")
public class MenuWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // panel de contenido del menu

	/**
	 * M�todo main para el inicio del men�
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuWindow frame = new MenuWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JButton btnStart; // bot�n de empezar
	JButton btnEditor; // bot�n para ir a la ventana de editor
	JButton btnScore; // bot�n para mostrar las puntuaciones m�ximas
	JButton btnSalir; // bot�n para salir del programa
	JLabel lblNewLabel; // label para el panel de contenido del menu

	/**
	 * Constructor de la ventana
	 */
	public MenuWindow() {
		setForeground(new Color(0, 191, 255));
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		setTitle("AudioSurf Menu - Selecciona una opcion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuWindow.class.getResource("/main/java/es/deusto/spq/img/icon.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnStart = new JButton("Jugar");
		btnStart.setForeground(new Color(0, 0, 255));
		btnStart.setFont(new Font("Dungeon", Font.BOLD | Font.ITALIC, 51));
		btnStart.setBounds(67, 567, 253, 58);
		contentPane.add(btnStart);

		btnEditor = new JButton("Editor");
		btnEditor.setForeground(new Color(0, 0, 255));
		btnEditor.setFont(new Font("Dungeon", Font.BOLD | Font.ITALIC, 51));
		btnEditor.setBounds(659, 567, 253, 58);
		contentPane.add(btnEditor);

		btnScore = new JButton("Record");
		btnScore.setForeground(new Color(0, 0, 255));
		btnScore.setFont(new Font("Dungeon", Font.BOLD | Font.ITALIC, 51));
		btnScore.setBounds(356, 567, 253, 58);
		contentPane.add(btnScore);

		btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.BLUE);
		btnSalir.setFont(new Font("Dungeon", Font.BOLD | Font.ITALIC, 51));
		btnSalir.setBounds(949, 567, 253, 58);
		contentPane.add(btnSalir);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuWindow.class.getResource("/main/java/es/deusto/spq/img/fondoMenu.jpg")));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		contentPane.add(lblNewLabel);

		btnEditor.addActionListener(this);
		btnScore.addActionListener(this);
		btnStart.addActionListener(this);
		btnSalir.addActionListener(this);
		
		ReproducirCanciones.play("src/main/java/es/deusto/spq/songs/payday.wav");
	}

	/**
	 * Clase que implementa el boton salir y su animacion
	 * @author Alvaro e I�aki
	 *
	 */
	class Quit implements Runnable {
		@Override
		public void run() {
			for (int i = 3; i >= 1; i--) {
				btnSalir.setText(i + "");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Hilo interrumpido");
					e.printStackTrace();
				}
			}
			System.exit(0);
		}
	}

	/**
	 * M�todo del actionListener
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton boton = (JButton) arg0.getSource();
		if (boton == btnEditor) {
			this.dispose();
			try {
				ReproducirCanciones.apagarSonidos();
			} catch (LineUnavailableException | IOException
					| UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			VentanaEditor tarea = new VentanaEditor();
			tarea.setVisible(true);
		} else if (boton == btnScore) {
			BD.mostrarPMax();
		} else if (boton == btnStart) {
			this.dispose();
			try {
				ReproducirCanciones.apagarSonidos();
			} catch (LineUnavailableException | IOException
					| UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			VentanaSeleccionCancion tarea = new VentanaSeleccionCancion();
		} else if (boton == btnSalir) {
			Thread hilo = new Thread(new Quit());
			hilo.start();
		}
	}
}
