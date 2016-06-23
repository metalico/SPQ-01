package test.java.es.deusto.spq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
import main.java.es.deusto.spq.windows.ObjetoGrafico;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule; 

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class TestObjetoGrafico {

	ObjetoGrafico og;
	static Logger logger = Logger.getLogger(TestObjetoGrafico.class.getName());
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(TestObjetoGrafico.class);
	}
	
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		logger.info("Entering setUp");
		og = new ObjetoGrafico( "nave", true, 20, 200 );
		logger.info("Finishing setUp");
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	/*@Test
	public void testVisible() {
		logger.info("Entering testVisible");
		assertTrue( og.isVisible() );
		og.setVisible(false);
		assertTrue( !og.isVisible() );
		logger.info("Finishing testVisible");
	}*/

	@Test
	public void testTamanyo() {
		logger.info("Entering testTamanyo");
		assertTrue( og.getAnchuraObjeto() == 20 );
		assertTrue( og.getAlturaObjeto() == 200 );
		logger.info("Finishing testTamanyo");
	}

}
