package main.java.es.deusto.spq.data;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.java.es.deusto.spq.windows.VentanaEditor;

/**
 * Clase para la reproducci�n y detenci�n de canciones
 * @author 001
 * 
 */
public class ReproducirCanciones implements LineListener {

	private static Clip clip; // clip para manejar la canci�n
	
	/**
	 * Reproduce una canción en loop
	 * @param filename nombre de la canción
	 */
	public static void play(String filename) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
			clip.loop(99999);
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	/**
	 * Reproduce una canci�n una sola vez
	 * @param filename nombre de la canci�n
	 */
	public static void playOnce(String filename) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
			clip.addLineListener(new LineListener() {
				public void update(LineEvent evt) {
					if (evt.getType() == LineEvent.Type.STOP) {
						try {
							apagarSonidos();
						} catch (LineUnavailableException | IOException
								| UnsupportedAudioFileException e) {
							e.printStackTrace();
						}
						VentanaEditor.reproducir = false;
					}
				}
			});
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	/**
	 * Detiene la canci�n en curso
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public static void apagarSonidos() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip.stop();
		clip.close();
	}

	@Override
	public void update(LineEvent event) {

	}
}
