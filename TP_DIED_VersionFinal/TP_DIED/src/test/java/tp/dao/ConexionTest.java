package tp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mysql.cj.jdbc.Driver;

class ConexionTest {

	 @InjectMocks
	    private Conexion conexion;

	 @Mock
	    private Enumeration<java.sql.Driver> mockDrivers;

	    @Mock
	    private Connection mockConnection;
	    
	    @Mock
	    private DriverManager mockDriverManager;
	    
	    @Mock
	    private Driver mockDriver;
	    
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
    @Test
    public void testGetConn_ConexionExistente() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.isClosed()).thenReturn(false);
        conexion.conn = mockConnection;

        assertEquals(mockConnection, conexion.getConn(), "La conexión existente debe ser devuelta");
    }

    @Test
    public void testCrearConexion_Exito() {
        Connection conn = null;
		try {
			conn = conexion.crearConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        assertNotNull(conn, "La conexión no debe ser nula");
    }

    @Test
    public void testGetConn_ConexionFalla() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.isClosed()).thenReturn(true); // Simulamos que la conexión está cerrada

        // Creamos una instancia de la clase a probar
        Conexion conexion = new Conexion();
        conexion.conn = mockConnection; // Establecemos la conexión simulada en la instancia

        try {
	        // Intentar obtener una conexión (simulará un error)
	        Connection conn = conexion.getConn();
	        if (conn != null) {
	            conn.close(); // Cerrar la conexión simulada
	        }
	    } catch (SQLException e) {
	    	assertEquals("Error en la conexion", e.getMessage());
	    }
    }
    
    @Test
    public void testConexionFallida() throws SQLException, ClassNotFoundException {
        // Crear un mock de Conexion
        Conexion conexionMock = mock(Conexion.class);
        
        // Configurar el mock para que lance una SQLException al crear la conexión
        when(conexionMock.crearConexion()).thenThrow(new SQLException("Error de conexión"));

        // Crear una instancia de la clase que utiliza la conexión
        Conexion conexion = new Conexion();

      
        try {
        	 conexion.crearConexion();
        } catch (SQLException e) {
            // Verificar que se lanzó la excepción esperada
            assertEquals("Error de conexión", e.getMessage());
        }
    }
    
}
