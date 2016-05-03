package main.java.es.deusto.spq.windows;

import java.awt.Toolkit;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import main.java.es.deusto.spq.utils.BD;
import main.java.es.deusto.spq.utils.FileManager;
import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;
import main.java.es.deusto.spq.data.BloqueGrafico;
import main.java.es.deusto.spq.data.Nave;
import main.java.es.deusto.spq.data.ReproducirCanciones;

/**
 * Creaci�n de la ventana de juego con el funcionamiento del juego principal
 * @author Alvaro e I�aki
 *
 */
public class VentanaJuego extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // panel de contenido de la ventana
	public static Nave nv; // nave de juego que controlar� el usuario
	private ArrayList<Bloque> arrayBloques; // array de bloques que se sacar�n por pantalla
	private static int cont; // contador para sacar los bloques
	private static long tiempoBase; // tiempo para separar los bloques entre si
	private int puntos = 0; // puntuaci�n del jugador
	private String titulo = ""; // t�tulo de la canci�n y del .dat

	/**
	 * Constructor de la ventana de juego
	 */
	public VentanaJuego(String titulo) {
		this.titulo = titulo;
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		setSize(1280, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEditor.class.getResource("/main/java/es/deusto/spq/img/icon.png")));
		setTitle("Audiosurf - " + titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelVentana = new JLabel("");
		labelVentana.setBounds(0, 0, 1280, 720);
		//Imagen de fondo, est� comentada porque se pon�a encima de todo
		// labelVentana.setIcon(new ImageIcon(VentanaEditor.class.getResource("/img/fondoJuego.jpg")));
		contentPane.setBackground(Color.black);
		contentPane.add(labelVentana);

		nv = new Nave(3, "/main/java/es/deusto/spq/img/nave.png", true);
		addNave(nv);

		try {
			arrayBloques = FileManager.readBloqueFromFile(titulo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		tiempoBase = 0;
		cont = 0;
		for (int i = 0; i < arrayBloques.size() - 1; i++) {
			Thread hilo = new Thread(new BajarBloque());
			hilo.start();
		}
		Thread hilo = new Thread(new BajarUltimoBloque());
		hilo.start();

		ReproducirCanciones.playOnce("src/main/java/es/deusto/spq/songs/" + titulo + ".wav");
	}

	/**
	 * Clase para bajar bloques mediante un hilo
	 * @author Alvaro e I�aki
	 *
	 */
	class BajarBloque implements Runnable {
		@Override
		public void run() {
			int i = cont;
			cont++;
			BloqueGrafico bg = new BloqueGrafico(arrayBloques.get(i), "/main/java/es/deusto/spq/img/" + arrayBloques.get(i).getTipo() + ".png", true);
			try {
				Thread.sleep(arrayBloques.get(i).getTiempo() - tiempoBase);
				addBloque(bg);
				tiempoBase = arrayBloques.get(i).getTiempo();
				moverBloque(bg);
			} catch (InterruptedException e) {
				System.out.println("Hilo interrumpido");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Clase para bajar el �ltimo bloque, que tiene funcionamiento especial
	 * @author Alvaro e I�aki
	 *
	 */
	class BajarUltimoBloque implements Runnable {
		@Override
		public void run() {
			int i = cont;
			cont++;
			BloqueGrafico bg = new BloqueGrafico(arrayBloques.get(i), "/main/java/es/deusto/spq/img/" + arrayBloques.get(i).getTipo() + ".png", true);
			try {
				Thread.sleep(arrayBloques.get(i).getTiempo() - tiempoBase);
				addBloque(bg);
				tiempoBase = arrayBloques.get(i).getTiempo();
				moverBloque(bg);
			} catch (InterruptedException e) {
				System.out.println("Hilo interrumpido");
				e.printStackTrace();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (BD.esPMax(titulo, puntos)) {
				JOptionPane.showMessageDialog(null, "Puntuaci�n: " + puntos + " �Nuevo r�cord!");
				BD.actualizarRecord(titulo, puntos);
			} else
				JOptionPane.showMessageDialog(null, "Puntuaci�n: " + puntos);

			dispose();
			try {
				ReproducirCanciones.apagarSonidos();
			} catch (LineUnavailableException | IOException
					| UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
			MenuWindow tarea = new MenuWindow();
			tarea.setVisible(true);
		}
	}

	/**
	 * M�todo que comprueba si choca la nave con un bloque que recibe
	 * @param bg bloque gr�fico a comprobar
	 * @return true si choca, false si no
	 */
	private static boolean comprobarChoque(BloqueGrafico bg) {
		if (bg.getBloque().getPista() == nv.getPista()) {
			return true;
		}
		return false;
	}

	/**
	 * M�todo para mover un bloque gr�fico hasta cierta altura
	 * Desaparece al llegar abajo o al chocar con la nave
	 * @param bg bloque gr�fico a mover
	 */
	public void moverBloque(BloqueGrafico bg) {
		while (bg.getPosY() < 660) {
			bg.setPosY(bg.getPosY() + 1);
			addBloque(bg);
			if (bg.getPosY() >= 515 && comprobarChoque(bg)) {
				quitarBloque(bg);
				sumaPuntos(bg);
				break;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (bg.getPosY() == 660)
			quitarBloque(bg);
	}

	/**
	 * M�todo para eliminar un bloque gr�fico
	 * @param bg bloque gr�fico a eliminar
	 */
	public void quitarBloque(BloqueGrafico bg) {
		remove(bg);
	}

	/**
	 * A�ade a la ventana un bloque gr�fico, que se visualizar�
	 * inmediatamente si est� marcado para ser visible.
	 * @param bg bloque gr�fico a introducir
	 */
	public void addBloque(final BloqueGrafico bg) {
		bg.setLocation(bg.getPosX(), bg.getPosY());
		contentPane.add(bg);
		contentPane.repaint();
	}

	/**
	 * A�ade la nave de juego en la ventana
	 * @param nv nave de juego a a�adir
	 */
	public void addNave(final Nave nv) {
		nv.setLocation(nv.getPosX(), nv.getPosY());
		contentPane.add(nv);
		contentPane.repaint();
	}

	/**
	 * Desplaza la nave a la siguiente posici�n a la izquierda
	 */
	public void moverIzquierda() {
		if (nv.getPosX() > 320) {
			nv.setPista(nv.getPista() - 1);
			nv.setPosX(nv.getPosX() - 130);
			addNave(nv);
		}
	}
	
	/**
	 * Desplaza la nave a la siguiente posici�n a la derecha
	 */
	public void moverDerecha() {
		if (nv.getPosX() < 840) {
			nv.setPista(nv.getPista() + 1);
			nv.setPosX(nv.getPosX() + 130);
			addNave(nv);
		}
	}

	/**
	 * Lleva la cuenta de los puntos de la partida
	 * Los bloques de color suman 10, los grises restan 10
	 * @param bg
	 */
	public void sumaPuntos(BloqueGrafico bg) {
		if (bg.getBloque().getTipo() == Tipo.color)
			puntos += 10;
		else
			puntos -= 10;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		final char car = Character.toLowerCase(arg0.getKeyChar());
		switch (car) {
		case 'j':
			moverIzquierda();
			break;
		case 'l':
			moverDerecha();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
