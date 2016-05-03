package main.java.es.deusto.spq.data;

import main.java.es.deusto.spq.windows.ObjetoGrafico;

/**
 * Clase para crear la nave que aparece en pantalla en el juego
 * @author 001
 *
 */
public class Nave extends ObjetoGrafico {

	private static final long serialVersionUID = 1L;
	private int pista; // pista en la que aparece la nave
	private int posX; // posici�n x de la nave
	private int posY = 575; // posici�n y de la nave
	private ObjetoGrafico og; // objeto gr�fico para construir la nave en pantalla
	String nombreImagenObjeto; // nombre de la imagen a utilizar

	/**
	 * Constructor para un objeto de tipo nave
	 * @param pista en la que aparece la nave
	 * @param nombreImagenObjeto nombre de la imagen a utilizar
	 * @param visible si es visible o no
	 */
	public Nave(int pista, String nombreImagenObjeto, boolean visible) {
		super(nombreImagenObjeto, visible);
		this.pista = pista;
		if (pista == 1) {
			this.posX = 320;
		} else if (pista == 2) {
			this.posX = 450;
		} else if (pista == 3) {
			this.posX = 580;
		} else if (pista == 4) {
			this.posX = 710;
		} else if (pista == 5) {
			this.posX = 840;
		}
		this.nombreImagenObjeto = nombreImagenObjeto;// "/img/nave.png"
		og = new ObjetoGrafico(nombreImagenObjeto, true);
	}

	/**
	 * Devuelve la pista en la que est� la nave
	 * @return pista en la que est� la nave
	 */
	public int getPista() {
		return pista;
	}

	/**
	 * Establece la pista en la que est� la nave
	 * @param pista en la que est� la nave
	 */
	public void setPista(int pista) {
		this.pista = pista;
	}

	/**
	 * Devuelve la posici�n x de la nave
	 * @return posX posici�n x de la nave
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Devuelve la posici�n y de la nave
	 * @return posY posici�n y de la nave
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Establece la posici�n x de la nave
	 * @param posX posici�n x de la nave
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Devuelve el objeto gr�fico de esta nave
	 * @return Objeto  gr�fico de esta nave
	 */
	public ObjetoGrafico getObjeto() {
		return og;
	}
}
