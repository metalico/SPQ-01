package main.java.es.deusto.spq.data;

import main.java.es.deusto.spq.windows.ObjetoGrafico;

/**
 * Clase para crear un bloque que aparece por pantalla
 * @author 001
 * 
 */
public class BloqueGrafico extends ObjetoGrafico {

	private static final long serialVersionUID = 1L;
	private int posX; // posici�n x en pantalla del bloque
	private int posY; // posici�n y en pantalla del bloque
	private Bloque b; // objeto tipo bloque con la informaci�n b�sica (tiempo, pista, tipo)
	private ObjetoGrafico og; //objeto gr�fico para construir bloques gr�ficos en pantalla
	
	/**
	 * Constructor para crear un bloque gr�fico
	 * @param b bloque con los datos de tiempo, pista y tipo
	 * @param nombreImagenObjeto nombre del fichero de imagen del objeto
	 * @param visible si es visible por pantalla o no
	 */
	public BloqueGrafico(Bloque b, String nombreImagenObjeto, boolean visible) {
		super(nombreImagenObjeto, visible);
		this.b = b;
		this.nombreImagenObjeto = nombreImagenObjeto;
		if (b.getPista() == 1) {
			this.posX = 320;
			this.posY = 0;
		} else if (b.getPista() == 2) {
			this.posX = 450;
			this.posY = 0;
		} else if (b.getPista() == 3) {
			this.posX = 580;
			this.posY = 0;
		} else if (b.getPista() == 4) {
			this.posX = 710;
			this.posY = 0;
		} else if (b.getPista() == 5) {
			this.posX = 840;
			this.posY = 0;
		}
		og = new ObjetoGrafico(nombreImagenObjeto, true);
	}

	/**
	 * Devuelve la posici�n x del bloque
	 * @return posX posici�n del bloque gr�fico
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Devuelve la posici�n y del bloque
	 * @return posY posici�n y del bloque gr�fico
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Establece la posici�n y del bloque
	 * @param posY posici�n y del bloque gr�fico
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Devuelve el objeto bloque dentro de un bloque gr�fico
	 * @return b bloque
	 */
	public Bloque getBloque() {
		return b;
	}

	/**
	 * Devuelve el objeto gr�fico de este bloque gr�fico
	 * @return Objeto  gr�fico de este bloque
	 */
	public ObjetoGrafico getObjeto() {
		return og;
	}
}
