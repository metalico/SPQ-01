package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.es.deusto.spq.data.BloqueGrafico;
import main.java.es.deusto.spq.windows.ObjetoGrafico;
import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;

public class TestBloqueGrafico {

	BloqueGrafico bg;
	
	@Before
	public void setUp() throws Exception {
		bg = new BloqueGrafico( new Bloque( 40, 2, Tipo.color ), "color", false );
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testX() {
		assertTrue( bg.getPosX() == 450 );
	}

	@Test
	public void testY() {
		assertTrue( bg.getPosY() == 0 );
		bg.setPosY(7);
		assertTrue( bg.getPosY() == 7 );
	}
	
	@Test
	public void testBloque() {
		assertTrue( bg.getBloque() instanceof Bloque );
	}
	
	@Test
	public void testObjetoGrafico() {
		assertTrue( bg.getObjeto() instanceof ObjetoGrafico );
	}

}
