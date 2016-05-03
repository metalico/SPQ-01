package main.java.es.deusto.spq.data;


import java.io.Serializable;

/**
 * Clase para crear bloques con la informaci�n b�sica para el editor
 * @author 001
 *
 */
@SuppressWarnings("serial")
public class Bloque implements Serializable {

	private long tiempo; // tiempo en el que aparece el bloque
	private int pista; // pista en la que aparece el bloque
	private Tipo tipo; // tipo de bloque

	/**
	 * Constructor de Bloque
	 * @param tiempo en el que aparece el bloque
	 * @param pista en la que aparece el bloque
	 * @param tipo de bloque
	 */
	public Bloque(long tiempo, int pista, Tipo tipo) {
		this.tiempo=tiempo;
		this.pista= pista;
		this.tipo=tipo;
	}
	
	/**
	 * Devuelve el tiempo en el que aparece el bloque
	 * @return tiempo en el que aparece el bloque
	 */
	public long getTiempo() {
		return tiempo;
	}
	
	/**
	 * Establece el tiempo en que aparece el bloque
	 * @param tiempo a establecer
	 */
	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * Devuelve la pista en la que se encuentra el bloque
	 * @return pista
	 */
	public int getPista() {
		return pista;
	}

	/**
	 * Establece la pista en la que se encuentra el bloque
	 * @param pista a establecer
	 */
	public void setPista(int pista) {
		this.pista = pista;
	}

	/**
	 * Devuelve el tipo de bloque
	 * @return tipo
	 */
	public Tipo getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo de bloque
	 * @param tipo de bloque a establecer
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * Clase que establece el tipo que puede adoptar un bloque Puede ser gris o
	 * color
	 * 
	 * @author 001
	 */
	public enum Tipo {
		gris, color
	}
	
	public void quitar(){
		
	}
}
