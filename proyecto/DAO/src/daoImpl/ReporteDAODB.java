package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import busImpl.Reporte;
import daoInterfaces.ReporteDAO;

public class ReporteDAODB implements ReporteDAO{

	public Reporte getReporte() {
		Reporte r=new Reporte();
		Conexion c=new Conexion();
		int montoTotalNoVirtuales=0;
		int montoTotalVirtuales=0;
		int montoTotalComisionesJVF=0;
		try {
			ResultSet rs=c.devolverResutado("select sum(credito) from usuarios where virtual='0' AND usuario!='jvf'");
			if(rs.first()){
				montoTotalNoVirtuales=rs.getInt("sum(credito)");
				r.setMontoTotalNoVirtuales(montoTotalNoVirtuales);
			}
			ResultSet rs2=c.devolverResutado("select sum(credito) from usuarios where virtual='1' AND usuario!='jvf'");
			if(rs2.first()){
				montoTotalVirtuales=rs2.getInt("sum(credito)");
				r.setMontoTotalVirtuales(montoTotalVirtuales);
			}
			ResultSet rs3=c.devolverResutado("select credito from usuarios where usuario!='jvf'");
			if(rs3.first()){
				montoTotalComisionesJVF=rs3.getInt("credito");
				r.setMontoTotalComisionesJVF(montoTotalComisionesJVF);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;
	}


}
