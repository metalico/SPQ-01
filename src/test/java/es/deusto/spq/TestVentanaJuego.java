package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;
import main.java.es.deusto.spq.data.BloqueGrafico;
import main.java.es.deusto.spq.windows.VentanaJuego;

public class TestVentanaJuego {

	VentanaJuego vj;
	
	@Before
	public void setUp() throws Exception {
		vj = new VentanaJuego("aoe2");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testChoque() {
		BloqueGrafico bg = new BloqueGrafico( new Bloque( 40, 2, Tipo.color ), "color", false );
		assertTrue( bg.getPosY() == 0 );
		vj.moverBloque( bg );
		assertTrue( bg.getPosY() == 660 );
	}

}
