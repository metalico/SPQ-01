package main.java.es.deusto.spq.windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import main.java.es.deusto.spq.utils.Img;

/**
 * Clase de objeto visible en pantalla en juego
 * @author 001
 */
public class ObjetoGrafico extends JLabel {

	protected String nombreImagenObjeto; // Nombre del fichero de imagen del objeto
	protected JPanel panelJuego; // panel del juego donde se dibuja el objeto
	protected boolean esVisible; // Info de si el objeto va a ser visible en el panel
	protected int anchuraObjeto; // Anchura del objeto en pixels (depende de la imagen)
	protected int alturaObjeto; // Altura del objeto en pixels (depende de la imagen)
	protected ImageIcon icono; // icono del objeto
	protected boolean escalado; // escalado del icono
	protected BufferedImage imagenObjeto; // imagen para el escalado
	private static final long serialVersionUID = 1L; // para serializar

	/**
	 * Crea un nuevo objeto gr�fico de ventana para juegos. Si no existe el
	 * fichero de imagen, se crea un rect�ngulo blanco con borde rojo
	 * @param nombreImagenObjeto Nombre fichero donde est� la imagen del objeto (carpeta resources)
	 * @param visible true si se quiere ver, false si se quiere tener oculto
	 * @param anchura Anchura del objeto en p�xels
	 * @param altura Altura del objeto en p�xels
	 */
	public ObjetoGrafico(String nombreImagenObjeto, boolean visible,int anchura, int altura) {
		panelJuego = null;
		anchuraObjeto = anchura;
		alturaObjeto = altura;
		// Cargamos el icono (como un recurso - vale tambien del .jar)
		this.nombreImagenObjeto = nombreImagenObjeto;
		URL imgURL = Img.getURLRecurso(nombreImagenObjeto);
		
		if (imgURL == null) {
			icono = null;
			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(Color.red));
		} else {
			icono = new ImageIcon(imgURL);
			setIcon(icono);
			if (anchura == icono.getIconWidth()
					&& altura == icono.getIconHeight()) {
				escalado = false;
			} else { // Hay escalado: prepararlo
				escalado = true;
				try { // pone la imagen para el escalado
					imagenObjeto = ImageIO.read(imgURL);
				} catch (IOException e) {
					escalado = false;
				}
			}
		}
		setSize(anchura, altura);
		esVisible = visible;
		setVisible(esVisible);
	}

	/**
	 * Crea un nuevo objeto gr�fico de ventana para juegos.<br>
	 * Si no existe el fichero de imagen, se crea un rect�ngulo blanco con borde
	 * rojo de 10x10 p�xels<br>
	 * Si existe, se toma la anchura y la altura de esa imagen.
	 * @param nombreImagenObjeto Nombre fichero donde est� la imagen del objeto (carpeta resources)
	 * @param visible Panel en el que se debe dibujar el objeto
	 */
	public ObjetoGrafico(String nombreImagenObjeto, boolean visible) {
		this(nombreImagenObjeto, visible, 10, 10);
		if (icono != null) { // En este constructor se adapta la anchura y altura al icono
			anchuraObjeto = icono.getIconWidth();
			alturaObjeto = icono.getIconHeight();
			setSize(anchuraObjeto, alturaObjeto);
		}
	}

	/**
	 * Activa o desactiva la visualizaci�n del objeto
	 * @param visible true si se quiere ver, false si se quiere tener oculto
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		esVisible = visible;
	}

	/**
	 * Devuelve la anchura del rect�ngulo gr�fico del objeto
	 * @return Anchura
	 */
	public int getAnchuraObjeto() {
		return anchuraObjeto;
	}

	/**
	 * Devuelve la altura del rect�ngulo gr�fico del objeto
	 * @return Altura
	 */
	public int getAlturaObjeto() {
		return alturaObjeto;
	}

	// Dibuja este componente de una forma no habitual (si es proporcional)
	@Override
	protected void paintComponent(Graphics g) {
		if (escalado) {
			Graphics2D g2 = (Graphics2D) g; // El Graphics realmente es Graphics2D
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.drawImage(imagenObjeto, 0, 0, anchuraObjeto, alturaObjeto, null);
		} else { // sin escalado
			super.paintComponent(g);
		}
	}
}
