package main.java.es.deusto.spq.utils;

import java.io.*;
import java.util.ArrayList;
import main.java.es.deusto.spq.data.Bloque;

/** 
 * Clase para el manejo de ficheros
 * @author 001
 * 
 */
public class FileManager {
	
	/**
	 * Guarda un fichero con objetos de tipo bloque
	 * @param listaBloquesGuardar arraylist de tipo bloque, es lo que se guarda
	 * @param titulo nombre del fichero
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void saveBloqueToFile(ArrayList<Bloque> listaBloquesGuardar, String titulo) throws FileNotFoundException, IOException {
		  java.io.OutputStream os = new java.io.FileOutputStream( "src/main/java/es/deusto/spq/songs/"+titulo+".dat");
		  java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream( os );
		  oos.writeObject( listaBloquesGuardar );
		  oos.close();
	}
	
	/**
	 * Carga un fichero que tiene objetos de tipo bloque
	 * @param titulo nombre del fichero a cargar
	 * @return arraylist de bloques
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static ArrayList<Bloque> readBloqueFromFile(String titulo) throws Exception {
		  ArrayList<Bloque> listaBloquesCargar=new ArrayList<Bloque>();
		  java.io.InputStream is = new java.io.FileInputStream( "src/main/java/es/deusto/spq/songs/"+titulo+".dat" );
		  java.io.ObjectInputStream ois = new java.io.ObjectInputStream( is );
		  listaBloquesCargar= (ArrayList<Bloque>) ois.readObject();
		  return listaBloquesCargar;
	 }
	
	/**
	 * Copia un fichero en otro lugar
	 * @param sourceFile ruta del fichero original
	 * @param destinationFile ruta a la que copiamos el fichero
	 */
	public static void fileCopy(String sourceFile, String destinationFile) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(sourceFile);
			os = new FileOutputStream(destinationFile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (Exception e) {
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
