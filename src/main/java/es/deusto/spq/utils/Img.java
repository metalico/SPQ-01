package main.java.es.deusto.spq.utils;

/**
 * Clase para introducir imagenes en el juego.
 * @author 001
 * 
 */
public class Img {
	
	/**
	 * Devuelve la URL de un recurso de imagen dado en este paquete Si no
	 * existe, muestra el volcado del error en la salida de error est�ndar
	 * @param nomRecImg Nombre del recurso de imagen a buscar
	 * @return URL del recurso, o null si no existe
	 */
	public static java.net.URL getURLRecurso(String nomRecImg) {
		java.net.URL recurso = null;
		try {
			recurso = Img.class.getResource(nomRecImg).toURI().toURL();
		} catch (Exception e) {
			System.err.println("Recurso no encontrado: " + nomRecImg);
			e.printStackTrace();
		}
		return recurso;
	}
}
