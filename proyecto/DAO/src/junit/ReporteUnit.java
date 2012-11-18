package junit;

import daoImpl.ReporteDAODB;
import junit.framework.TestCase;



public class ReporteUnit extends TestCase {
	ReporteDAODB r;

	public void setUp(){
		r=new ReporteDAODB();
	}

	public void testReporte(){

		System.out.println(r.getReporte().getMontoTotalVirtuales());

		assertEquals("Debería dar 30200",30200,r.getReporte().getMontoTotalComisionesJVF());
		assertEquals("Debería dar 77300",77300,r.getReporte().getMontoTotalNoVirtuales());
		assertEquals("Debería dar 164900",164900,r.getReporte().getMontoTotalVirtuales());
	}




}
