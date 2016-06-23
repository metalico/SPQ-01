package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
import main.java.es.deusto.spq.data.BloqueGrafico;
import main.java.es.deusto.spq.windows.ObjetoGrafico;
import main.java.es.deusto.spq.data.Bloque;
import main.java.es.deusto.spq.data.Bloque.Tipo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule; 

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class TestBloqueGrafico {

	BloqueGrafico bg;
	static Logger logger = Logger.getLogger(TestBloqueGrafico.class.getName());
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(TestBloqueGrafico.class);
	}
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		logger.info("Entering setUp");
		bg = new BloqueGrafico( new Bloque( 40, 2, Tipo.color ), "color", false );
		logger.info("Finishing setUp");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testX() {
		logger.info("Entering testX");
		assertTrue( bg.getPosX() == 450 );
		logger.info("Finishing testX");
	}

	@Test
	public void testY() {
		logger.info("Entering testY");
		assertTrue( bg.getPosY() == 0 );
		logger.info("Finishing testY");
	}
	
	@Test
	public void testBloque() {
		logger.info("Entering testBloque");
		assertTrue( bg.getBloque() instanceof Bloque );
		logger.info("Finishing testBloque");
	}
	
	@Test
	public void testObjetoGrafico() {
		logger.info("Entering testObjetoGrafico");
		assertTrue( bg.getObjeto() instanceof ObjetoGrafico );
		logger.info("Finishing testObjetoGrafico");
	}

}
