package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;

public class TestBloque {

	Bloque b;
	
	@Before
	public void setUp() throws Exception {
		b = new Bloque( 40, 4, Tipo.gris);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testTiempo() {
		assertTrue( b.getTiempo() == 40 );
	}
	
	@Test
	public void testPista() {
		assertTrue( b.getPista() == 4 );
	}


}
