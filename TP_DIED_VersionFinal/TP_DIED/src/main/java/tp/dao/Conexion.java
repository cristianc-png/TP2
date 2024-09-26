package tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	Connection conn;
	private static Conexion _INSTANCE;
	
	private static final String Controlador = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tpdied";
	private static final String Usuario = "root";
	private static final String Clave = "tomasito";
	
	public static Conexion getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new Conexion();
		}
		return _INSTANCE;
	}
	
	public Connection getConn() throws SQLException {
	    if (conn == null || conn.isClosed()) {
	      try {
			this.crearConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    return this.conn;
	}

	public Connection crearConexion() throws ClassNotFoundException, SQLException  {
	    try {
	        Class.forName(getControlador());
	        conn = DriverManager.getConnection(getUrl(), Usuario, Clave);
	        //System.out.println("Conexion OK");
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error al cargar el controlador");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Error en la conexion");
	        e.printStackTrace();
	    }

	    return conn;
	}

	public String getControlador() {
		return Controlador;
	}

	public static String getUrl() {
		return URL;
	}
	
}
