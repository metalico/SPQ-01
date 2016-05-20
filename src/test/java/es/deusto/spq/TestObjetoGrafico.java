package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.es.deusto.spq.windows.ObjetoGrafico;

public class TestObjetoGrafico {

	ObjetoGrafico og;
	
	@Before
	public void setUp() throws Exception {
		og = new ObjetoGrafico( "nave", true, 20, 200 );
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testVisible() {
		assertTrue( og.isVisible() );
		og.setVisible(false);
		assertTrue( !og.isVisible() );
	}

	@Test
	public void testTamanyo() {
		assertTrue( og.getAnchuraObjeto() == 20 );
		assertTrue( og.getAlturaObjeto() == 200 );
	}

}
