package test.java.es.deusto.spq;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.es.deusto.spq.windows.VentanaJuego;

public class TestMoverNave {
	
	VentanaJuego v;
	
	@Before
	public void setUp() throws Exception {
		v=new VentanaJuego("aoe2");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testMoverIzquierda() {
		v.moverIzquierda();
		assertTrue(VentanaJuego.nv.getPista()==2);
	}

	@Test
	public void testMoverDerecha() {
		v.moverDerecha();
		assertTrue(VentanaJuego.nv.getPista()==4);
	}
}
