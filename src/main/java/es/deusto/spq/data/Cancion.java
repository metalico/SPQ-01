package main.java.es.deusto.spq.data;

/**
 * Clase para crear objetos de tipo canci�n
 * @author 001
 * 
 */
public class Cancion {

	private String titulo; // t�tulo de la canci�n
	private String dir; // direcci�n del archivo
	/** 
	 * Constructor de la clase Canci�n
	 * @param titulo de la canci�n
	 * @param dir direcci�n del archivo
	 * @param pMax puntuaci�n m�xima sacada en la canci�n
	 */
	public Cancion(String titulo, String dir) {
		super();
		this.titulo = titulo;
		this.dir = dir;
	}

	/**
	 * Devuelve el t�tulo de la canci�n
	 * @return titulo de la canci�n
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Devuelve la direcci�n del archivo
	 * @return direcci�n del archivo
	 */
	public String getDir() {
		return dir;
	}
}
