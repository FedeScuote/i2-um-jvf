package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import busImpl.Desafio;
import busImpl.usuario.Usuario;
import conexion.Conexion;
import daoExcepciones.NoExisteDesafioException;
import daoInterfaces.DesafioDAO;
import excepcionesB.NoEsUsuarioVirtualException;
import excepcionesB.NoExisteUsuarioException;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import excepcionesB.NotDataFoundException;

public class DesafioDAODB implements DesafioDAO {
    private static Logger logger = Logger.getLogger(DesafioDAODB.class);
    private int idDesafio;
    private int monto;
    private Date fechaHoraInicioD;
    private String estado; // En hora, En hora-lleno, En curso, Atrasado,

    // Finalizado, Cancelado

    public int getIdDesafio() {
        return idDesafio;
    }

    public void setIdDesafio(int idDesafio) {
        this.idDesafio = idDesafio;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFechaHoraInicioD() {
        return fechaHoraInicioD;
    }

    public void setFechaHoraInicioD(Date fechaHoraInicioD) {
        this.fechaHoraInicioD = fechaHoraInicioD;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList getDesafios() throws NoHayDesafioException {
        logger.debug("Entro a getDesafios");
        ArrayList a = new ArrayList();

        Conexion c = new Conexion();

        try {

            ResultSet resultado = c
                    .devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio");
            while (resultado.next()) {
                Desafio d = new Desafio();
                // cargo los datos en el objeto d de Desafio
                logger.debug("Cargo los datos en el objeto de Desafio");
                int idDesafio = resultado.getInt("idDesafio");
                int monto = resultado.getInt("monto");
                int idUsuario = resultado.getInt("usuarios_idusuario");
                Date fecha = resultado.getDate("fechaHoraInicioD");
                String estadoD = resultado.getString("estadoD");
                UsuarioDAODB ud = new UsuarioDAODB();
                String usuarioDesafio = ud.getUsuario(idUsuario);

                logger.debug("idDesafio= " + idDesafio);
                logger.debug("monto= " + monto);
                logger.debug("idUsuario= " + idUsuario);
                logger.debug("fecha= " + fecha);
                logger.debug("estado= " + estadoD);
                logger.debug("usuarioDesafio= " + usuarioDesafio);

                d.setUsuarioDesafio(usuarioDesafio);
                d.setIdDesafio(idDesafio);
                d.setMonto(monto);
                d.setFechaHoraInicioD(fecha);
                d.setEstado(estadoD);
                a.add(d);
            }
            // compruebo si hay desafios
            a.get(0);

        } catch (SQLException ex) {

            throw new NoHayDesafioException();

        } catch (IndexOutOfBoundsException i) {
            throw new NoHayDesafioException();
        } catch (NoExisteUsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo getDesafios()");
            c.disconnect();
        }
        return a;
    }

    public ArrayList getDesafiosBatallaNaval() throws NoHayDesafioException {
        ArrayList a = new ArrayList();

        Conexion c = new Conexion();

        try {

            ResultSet resultado = c
                    .devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='1' ");
            while (resultado.next()) {
                Desafio d = new Desafio();

                int idDesafio = resultado.getInt("idDesafio");
                int monto = resultado.getInt("monto");
                Date fecha = resultado.getDate("fechaHoraInicioD");
                String estadoD = resultado.getString("estadoD");

                d.setIdDesafio(idDesafio);
                d.setMonto(monto);
                d.setFechaHoraInicioD(fecha);
                d.setEstado(estadoD);
                a.add(d);
            }
            a.get(0);

        } catch (SQLException ex) {

            throw new NoHayDesafioException();

        } catch (IndexOutOfBoundsException i) {
            throw new NoHayDesafioException();
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo getDesafiosBatallaNaval");
            c.disconnect();
        }
        return a;
    }
    //optimizado
    public ArrayList getDesafiosUsuariosDisponibleBatallaNaval()
            throws NoHayDesafioException {
        logger.debug("Entro a getDesafiosUsuariosDisponibleBatallaNaval");
        ArrayList a = new ArrayList();

        Conexion c = new Conexion();

        try {

            ResultSet resultado = c
                    .devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='1'  ");
            while (resultado.next()) {
                Desafio d = new Desafio();

                int idDesafio = resultado.getInt("idDesafio");
                int idUsuario = resultado.getInt("usuarios_idusuario");
                int monto = resultado.getInt("monto");
                Date fecha = resultado.getDate("fechaHoraInicioD");
                String estadoD = resultado.getString("estadoD");
                UsuarioDAODB ud = new UsuarioDAODB();
                String usuarioDesafio = ud.getUsuario2(idUsuario,c);

                logger.debug("idDesafio= " + idDesafio);
                logger.debug("monto= " + monto);
                logger.debug("idUsuario= " + idUsuario);
                logger.debug("fecha= " + fecha);
                logger.debug("estado= " + estadoD);
                logger.debug("usuarioDesafio= " + usuarioDesafio);

                d.setIdDesafio(idDesafio);
                d.setMonto(monto);
                d.setFechaHoraInicioD(fecha);
                d.setEstado(estadoD);
                d.setIdUsuario(idUsuario);

                d.setUsuarioDesafio(usuarioDesafio);

                a.add(d);
            }
            a.get(0);

        } catch (SQLException ex) {

            throw new NoHayDesafioException();

        } catch (IndexOutOfBoundsException i) {
            throw new NoHayDesafioException();
        } catch (NoExisteUsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo getDesafiosUsuariosDisponibleBatallaNaval()");
            c.disconnect();
        }
        return a;
    }

    public ArrayList getDesafiosUsuariosDisponibleLudo()
            throws NoHayDesafioException {
        logger.debug("Entro a getDesafiosUsuariosDisponiblesLudo");
        ArrayList a = new ArrayList();

        Conexion c = new Conexion();

        try {

            ResultSet resultado = c
                    .devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='2'  ");
            while (resultado.next()) {
                Desafio d = new Desafio();

                int idDesafio = resultado.getInt("idDesafio");
                int idUsuario = resultado.getInt("usuarios_idusuario");
                int monto = resultado.getInt("monto");
                Date fecha = resultado.getDate("fechaHoraInicioD");
                String estadoD = resultado.getString("estadoD");
                UsuarioDAODB ud = new UsuarioDAODB();
                String usuarioDesafio = ud.getUsuario(idUsuario);

                logger.debug("idDesafio= " + idDesafio);
                logger.debug("monto= " + monto);
                logger.debug("idUsuario= " + idUsuario);
                logger.debug("fecha= " + fecha);
                logger.debug("estado= " + estadoD);
                logger.debug("usuarioDesafio= " + usuarioDesafio);

                d.setIdDesafio(idDesafio);
                d.setMonto(monto);
                d.setFechaHoraInicioD(fecha);
                d.setEstado(estadoD);
                d.setUsuarioDesafio(usuarioDesafio);

                a.add(d);
            }
            a.get(0);

        } catch (SQLException ex) {

            throw new NoHayDesafioException();

        } catch (IndexOutOfBoundsException i) {
            throw new NoHayDesafioException();
        } catch (NoExisteUsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo getDesafiosUsuariosDisponibleLudo()");
            c.disconnect();
        }
        return a;
    }

    public ArrayList getDesafiosUsuariosDisponibleBackgammon()
            throws NoHayDesafioException {
        logger.debug("Entro a getDesafiosUsuariosDisponibleBackgammon");
        ArrayList a = new ArrayList();

        Conexion c = new Conexion();

        try {

            ResultSet resultado = c
                    .devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='3'  ");
            while (resultado.next()) {
                Desafio d = new Desafio();

                int idDesafio = resultado.getInt("idDesafio");
                int idUsuario = resultado.getInt("usuarios_idusuario");
                int monto = resultado.getInt("monto");
                Date fecha = resultado.getDate("fechaHoraInicioD");
                String estadoD = resultado.getString("estadoD");
                UsuarioDAODB ud = new UsuarioDAODB();
                String usuarioDesafio = ud.getUsuario(idUsuario);

                logger.debug("idDesafio= " + idDesafio);
                logger.debug("monto= " + monto);
                logger.debug("idUsuario= " + idUsuario);
                logger.debug("fecha= " + fecha);
                logger.debug("estado= " + estadoD);
                logger.debug("usuarioDesafio= " + usuarioDesafio);

                d.setIdDesafio(idDesafio);
                d.setMonto(monto);
                d.setFechaHoraInicioD(fecha);
                d.setEstado(estadoD);
                d.setUsuarioDesafio(usuarioDesafio);

                a.add(d);
            }
            a.get(0);

        } catch (SQLException ex) {

            throw new NoHayDesafioException();

        } catch (IndexOutOfBoundsException i) {
            throw new NoHayDesafioException();
        } catch (NoExisteUsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo getDesafiosUsuariosDisponibleBackgammon()");
            c.disconnect();
        }
        return a;
    }

    //optimizado, devuelve la idDesafio de BatallaNaval
    public int crearDesafio(String usuario, int monto) throws NoHaySuficienteCreditoUsuarioException {
        logger.debug("Entro a crearDesafios con par�metros de entrada usuario= "+usuario+" monto= "+monto);
        UsuarioDAODB ud = new UsuarioDAODB();
        Usuario u;
        Conexion c = new Conexion();
        int idDesafio = 0;
        int idUsuario = 0;
        int idJuego = 1; // Batalla Naval
        int usuarioGanadorD = 0; // El cero representa a ningun usuario

        try {
            u = ud.findByName2(usuario,c);
            idUsuario = u.getIdUsuarioB();
            boolean creditoSuficiente;
            int credito=u.getCreditoB();
            creditoSuficiente=ud.creditoSuficiente2(credito, idUsuario,c);
            if(!creditoSuficiente){
                throw new NoHaySuficienteCreditoUsuarioException();
            }
            logger.debug("Cr�dito suficiente!");
            this.getDesafiosUsuariosDisponibleBatallaNaval(idUsuario);
            logger.debug("Ya existe desafio del usuario: " + usuario);

        } catch (NotDataFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoHayDesafioException e) {
            // ingreso en desafios
            try {
                logger.debug("primero ingreso en la tabla desafios");
                logger.debug("idDesafio= " + idDesafio);
                logger.debug("monto= " + monto);
                logger.debug("fechaHoraInicioD= " + fechaHoraInicioD);
                logger.debug("estado= En hora");

                c.ingresarNuevaTuplaDeTresColumnas2("desafios", "idDesafio",
                        "monto", "fechaHoraInicioD", "estadoD", monto, "now()",
                        "En hora");
                ResultSet r = c
                        .devolverResutado("SELECT MAX(idDesafio)FROM desafios");
                r.next();
                idDesafio = r.getInt("MAX(idDesafio)");
                // ingreso en la relacion usuarios_has_juegos_desafios
                logger.debug("luego ingreso en la relaci�n usuarios_has_juegos_desafios");
                logger.debug("idUsuario= " + idUsuario);
                logger.debug("juegos_idJuego= 1");
                logger.debug("desafios_idDesafio= "+idDesafio);
                logger.debug("usuarioGanadorD= "+usuarioGanadorD );

                c.ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas(
                        "usuarios_has_juegos_desafios", "juegos_idJuego",
                        "desafios_idDesafio", "usuarios_idUsuario",
                        "usuarioGanadorD", 1, idDesafio, idUsuario,
                        usuarioGanadorD);

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally{
            logger.debug("idDesafio= "+idDesafio);
            logger.debug("Me desconecto de la base de datos del m�todo crearDesafio");
            c.disconnect();
        }
        return idDesafio;
    }
    //optimizado
    public ArrayList getDesafiosUsuariosDisponibleBatallaNaval(int idUser)
            throws NoHayDesafioException {
        logger.debug("Entro a getDesafiosUsuariosDisponibleBatallaNaval con par�metro de entrada idUsuario= "+idUser);
        ArrayList a = new ArrayList();

        Conexion c = new Conexion();

        try {

            ResultSet resultado = c
                    .devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='1' AND usuarios_idusuario='"
                            + idUser + "'");
            while (resultado.next()) {
                Desafio d = new Desafio();

                int idDesafio = resultado.getInt("idDesafio");
                int idUsuario = resultado.getInt("usuarios_idusuario");
                int monto = resultado.getInt("monto");
                Date fecha = resultado.getDate("fechaHoraInicioD");
                String estadoD = resultado.getString("estadoD");

                d.setIdDesafio(idDesafio);
                d.setMonto(monto);
                d.setFechaHoraInicioD(fecha);
                d.setEstado(estadoD);
                d.setIdUsuario(idUsuario);
                UsuarioDAODB ud = new UsuarioDAODB();
                String usuarioDesafio = ud.getUsuario(idUsuario);
                d.setUsuarioDesafio(usuarioDesafio);

                a.add(d);
            }
            a.get(0);

        } catch (SQLException ex) {

            throw new NoHayDesafioException();

        } catch (IndexOutOfBoundsException i) {
            throw new NoHayDesafioException();
        } catch (NoExisteUsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo getDesafiosUsuariosDisponibleBatallaNaval");
            c.disconnect();
        }
        return a;
    }
    //optimizado
    public boolean aceptaronDesafio(int idUsuario) {
        logger.debug("Entro a aceptaronDesafio con par�metro de entrada idUsuario= "+idUsuario);
        Conexion c = new Conexion();
        try {
            ResultSet r = c
                    .devolverResutado("SELECT estadoD FROM desafios,usuarios_has_juegos_desafios WHERE idDesafio=desafios_idDesafio AND usuarios_idusuario='"
                            + idUsuario + "' AND estadoD='En curso'");

            if (r.first()) {
                logger.debug("aceptaron= "+true);
                return true;
            } else {
                logger.debug("aceptaron= "+false);
                return false;
            }
        } catch (SQLException e) {
            logger.debug("aceptaron= "+false);
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally{
            logger.debug("Me desconecto de la base de datos del m�todo aceptaronDesafio");
            c.disconnect();
        }

    }
    //optimizado
    public boolean desafioFinalizado(int idDesafio) throws NoExisteDesafioException{
        logger.debug("Entro a desafioFinalizado con par�metro de entrada idDesafio= "+idDesafio);
        boolean finalizado=false;
        Conexion c=new Conexion();
        try {
            ResultSet r=c.devolverResutado("SELECT estadoD FROM desafios WHERE idDesafio='"+idDesafio+"'");
            if(r.first()){
                if((r.getString("estadoD")).equals("Finalizado")){
                    finalizado=true;
                }
            }else{
                throw new NoExisteDesafioException();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("finalizado= "+finalizado);
            logger.debug("Me desconecto de la base de datos del m�todo desafioFinalizado");
            c.disconnect();
        }
        return finalizado;
    }
    //optimizado
    public int getMontoDesafio(int idDesafio) throws NoHayDesafioException{
        logger.debug("Entro a getMontoDesafio con par�metros de entrada idDesafio= "+idDesafio);
        int monto=0;
        Conexion c=new Conexion();
        try {
            ResultSet r=c.devolverResutado("SELECT monto FROM desafios WHERE idDesafio="+idDesafio);
            if(r.first()){
                monto=r.getInt("monto");
            }else{
                throw new NoHayDesafioException();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            logger.debug("monto= "+monto);
            logger.debug("Me desconecto de la base de datos del m�todo getMontoDesafio");
            c.disconnect();
        }
        return monto;

    }

    public void cancelarDesafio(int idUsuario) {
        Conexion c=new Conexion();
        PartidaDAODB p=new PartidaDAODB();
        int idDesafio=p.idPartidaNoEstaEnCurso(idUsuario, c);

        try {
			c.borrarTuplasCondicionUnAtributo("usuarios_has_juegos_desafios", "desafios_idDesafio", idDesafio);
			c.borrarTuplasCondicionUnAtributo("desafios", "idDesafio", idDesafio);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    //desafio disponibles que no estan en curso
	public boolean desafioDisponible(int idUsuario) {
		logger.debug("Entro a desafioDisponible con par�metro de entrada idUsuario= "+idUsuario);
		boolean disponible=false;
		Conexion c=new Conexion();
		ResultSet r;
		try {
			r = c.devolverResutado("SELECT desafios_idDesafio FROM usuarios_has_juegos_desafios, desafios WHERE usuarios_idusuario='"+idUsuario+"' AND desafios_idDesafio=idDesafio AND estadoD='En hora'");
			if(r.first()){
				idDesafio=r.getInt("desafios_idDesafio");
				disponible=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disponible;
	}


	public int idPartidaDisponible(int idUsuario, Conexion c) {
		logger.debug("Entro a idPartida con par�metro de entrada idUsuario= "+idUsuario);
		int idPartida=0;
		try {
			ResultSet r=c.devolverResutado("SELECT desafios_idDesafio FROM usuarios_has_juegos_desafios WHERE usuarios_idusuario='"+idUsuario+"' AND usuarioGanadorD='0'");
			if(r.first()){
				idPartida=r.getInt("desafios_idDesafio");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("idPartida= "+idPartida);
			logger.debug("Me salgo del m�todo idPartida");
		}
		return idPartida;
	}


	public boolean desafioDisponibleTodosLosUsuarios(int idUsuario){
		logger.debug("Entro a desafioDisponible con par�metro de entrada idUsuario= "+idUsuario);
		boolean noEnCurso=false;
		boolean esVirtual=false;
		UsuarioDAODB ud=new UsuarioDAODB();
		Conexion c=new Conexion();
		String usuario;
		try {
			usuario = ud.getUsuario2(idUsuario, c);
			esVirtual=ud.esUsuarioVirtual(usuario);
		} catch (NoExisteUsuarioException e1) {
			logger.debug("No existe usuario");
		}

		if(esVirtual){
			logger.debug("No es usuario virtual");
			ResultSet r;
			try {
				r = c.devolverResutado("SELECT desafios_idDesafio FROM usuarios_has_juegos_desafios, desafios WHERE usuarios_idusuario='"+idUsuario+"' AND desafios_idDesafio=idDesafio AND estadoD='En curso'");
				if(r.first()){
					logger.debug("El usuario est� en curso");
					noEnCurso=false;

				}else{
					noEnCurso=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			logger.debug("No es usuario virtual");
			noEnCurso=this.desafioDisponible(idUsuario);
		}
		return noEnCurso;
	}




}
