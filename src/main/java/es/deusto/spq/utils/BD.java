package main.java.es.deusto.spq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Clase para el manejo de bases de datos
 * @author 001
 * 
 */
public class BD {

	private static BD instance = null; // sentencia sql
	private static Connection conn; // conexi�n a establecer

	/**
	 * Devuelve el atributo instance
	 * @return instance
	 */
	public static BD getInstance() {
		if (instance == null)
			instance = new BD();
		return instance;
	}

	/**
	 * Establece una conexi�n con la BD
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/canciones.sqlite");
	}

	/**
	 * Detiene la conexi�n con la BD
	 * @throws SQLException
	 */
	public static void disconnect() throws SQLException {
		conn.close();
	}

	/**
	 * Guarda una canci�n nueva en la BD
	 * @param titulo de la cancion
	 * @param url direcci�n del archivo
	 * @param pmax puntuaci�n m�xima en esa canci�n
	 */
	public static void guardarCancion(String titulo, String url, int pmax) {
		try {
			connect();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("insert into canciones values (?,?,?)");
			stat.setString(1, titulo);
			stat.setString(2, url);
			stat.setInt(3, pmax);
			stat.executeUpdate();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra una ventana emergente con la puntuación
	 *  máxima de cada canción
	 */
	public static void mostrarPMax() {
		try {
			connect();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("select titulo, pmax from canciones");
			ResultSet rs = stat.executeQuery();
			String texto = "";
			while (rs.next()) {
				texto = texto.concat(rs.getString("titulo") + " : " + rs.getInt("pmax") + "\n");
			}
			rs.close();
			JOptionPane.showMessageDialog(null, texto);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Comprueba si la puntuación enviada es mayor que el récord
	 * @param titulo de la canción
	 * @param punt puntuación sacada
	 * @return boolean
	 */
	public static boolean esPMax(String titulo, int punt) {
		int pmax = 0;
		try {
			connect();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("select pmax from canciones where titulo='" + titulo + "'");
			ResultSet rs = stat.executeQuery();
			pmax = rs.getInt("pmax");
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (punt > pmax)
			return true;
		return false;
	}

	/**
	 * Saca un arraylist con los títulos de las canciones
	 * @return arraylist de títulos
	 */
	public static ArrayList<String> sacarTitulos() {
		ArrayList<String> titulos = new ArrayList<String>();
		try {
			connect();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("select titulo from canciones");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				titulos.add(rs.getString("titulo"));
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return titulos;
	}

	public static void actualizarRecord(String titulo, int pmax) {
		try {
			connect();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("update canciones set pmax=" + pmax);
			stat.executeUpdate();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}