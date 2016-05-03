import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import windows.VentanaJuego;
import data.Nave;

public class TestMoverNave {
	
	VentanaJuego v;
	
	@Before
	public void setUp() throws Exception {
		v=new VentanaJuego("Ejemplo");
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
