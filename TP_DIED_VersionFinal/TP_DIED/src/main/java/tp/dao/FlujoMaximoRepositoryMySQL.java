package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlujoMaximoRepositoryMySQL implements FlujoMaximoRepository{
	
	public List<List<Integer>> encontrarCaminos(int sucursalDestino) {
	    List<List<Integer>> caminos = new ArrayList<>();
	    
	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ruta WHERE estado = true AND sucursalOrigen = 1");
	         ResultSet rutasResultSet = stmt.executeQuery()) {

	        while (rutasResultSet.next()) {
	            int siguienteSucursal = rutasResultSet.getInt("sucursalDestino");
	            List<Integer> camino = new ArrayList<>();
	            camino.add(1); // Agregar la sucursalOrigen, que es igual a 1
	            camino.add(siguienteSucursal);
	            encontrarCaminosRecursivo(conn, siguienteSucursal, sucursalDestino, camino, caminos);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las rutas por sucursal origen");
	        e.printStackTrace();
	    }
	    
	    caminos.removeIf(camino -> camino.size() == 1 || camino.stream().anyMatch(sucursalId -> !esEstadoSucursalTrue(sucursalId)));
	    return caminos;
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
	
	public List<Map<Integer, List<Integer>>> obtenerCapacidadMinima(List<List<Integer>> caminos) {
        List<Map<Integer, List<Integer>>> resultados = new ArrayList<>();

        try (Connection conn = Conexion.getInstance().getConn();) {
            for (List<Integer> camino : caminos) {
                int capacidadMinima = Integer.MAX_VALUE;
                List<Integer> caminoConCapacidadMinima = new ArrayList<>();
                caminoConCapacidadMinima.addAll(camino);

                for (int i = 0; i < camino.size() - 1; i++) {
                    int inicio = camino.get(i);
                    int destino = camino.get(i + 1);

                    String sql = "SELECT capacidad FROM ruta WHERE sucursalOrigen = ? AND sucursalDestino = ?";
                    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                        preparedStatement.setInt(1, inicio);
                        preparedStatement.setInt(2, destino);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            int capacidad = resultSet.getInt("capacidad");
                            if (capacidad < capacidadMinima) {
                                capacidadMinima = capacidad;
                            }
                        }
                    }
                }

                Map<Integer, List<Integer>> resultadoCamino = new HashMap<>();
                resultadoCamino.put(capacidadMinima, caminoConCapacidadMinima);
                resultados.add(resultadoCamino);
            }

            // Ordenar los resultados por capacidad mínima de mayor a menor
            resultados.sort((a, b) -> b.keySet().iterator().next().compareTo(a.keySet().iterator().next()));
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return resultados;
    }
	
	public int encontrarSucursalCentro() {
	   int idcentro = 0;
	    String sql = "SELECT id FROM sucursal WHERE nombre = 'Centro'"; // Cambiar 'nombre' al nombre de la columna correspondiente

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)){

	        ResultSet resultSet = stmt.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            idcentro = id;
	        }

	    } catch(SQLException e){
	        e.printStackTrace();
	    }
	    return idcentro;
	}

}
