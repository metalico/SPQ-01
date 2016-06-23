package test.java.es.deusto.spq;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;
import main.java.es.deusto.spq.windows.VentanaJuego;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule; 

//@PerfTest(invocations = 5)
//@Required(max = 1200, average = 250)
public class TestMoverNave {
	
	VentanaJuego v;
	static Logger logger = Logger.getLogger(TestMoverNave.class.getName());
	
	//@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	//public static junit.framework.Test suite() {
	//	 return new JUnit4TestAdapter(TestMoverNave.class);
	//}
	
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		logger.info("Entering setUp");
		v=new VentanaJuego("aoe2");
		logger.info("Finishing setUp");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testMoverIzquierda() {
		logger.info("Entering testMoverIzquierda");
		v.moverIzquierda();
		assertTrue(VentanaJuego.nv.getPista()==2);
		logger.info("Finishing testMoverIzquierda");
	}

	@Test
	public void testMoverDerecha() {
		logger.info("Entering testMoverIzquierda");
		v.moverDerecha();
		assertTrue(VentanaJuego.nv.getPista()==4);
		logger.info("Finishing testMoverDerecha");
	}
}
