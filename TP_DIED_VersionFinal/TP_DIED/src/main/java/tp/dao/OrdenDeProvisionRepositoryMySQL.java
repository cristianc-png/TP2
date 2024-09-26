package tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tp.modelo.OrdenDeProvision;

public class OrdenDeProvisionRepositoryMySQL implements OrdenDeProvisionRepository {

	@Override
	public OrdenDeProvision buscarOrden(int id) {
		OrdenDeProvision orden = null;
        String sql = "SELECT * FROM ordenDeProvision WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los valores de la fila encontrada en la base de datos
            	Date fecha = rs.getDate("fecha");
            	int sucursalDestino = rs.getInt("sucursalDestino");
                Time tiempoMaximo = rs.getTime("tiempoMaximo");
                boolean pendiente = rs.getBoolean("pendiente");

                // Crear un objeto Sucursal con los datos obtenidos de la base de datos
                orden = new OrdenDeProvision(id, fecha, sucursalDestino, tiempoMaximo, pendiente);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la orden de provision por ID");
            e.printStackTrace();
        }

        return orden;
	}
	
	@Override
	public List<List<Integer>> encontrarCaminos(int sucursalDestino) {
        List<List<Integer>> caminos = new ArrayList<>();
        
        try (Connection conn = Conexion.getInstance().getConn();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT id FROM sucursal WHERE estado = true";
            ResultSet sucursalesResultSet = stmt.executeQuery(sql);

            while (sucursalesResultSet.next()) {
                int sucursalId = sucursalesResultSet.getInt("id");
                List<Integer> camino = new ArrayList<>();
                camino.add(sucursalId);
                encontrarCaminosRecursivo(conn, sucursalId, sucursalDestino, camino, caminos);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las sucursales por estado");
            e.printStackTrace();
        }
        
        //caminos.removeIf(camino -> camino.stream().anyMatch(sucursalId -> !esEstadoSucursalTrue(sucursalId)));
        caminos.removeIf(camino -> camino.size() == 1 || camino.stream().anyMatch(sucursalId -> !esEstadoSucursalTrue(sucursalId)));

        
        return caminos;
    }
	
	private boolean esEstadoSucursalTrue(int sucursalId) {
        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement("SELECT estado FROM sucursal WHERE id = ?")) {

            stmt.setInt(1, sucursalId);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean("estado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
	
	private void encontrarCaminosRecursivo(Connection conn, int sucursalActual, int sucursalDestino, List<Integer> caminoActual, List<List<Integer>> caminos) {
		if (sucursalActual == sucursalDestino) {
			caminos.add(new ArrayList<>(caminoActual));
	        return;
	    }

	    String sql = "SELECT * FROM ruta WHERE estado = true AND sucursalOrigen = " + sucursalActual;
	        
	    try (Statement stmt = conn.createStatement();
	             ResultSet rutasResultSet = stmt.executeQuery(sql)) {

	            while (rutasResultSet.next()) {
	                //int rutaId = rutasResultSet.getInt("id");
	                int siguienteSucursal = rutasResultSet.getInt("sucursalDestino");
	                if (!caminoActual.contains(siguienteSucursal)) {
	                    List<Integer> nuevoCamino = new ArrayList<>(caminoActual);
	                    nuevoCamino.add(siguienteSucursal);
	                    encontrarCaminosRecursivo(conn, siguienteSucursal, sucursalDestino, nuevoCamino, caminos);
	                }
	            }
	    } catch (SQLException e) {
	            System.out.println("Error al buscar las rutas por sucursal origen");
	            e.printStackTrace();
	        }
	    }
		
	
	@Override
	public List<OrdenDeProvision> buscarPorEstado(boolean estado) {
		// TODO Auto-generated method stub
	    List<OrdenDeProvision> ordenes = new ArrayList<OrdenDeProvision>();
	    String sql = "SELECT * FROM ordendeprovision WHERE pendiente = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setBoolean(1, estado);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            Date fecha = rs.getDate("fecha");
	            int sucursalDestino = rs.getInt("sucursalDestino");
	            Time tiempoMaximo = rs.getTime("tiempoMaximo");

	            // Crear un objeto Orden con los datos obtenidos de la base de datos
	            OrdenDeProvision orden = new OrdenDeProvision(id, fecha, sucursalDestino, tiempoMaximo, estado);
	            ordenes.add(orden);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las ordenes por estado");
	        e.printStackTrace();
	    }

	    return ordenes;
	}
	
	@Override
	public List<String> obtenerNombresSucursalesPorIDs(List<Integer> ids) {
        List<String> nombres = new ArrayList<>();

        try (Connection conn = Conexion.getInstance().getConn()) {
            String sql = "SELECT nombre FROM sucursal WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Integer id : ids) {
                stmt.setInt(1, id);
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    nombres.add(nombre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombres;
    }
	
	@Override
	public List<List<Integer>> buscarRutas(List<List<Integer>> listasDeIdsSucursales) {
        List<List<Integer>> resultado = new ArrayList<>();

        try (Connection conn = Conexion.getInstance().getConn()) {
            for (List<Integer> lista : listasDeIdsSucursales) {
                List<Integer> rutasEnLista = new ArrayList<>();
                for (int i = 0; i < lista.size() - 1; i++) {
                    int origen = lista.get(i);
                    int destino = lista.get(i + 1);
                    int rutaId = buscarRutaId(conn, origen, destino);
                    if (rutaId != -1) {
                        rutasEnLista.add(rutaId);
                    }
                }
                resultado.add(rutasEnLista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    private static int buscarRutaId(Connection conn, int origen, int destino) throws SQLException {
        String sql = "SELECT id FROM ruta WHERE sucursalOrigen = ? AND sucursalDestino = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, origen);
            stmt.setInt(2, destino);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1; // Devuelve -1 si no se encuentra la ruta
    }
    
    
    
    public int convertTimeToHours(Time time) {
        long milliseconds = time.getTime();
        return (int) (milliseconds / (60 * 60 * 1000)); // Convertir de milisegundos a horas
    }
    public int convertTimeToMinutes(Time time) {
        LocalTime localTime = time.toLocalTime();
        int minutes = localTime.getHour() * 60 + localTime.getMinute();
        return minutes;
    }

    @Override
    public List<Integer> calculateTimeSumInHours(List<List<Integer>> routeIdsLists) {
    	List<Integer> result = new ArrayList<>();

    	try (Connection conn = Conexion.getInstance().getConn()) {
    		for (int i=0; i<routeIdsLists.size(); i++) {
            	int totalMinutes = 0;
                for (int j=0; j<routeIdsLists.get(i).size(); j++) {
                	
                	String sql = "SELECT tiempoDeTransito FROM ruta WHERE id = ?";
                    try (PreparedStatement stm = conn.prepareStatement(sql)) {
                    	System.out.println(routeIdsLists.get(i) + "ruta " + i );        
                    	stm.setInt(1, routeIdsLists.get(i).get(j));
                    	ResultSet rs = stm.executeQuery();
                    	if (rs.next()) {	
                            Time tiempoDeTransito = rs.getTime("tiempoDeTransito");
                            totalMinutes += convertTimeToMinutes(tiempoDeTransito);
                        }
                    	
                    }  
                }
                int totalHours = totalMinutes / 60; // Convertir los minutos a horas
                result.add(totalHours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
        return result;
    }
    //tomas CUIDADO
    @Override
    public List<List<Integer>> condiciones (List<List<Integer>> cumplen){
		List<List<Integer>> definitiva = new ArrayList<>();
		
		try (Connection conn = Conexion.getInstance().getConn();
	             Statement stmt = conn.createStatement()) {
			// Verificar si la lista de listas no está vacía
			if (!cumplen.isEmpty()) {
			    List<Integer> ultima = cumplen.get(cumplen.size() - 1); // Obtiene la última lista
			    // Verificar si la última lista no está vacía
			    if (!ultima.isEmpty()) {
			    	List<String> ordenProvision = new ArrayList<>();
			        int provisionDestino = getIdOrdenProvision((ultima.get(ultima.size() - 1)), conn); // Obtiene el último valor de la última lista
			        String sqlProvision = "SELECT nombre_producto, cantidad FROM productosOrdenDeProvision WHERE id_ordenDeProvision = " + provisionDestino;
		            ResultSet provisionResultSet = stmt.executeQuery(sqlProvision);
		            while(provisionResultSet.next()) {
		            	String nombreProductoOrden = provisionResultSet.getString("nombre_producto");
		 	            int cantidadProductoOrden = provisionResultSet.getInt("cantidad");
		 	           for (int i = 0; i < cantidadProductoOrden; i++) {
			                ordenProvision.add(nombreProductoOrden);
			            }
		            }
		            for(int i = 0 ; i < cumplen.size(); i++) {
		            	List<Integer> actual = cumplen.get(i);
		            	  String sqlStock = "SELECT nombre_producto, cantidad FROM stock WHERE id_sucursal = " + actual.get(0); ;
		            	   ResultSet stockResultSet = stmt.executeQuery(sqlStock);
		            	  List<String> productosEnStock = new ArrayList<>(); 
		           	   while (stockResultSet.next()) {
		           	            String nombreProductoStock = stockResultSet.getString("nombre_producto");
		           	            int cantidadProductoStock = stockResultSet.getInt("cantidad");
		           	            // Agrega el producto a la lista la cantidad de veces disponible en stock
		           	            for (int j = 0; j < cantidadProductoStock; j++) {
		           	                productosEnStock.add(nombreProductoStock);
		           	            }
		           	        }
		           	   System.out.println(ordenProvision);
		           	   System.out.println(productosEnStock);
		           	   /*if (!ordenProvision.containsAll(productosEnStock)) {
		           		definitiva.add(actual);
		           	   }*/
		           	// Crear mapas para contar la cantidad de veces que aparece cada elemento en aux y aux2
		               Map<String, Integer> countMapAux = new HashMap<>();
		               Map<String, Integer> countMapAux2 = new HashMap<>();

		               for (String elemento : productosEnStock) {
		                   countMapAux.put(elemento, countMapAux.getOrDefault(elemento, 0) + 1);
		               }

		               for (String elemento : ordenProvision) {
		                   countMapAux2.put(elemento, countMapAux2.getOrDefault(elemento, 0) + 1);
		               }

		               // Verificar si aux2 está contenido en aux teniendo en cuenta la cantidad de apariciones
		               boolean contieneTodos = true;
		               for (Map.Entry<String, Integer> entry : countMapAux2.entrySet()) {
		                   String elemento = entry.getKey();
		                   int cantidadEnAux2 = entry.getValue();
		                   int cantidadEnAux = countMapAux.getOrDefault(elemento, 0);

		                   if (cantidadEnAux < cantidadEnAux2) {
		                       contieneTodos = false;
		                       break;
		                   }
		               }

		               if (contieneTodos) {
		            	   definitiva.add(actual);
		                   System.out.println("Éxito");
		               } else {
		                   System.out.println("Fallo");
		               }
		           	   
		           	   
		           	   
		           	/*if (productosEnStock.containsAll(ordenProvision)) {
		           		definitiva.add(actual);
		           	   }
		            }*/
			    }
			    }

	        }} catch (SQLException e) {
	            System.out.println("Error al buscar las sucursales por estado");
	            e.printStackTrace();
	        }
		return definitiva;
		
	}
    
    private int getIdOrdenProvision(int idSucursal, Connection conn) {
	    String sql = "SELECT id FROM ordendeprovision WHERE sucursalDestino = " + idSucursal;

	    try (Statement stmt = conn.createStatement();
	         ResultSet resultSet = stmt.executeQuery(sql)) {

	        if (resultSet.next()) {
	        	System.out.println("el id es " + resultSet.getInt("id"));
	            return resultSet.getInt("id");
	        } else {
	            return -1; // Valor de retorno indicando que no se encontró la orden de provisión
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar la orden de provisión");
	        e.printStackTrace();
	        return -1; // Valor de retorno en caso de error
	    }
	}
    
    @Override
    public boolean cambiarEstado(int id) {
    	
   	 String sql = "UPDATE ordendeprovision SET pendiente = ? WHERE id = ?";

   	    try (Connection conn = Conexion.getInstance().getConn();
   	         PreparedStatement stmt = conn.prepareStatement(sql)) {

   	        // Establecer el nuevo valor "false" para el campo "pendiente"
   	        stmt.setBoolean(1, false);

   	        // Establecer el ID de la orden que deseas actualizar
   	        stmt.setInt(2, id);
   	        
   	        int filasActualizadas = stmt.executeUpdate();

   	        if (filasActualizadas > 0) {
   	        	return true;
   	            
   	        } else {
   	        	return false;
   	         
   	        }
       } catch (SQLException e) {
           System.out.println("Error al buscar la orden de provision por ID");
           e.printStackTrace();
       }
   	    return false;
   }
}