package tp.dao;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PageRankRepositoryMySQL implements PageRankRepository {

	@Override
	public List<Integer> encontrarSucursales() {
		List<Integer> sucursales = new ArrayList<>();
		String sql = "SELECT id FROM sucursal";
		
		try (Connection conn = Conexion.getInstance().getConn();
		     PreparedStatement stmt = conn.prepareStatement(sql)){
			
			ResultSet resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
                int id = resultSet.getInt("id");
                sucursales.add(id);
            }
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return sucursales;
	}
	
	@Override
	public List<List<Integer>> encontrarCaminosHaciaPuerto(int sucursalDestino) {
	    List<List<Integer>> caminos = encontrarCaminos(sucursalDestino);
	    List<List<Integer>> caminosHaciaPuerto = new ArrayList<>();

	    try (Connection conn = Conexion.getInstance().getConn();
	         Statement stmt = conn.createStatement()) {

	        for (List<Integer> camino : caminos) {
	            int primerSucursalId = camino.get(0);
	            String sql = "SELECT nombre FROM sucursal WHERE id = " + primerSucursalId;
	            ResultSet nombreResultSet = stmt.executeQuery(sql);

	            if (nombreResultSet.next()) {
	                String nombreSucursal = nombreResultSet.getString("nombre");
	                if ("Puerto".equalsIgnoreCase(nombreSucursal)) {
	                    caminosHaciaPuerto.add(camino);
	                }
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar el nombre de la sucursal");
	        e.printStackTrace();
	    }

	    return caminosHaciaPuerto;
	}
	
	
	@Override
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
	/*
	public List<List<Integer>> encontrarCaminos(int sucursalDestino){
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
	}*/
	
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
	
	
	@Override
	public String buscarPorId(int id) {
		String n = null;
        String sql = "SELECT * FROM sucursal WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los valores de la fila encontrada en la base de datos
                String nombre = rs.getString("nombre");
                n = nombre;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la sucursal por ID");
            e.printStackTrace();
        }

        return n;
	}

}
