package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
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
public class TestBloque {

	Bloque b;
	static Logger logger = Logger.getLogger(TestBloque.class.getName());
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(TestBloque.class);
	}
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		logger.info("Entering setUp");
		b = new Bloque( 40, 4, Tipo.gris);
		logger.info("Finishing setUp");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testTiempo() throws Exception {
		logger.info("Entering testTiempo");
		assertTrue( b.getTiempo() == 40 );
		logger.info("Finishing testTiempo");
	}
	
	@Test
	public void testPista() throws Exception {
		logger.info("Entering testPista");
		assertTrue( b.getPista() == 4 );
		logger.info("Finishing testTiempo");
	}


}
