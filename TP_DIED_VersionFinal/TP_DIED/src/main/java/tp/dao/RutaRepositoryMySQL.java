package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;
import tp.modelo.Ruta;

public class RutaRepositoryMySQL implements RutaRepository {

	@Override
	public boolean existeId(int id) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) as count FROM ruta WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                existe = count > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar si existe el ID");
            e.printStackTrace();
        }

        return existe;
    }
	
	@Override
	public Ruta darDeAlta(Ruta r) {
		String sql = "INSERT INTO ruta (id, sucursalOrigen, sucursalDestino, tiempoDeTransito, capacidad, estado) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, r.getId());
	        stmt.setInt(2, r.getOrigen());
	        stmt.setInt(3, r.getDestino());
	        stmt.setTime(4, r.getTiempoDeTransito());
	        stmt.setDouble(5, r.getCapacidad());
	        stmt.setBoolean(6, r.isEstado());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Error al dar de alta la ruta");
	        e.printStackTrace();
	    }

	    return r;
    }

	@Override
	public void darDeBaja(Ruta r) {
		String sql = "DELETE FROM ruta WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja la ruta");
            e.printStackTrace();
        }
	}

	@Override
	public Ruta editar(Ruta r) {
		String sql = "UPDATE ruta SET sucursalOrigen = ?, sucursalDestino = ?, tiempoDeTransito = ?, capacidad = ?, estado = ? WHERE id = ?";
	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, r.getOrigen());
	        stmt.setInt(2, r.getDestino());
	        stmt.setTime(3, r.getTiempoDeTransito());
	        stmt.setDouble(4, r.getCapacidad());
	        stmt.setBoolean(5, r.isEstado());
	        stmt.setInt(6, r.getId());
	        stmt.executeUpdate();

	        return r;

	    } catch (SQLException e) {
	        System.out.println("Error al editar la ruta");
	        e.printStackTrace();
	        return null; // Si ocurre un error, devolvemos null para indicar que no se pudo editar.
	    }
	}

	@Override
	public Ruta buscarPorId(int id) {
		Ruta ruta = null;
		String sql = "SELECT * FROM ruta WHERE id = ?";

		        try (Connection conn = Conexion.getInstance().getConn();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setInt(1, id);
		            ResultSet rs = stmt.executeQuery();

		            if (rs.next()) {
		                // Obtener los valores de la fila encontrada en la base de datos
		                int sucursalOrigen = rs.getInt("sucursalOrigen");
		                int sucursalDestino = rs.getInt("sucursalDestino");
		                Time tiempoDeTransito = rs.getTime("tiempoDeTransito");
		                double capacidad = rs.getDouble("capacidad");
		                boolean estado = rs.getBoolean("estado");

		                // Crear un objeto Sucursal con los datos obtenidos de la base de datos
		                ruta = new Ruta(id, sucursalOrigen, sucursalDestino, tiempoDeTransito, capacidad, estado);
		            }
		            
		        } catch (SQLException e) {
		            System.out.println("Error al buscar la sucursal por ID");
		            e.printStackTrace();
		        }

		        return ruta;
	}

	@Override
	public List<Ruta> buscarPorOrigen(int sucursalOrigen) {
	    List<Ruta> rutas = new ArrayList<>();
	    String sql = "SELECT ruta.id, ruta.sucursalOrigen, ruta.sucursalDestino, ruta.tiempoDeTransito, ruta.capacidad, ruta.estado, sucursal.nombre "
	            + "FROM ruta "
	            + "JOIN sucursal ON ruta.sucursalDestino = sucursal.id "
	            + "WHERE ruta.sucursalOrigen = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, sucursalOrigen);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int sucursalDestino = rs.getInt("sucursalDestino");
	            Time tiempoDeTransito = rs.getTime("tiempoDeTransito");
	            double capacidad = rs.getDouble("capacidad");
	            boolean estado = rs.getBoolean("estado");
	            //String nombreSucursalDestino = rs.getString("nombre");

	            // Obtener la instancia de la sucursal de origen mediante la función obtenerSucursalPorId(sucursalOrigen)
	            // La instancia de la sucursal destino ya la obtuvimos directamente de la consulta SQL.

	            Ruta ruta = new Ruta(id, sucursalOrigen, sucursalDestino, tiempoDeTransito, capacidad, estado);
	            rutas.add(ruta);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar las rutas por sucursal de origen");
	        e.printStackTrace();
	    }

	    return rutas;
	}

	@Override
	public List<Ruta> buscarPorDestino(int sucursalDestino) {
	    List<Ruta> rutas = new ArrayList<>();
	    String sql = "SELECT * FROM ruta WHERE sucursalDestino = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, sucursalDestino);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int sucursalOrigen = rs.getInt("sucursalOrigen");
	            Time tiempoDeTransito = rs.getTime("tiempoDeTransito");
	            double capacidad = rs.getDouble("capacidad");
	            boolean estado = rs.getBoolean("estado");
	    
	            Ruta ruta = new Ruta(id, sucursalOrigen, sucursalDestino, tiempoDeTransito, capacidad, estado);
	            rutas.add(ruta);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar las rutas por sucursal de destino");
	        e.printStackTrace();
	    }

	    return rutas;
	}

	@Override
	public List<Ruta> buscarPorTiempoDeTransito(Time tiempo) {
		List<Ruta> rutas = new ArrayList<Ruta>();
	    String sql = "SELECT * FROM ruta WHERE tiempoDeTransito = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setTime(1, tiempo);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int sucursalOrigen = rs.getInt("sucursalOrigen");
	            int sucursalDestino = rs.getInt("sucursalDestino");
	            double capacidad = rs.getDouble("capacidad");
	            boolean estado = rs.getBoolean("estado");

	            // Crear un objeto Sucursal con los datos obtenidos de la base de datos
	            Ruta ruta = new Ruta(id, sucursalOrigen, sucursalDestino, tiempo, capacidad, estado);
	            rutas.add(ruta);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las rutas por tiempo de transito");
	        e.printStackTrace();
	    }

	    return rutas;
	}

	@Override
	public List<Ruta> buscarPorCapacidad(double capacidad) {
		List<Ruta> rutas = new ArrayList<>();
	    String sql = "SELECT * FROM ruta WHERE capacidad = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setDouble(1, capacidad);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int sucursalOrigen = rs.getInt("sucursalOrigen");
	            int sucursalDestino = rs.getInt("sucursalDestino");
	            Time tiempoDeTransito = rs.getTime("tiempoDeTransito");
	            boolean estado = rs.getBoolean("estado");
	    
	            Ruta ruta = new Ruta(id, sucursalOrigen, sucursalDestino, tiempoDeTransito, capacidad, estado);
	            rutas.add(ruta);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar las rutas por sucursal de capacidad");
	        e.printStackTrace();
	    }

	    return rutas;
	}

	@Override
	public List<Ruta> buscarPorEstado(boolean estado) {
		List<Ruta> rutas = new ArrayList<Ruta>();
	    String sql = "SELECT * FROM ruta WHERE estado = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setBoolean(1, estado);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	int id = rs.getInt("id");
	            int sucursalOrigen = rs.getInt("sucursalOrigen");
	            int sucursalDestino = rs.getInt("sucursalDestino");
	            Time tiempoDeTransito = rs.getTime("tiempoDeTransito");
	            double capacidad = rs.getDouble("capacidad");
	        	
	            Ruta ruta = new Ruta(id, sucursalOrigen, sucursalDestino, tiempoDeTransito, capacidad, estado);
	            rutas.add(ruta);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las rutas por estado");
	        e.printStackTrace();
	    }

	    return rutas;
	}

}
