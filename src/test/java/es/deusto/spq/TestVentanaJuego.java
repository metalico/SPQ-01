package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;
import main.java.es.deusto.spq.data.BloqueGrafico;
import main.java.es.deusto.spq.windows.VentanaJuego;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule; 

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class TestVentanaJuego {

	VentanaJuego vj;
	static Logger logger = Logger.getLogger(TestVentanaJuego.class.getName());
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(TestVentanaJuego.class);
	}
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		logger.info("Entering setUp");
		vj = new VentanaJuego("aoe2");
		logger.info("Finishing setUp");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testChoque() {
		logger.info("Entering testChoque");
		BloqueGrafico bg = new BloqueGrafico( new Bloque( 40, 2, Tipo.color ), "color", false );
		assertTrue( bg.getPosY() == 0 );
		vj.moverBloque( bg );
		assertTrue( bg.getPosY() == 660 );
		logger.info("Finishing testChoque");
	}

}
