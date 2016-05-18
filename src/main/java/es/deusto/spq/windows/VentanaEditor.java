package main.java.es.deusto.spq.windows;

import java.awt.Toolkit;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import main.java.es.deusto.spq.utils.BD;
import main.java.es.deusto.spq.utils.FileManager;
import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;
import main.java.es.deusto.spq.data.ReproducirCanciones;

/**
 * Ventana del editor del juego
 * @author 001
 *
 */
public class VentanaEditor extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // panel de contenido de la ventana
	private JTextField url; // caja de texto con la direcci�n del archivo a copiar
	private JTextArea cajaTitulo; // contenedor del texto "T�tulo"
	private JTextField titulo; // caja de texto con el t�tulo de la canci�n
	public String tituloCancion; // t�tulo de la canci�n
	private JButton btnReproducir; // bot�n para reproducir la canci�n en la direcci�n indicada en url
	private File ficheroSeleccionado; // fichero.dat en el que se guardan los bloques
	public static boolean reproducir = false; // para comprobar si se debe o no reproducir la canci�n
	private ArrayList<Bloque> listaBloques = new ArrayList<Bloque>(); // lista de bloques que se van formando
	private ArrayList<Bloque> listaBloquesGuardar = new ArrayList<Bloque>(); // lista de bloques a guardar en fichero
	private long tiempoSobra; // tiempo a restar al temporizador de los bloques
	@SuppressWarnings("rawtypes")
	private JList list; // lista en la que aparece la informaci�n de los bloques que se introducen
	@SuppressWarnings("rawtypes")
	private DefaultListModel modeloList; // modelo de lista para JList
	private JScrollPane scrollPane; // scroll para el JList
	VentanaEditor frame; // frame de la ventana principal

	/**
	 * Constructor de la ventana editor
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VentanaEditor() {
		frame = this;
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		ClassLoader classLoader = getClass().getClassLoader();
		setIconImage(Toolkit.getDefaultToolkit().getImage(classLoader.getResource("icon.png")));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEditor.class.getResource("/main/resources/icon.png")));
		setTitle("Editor - Audiosurf");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea cajaText = new JTextArea();
		cajaText.setForeground(Color.WHITE);
		cajaText.setEditable(false);
		cajaText.setBackground(new Color(1, 58, 223));
		cajaText.setFont(new Font("hooge 05_53", Font.BOLD | Font.ITALIC, 16));
		cajaText.setText("\r\n Elige el archivo \r\n que deseas reproducir");
		cajaText.setBounds(110, 110, 275, 49);
		cajaText.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(cajaText);

		url = new JTextField();
		url.setBackground(Color.WHITE);
		url.setBounds(110, 180, 275, 26);
		contentPane.add(url);
		url.setColumns(10);

		cajaTitulo = new JTextArea();
		cajaTitulo.setEditable(false);
		cajaTitulo.setText("\r\n T\u00EDtulo");
		cajaTitulo.setForeground(Color.WHITE);
		cajaTitulo.setFont(new Font("hooge 05_53", Font.BOLD | Font.ITALIC, 16));
		cajaTitulo.setBorder(BorderFactory.createLineBorder(Color.black));
		cajaTitulo.setBackground(new Color(1, 58, 223));
		cajaTitulo.setBounds(110, 300, 275, 39);
		contentPane.add(cajaTitulo);

		titulo = new JTextField();
		titulo.setColumns(10);
		titulo.setBackground(Color.WHITE);
		titulo.setBounds(110, 360, 275, 26);
		contentPane.add(titulo);

		btnReproducir = new JButton("Reproducir");
		btnReproducir.setBounds(190, 500, 115, 29);
		contentPane.add(btnReproducir);
		btnReproducir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!reproducir) {
					ReproducirCanciones.playOnce(url.getText());
					tiempoSobra = System.currentTimeMillis();
					reproducir = !reproducir;
				} else {
					try {
						ReproducirCanciones.apagarSonidos();
					} catch (LineUnavailableException | IOException
							| UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					modeloList = new DefaultListModel();
					list = new JList(modeloList);
					scrollPane = new JScrollPane(list);
					scrollPane.setBounds(611, 73, 540, 345);
					contentPane.add(scrollPane);
				}
			}
		});

		modeloList = new DefaultListModel();
		list = new JList(modeloList);
		list.addKeyListener(this);
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(611, 73, 540, 345);
		contentPane.add(scrollPane);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(1038, 500, 115, 29);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if (reproducir) {
					try {
						ReproducirCanciones.apagarSonidos();
					} catch (LineUnavailableException | IOException
							| UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					reproducir = false;	
				}
				//aquí debería volver al menú inicial cuando lo tengamos
			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(611, 500, 115, 29);
		contentPane.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (titulo.getText().compareTo("") == 0
						|| url.getText().compareTo("") == 0) {
					JOptionPane.showMessageDialog(frame,
							"Introduce un direcci�n y t�tulo.");
				} else {
					tituloCancion = titulo.getText();
					FileManager.fileCopy(url.getText(), "src/main/java/es/deusto/spq/songs/" + tituloCancion + ".wav");
					try {
						FileManager.saveBloqueToFile(listaBloquesGuardar, tituloCancion);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					BD.guardarCancion(tituloCancion, url.getText(), 0);
					try {
						ReproducirCanciones.apagarSonidos();
					} catch (LineUnavailableException | IOException
							| UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.setBounds(110, 227, 115, 29);
		contentPane.add(btnExaminar);
		btnExaminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ficheroSeleccionado = pedirFichero();
				url.setText(ficheroSeleccionado.getPath());
			}
		});

		JLabel labelVentana = new JLabel("");
		labelVentana.setIcon(new ImageIcon(classLoader.getResource("fondoMenu.jpg")));
		labelVentana.setBounds(0, 0, 1280, 720);
		contentPane.add(labelVentana);
	}

	/**
	 * JFileChooser que pide un fichero y lo escribe en la url
	 * @return ruta de fichero seleccionado
	 */
	private static File pedirFichero() {
		File dirActual = new File(System.getProperty("user.dir"));
		JFileChooser chooser = new JFileChooser(dirActual);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Ficheros m�sica", "wav"));
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		else
			return null;
	}

	/**
	 * Para introducir los bloques se utilizan las teclas
	 *  q w e r t que meten un bloque gris en el orden de pista de las letras
	 *  a s d f g que meten un bloque de color en el orden de pista de las letras
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void keyPressed(KeyEvent arg0) {
		final char car = Character.toLowerCase(arg0.getKeyChar());
		if (reproducir) {
			switch (car) {
			case 'q':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 1, Tipo.gris));
				break;
			case 'w':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 2, Tipo.gris));
				break;
			case 'e':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 3, Tipo.gris));
				break;
			case 'r':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 4, Tipo.gris));
				break;
			case 't':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 5, Tipo.gris));
				break;
			case 'a':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 1, Tipo.color));
				break;
			case 's':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 2, Tipo.color));
				break;
			case 'd':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 3, Tipo.color));
				break;
			case 'f':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 4, Tipo.color));
				break;
			case 'g':
				listaBloques.add(new Bloque(System.currentTimeMillis()
						- tiempoSobra, 5, Tipo.color));
				break;
			default:
				break;
			}
			for (int i = 0; i < listaBloques.size(); i++) {
				modeloList.addElement(listaBloques.get(i).getPista() + ","
						+ listaBloques.get(i).getTipo() + ","
						+ listaBloques.get(i).getTiempo());
			}
			listaBloquesGuardar.add(listaBloques.get(0));
			list.setModel(modeloList);
			listaBloques.clear();
			// }
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
