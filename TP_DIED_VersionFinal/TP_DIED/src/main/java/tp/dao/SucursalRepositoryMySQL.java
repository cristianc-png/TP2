package tp.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.sql.Date;

import tp.modelo.OrdenDeProvision;
import tp.modelo.ProductosOrdenDeProvision;
import tp.modelo.Ruta;
import tp.modelo.Stock;
import tp.modelo.Sucursal;

public class SucursalRepositoryMySQL implements SucursalRepository {

	@Override
	public boolean existeId(int id) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) as count FROM sucursal WHERE id = ?";

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
	public boolean existeNombre(String nombre) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) as count FROM sucursal WHERE nombre = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                existe = count > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar si existe el nombre");
            e.printStackTrace();
        }

        return existe;
    }
	
	@Override
	public Sucursal darDeAlta(Sucursal s) {
		String sql = "INSERT INTO sucursal (id, nombre, horarioApertura, horarioCierre, estado) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, s.getId());
	        stmt.setString(2, s.getNombre());
	        stmt.setTime(3, s.getHorarioApertura());
	        stmt.setTime(4, s.getHorarioCierre());
	        stmt.setBoolean(5, s.isEstado());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Error al dar de alta la sucursal");
	        e.printStackTrace();
	    }

	    return s;
	}

	@Override
	public void eliminarProductosOrdenDeProvisionAsociados(String nombre){
		StockRepository stockRepo = new StockRepositoryMySQL();
		List<Stock> stockEliminar = stockRepo.verStock(nombre);
        List<ProductosOrdenDeProvision> productosOrdenEliminar = obtenerProductosOrdenPorNombre(nombre);
        
        for(int i = 0; i<productosOrdenEliminar.size(); i++) {
        	darDeBajaProductosOrdenDeProvision(productosOrdenEliminar.get(i));
        }
        for(int i = 0; i<stockEliminar.size(); i++) {
        	stockRepo.darDeBajaProducto(stockEliminar.get(i).getSucursal(), stockEliminar.get(i).getProducto());
        }
	}
	
	@Override
	public void darDeBaja(Sucursal s) {
		String sql = "DELETE FROM sucursal WHERE id = ?";
		StockRepository stockRepo = new StockRepositoryMySQL();
        List<Stock> stockEliminar = stockRepo.verStock(s);
        List<OrdenDeProvision> ordenesEliminar = obtenerOrdenesPorSucursalDestino(s.getId());
        List<ProductosOrdenDeProvision> productosOrdenEliminar = obtenerProductosOrdenePorId(s.getId());
        
        
        for(int i = 0; i<productosOrdenEliminar.size(); i++) {
        	darDeBajaProductosOrdenDeProvision(productosOrdenEliminar.get(i));
        }
        for(int i = 0; i<stockEliminar.size(); i++) {
        	stockRepo.darDeBajaProducto(s.getId(), stockEliminar.get(i).getProducto());
        }
        
        for(int i = 0; i<ordenesEliminar.size(); i++) {
        	darDeBajaOrden(ordenesEliminar.get(i).getId());
        }
        
        RutaRepository rutaRepo = new RutaRepositoryMySQL();
        List<Ruta> rutasEliminar = rutaRepo.buscarPorOrigen(s.getId());
        List<Ruta> rutasEliminar2 = rutaRepo.buscarPorDestino(s.getId());
        
        
        for(int i = 0; i<rutasEliminar.size(); i++) {
        	rutaRepo.darDeBaja(rutasEliminar.get(i));
        }
        for(int i = 0; i<rutasEliminar2.size(); i++) {
        	rutaRepo.darDeBaja(rutasEliminar2.get(i));
        }

        try (Connection conn = Conexion.getInstance().getConn();

             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
            stmt.setInt(1, s.getId());
            stmt.executeUpdate();
            

        } catch (SQLException e) {
            System.out.println("Error al dar de baja la sucursal");
            e.printStackTrace();
        }
	}

	@Override
	public Sucursal editar(Sucursal s) {
		String sql = "UPDATE sucursal SET nombre = ?, horarioApertura = ?, horarioCierre = ?, estado = ? WHERE id = ?";
	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, s.getNombre());
	        stmt.setTime(2, s.getHorarioApertura());
	        stmt.setTime(3, s.getHorarioCierre());
	        stmt.setBoolean(4, s.isEstado());
	        stmt.setInt(5, s.getId());
	        stmt.executeUpdate();

	        return s; // Devolvemos la sucursal modificada.

	    } catch (SQLException e) {
	        System.out.println("Error al editar la sucursal");
	        e.printStackTrace();
	        return null; // Si ocurre un error, devolvemos null para indicar que no se pudo editar.
	    }
	}

	@Override
	public Sucursal buscarPorId(int id) {
		Sucursal sucursal = null;
        String sql = "SELECT * FROM sucursal WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los valores de la fila encontrada en la base de datos
                String nombre = rs.getString("nombre");
                Time horarioApertura = rs.getTime("horarioApertura");
                Time horarioCierre = rs.getTime("horarioCierre");
                boolean estado = rs.getBoolean("estado");

                // Crear un objeto Sucursal con los datos obtenidos de la base de datos
                sucursal = new Sucursal(id, nombre, horarioApertura, horarioCierre, estado);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la sucursal por ID");
            e.printStackTrace();
        }

        return sucursal;
	}

	@Override
	public Sucursal buscarPorNombre(String nombre) {
		Sucursal sucursal = null;
        String sql = "SELECT * FROM sucursal WHERE nombre = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los valores de la fila encontrada en la base de datos
                int id = rs.getInt("id");
                Time horarioApertura = rs.getTime("horarioApertura");
                Time horarioCierre = rs.getTime("horarioCierre");
                boolean estado = rs.getBoolean("estado");

                // Crear un objeto Sucursal con los datos obtenidos de la base de datos
                sucursal = new Sucursal(id, nombre, horarioApertura, horarioCierre, estado);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la sucursal por nombre");
            e.printStackTrace();
        }

        return sucursal;
	}
	
	@Override
	public List<Sucursal> buscarPorHorarioApertura(Time apertura) {
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
	    String sql = "SELECT * FROM sucursal WHERE horarioApertura = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setTime(1, apertura);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            Time horarioCierre = rs.getTime("horarioCierre");
	            boolean estado = rs.getBoolean("estado");

	            // Crear un objeto Sucursal con los datos obtenidos de la base de datos
	            Sucursal sucursal = new Sucursal(id, nombre, apertura, horarioCierre, estado);
	            sucursales.add(sucursal);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las sucursales por horario de apertura");
	        e.printStackTrace();
	    }

	    return sucursales;
	}

	@Override
	public List<Sucursal> buscarPorHorarioCierre(Time cierre) {
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
	    String sql = "SELECT * FROM sucursal WHERE horarioCierre = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setTime(1, cierre);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            Time horarioApertura = rs.getTime("horarioApertura");
	            boolean estado = rs.getBoolean("estado");

	            // Crear un objeto Sucursal con los datos obtenidos de la base de datos
	            Sucursal sucursal = new Sucursal(id, nombre, horarioApertura, cierre, estado);
	            sucursales.add(sucursal);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las sucursales por horario de apertura");
	        e.printStackTrace();
	    }

	    return sucursales;
	}
	
	@Override
	public List<Sucursal> buscarPorEstado(boolean estado) {
	    List<Sucursal> sucursales = new ArrayList<Sucursal>();
	    String sql = "SELECT * FROM sucursal WHERE estado = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setBoolean(1, estado);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            Time horarioApertura = rs.getTime("horarioApertura");
	            Time horarioCierre = rs.getTime("horarioCierre");

	            // Crear un objeto Sucursal con los datos obtenidos de la base de datos
	            Sucursal sucursal = new Sucursal(id, nombre, horarioApertura, horarioCierre, estado);
	            sucursales.add(sucursal);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar las sucursales por estado");
	        e.printStackTrace();
	    }

	    return sucursales;
	}
	
	@Override
	public void ordenDeProvision(OrdenDeProvision orden) {
		String sql = "INSERT INTO ordenDeProvision (id, fecha, sucursalDestino, tiempoMaximo, pendiente) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, orden.getId());
	        stmt.setDate(2, orden.getFecha());
	        stmt.setInt(3, orden.getSucursalOrigen());
	        stmt.setTime(4, orden.getTiempoMaximo());
	        stmt.setBoolean(5, orden.isPendiente());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Error al dar de alta la orden de provision");
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void agregarProductoOrden(ProductosOrdenDeProvision productoOrden) {
		String sql = "INSERT INTO productosOrdenDeProvision (id_ordenDeProvision, nombre_producto, cantidad) VALUES (?, ?, ?)";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, productoOrden.getId_ordenDeProvision());
	        stmt.setString(2, productoOrden.getNombre_producto());
	        stmt.setInt(3, productoOrden.getCantidad());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Error al dar de alta la orden de provision");
	        e.printStackTrace();
	    }
	}
	
	@Override
	public List<OrdenDeProvision> obtenerOrdenesPorSucursalDestino(int sucursalDestino) {
	    List<OrdenDeProvision> ordenes = new ArrayList<>();
	    String sql = "SELECT * FROM ordenDeProvision WHERE sucursalDestino = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, sucursalDestino);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            Date fecha = rs.getDate("fecha");
	            Time tiempoMaximo = rs.getTime("tiempoMaximo");
	            boolean pendiente = rs.getBoolean("pendiente");

	            OrdenDeProvision orden = new OrdenDeProvision(id, fecha, sucursalDestino, tiempoMaximo, pendiente);
	            ordenes.add(orden);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener las órdenes por sucursal de destino");
	        e.printStackTrace();
	    }

	    return ordenes;
	}
	
	@Override
	public List<ProductosOrdenDeProvision> obtenerProductosOrdenPorNombre(String nombre) {
	    List<ProductosOrdenDeProvision> productodOrden = new ArrayList<>();
	    String sql = "SELECT * FROM productosOrdenDeProvision WHERE nombre_producto = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nombre);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	int id = rs.getInt("id_ordenDeProvision");
	            int cantidad = rs.getInt("cantidad");

	            ProductosOrdenDeProvision po = new ProductosOrdenDeProvision(id, nombre, cantidad);
	            productodOrden.add(po);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los productos de la orden de provision");
	        e.printStackTrace();
	    }

	    return productodOrden;
	}
	
	@Override
	public List<ProductosOrdenDeProvision> obtenerProductosOrdenePorId(int id) {
	    List<ProductosOrdenDeProvision> productodOrden = new ArrayList<>();
	    String sql = "SELECT * FROM productosOrdenDeProvision WHERE id_ordenDeProvision = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int cantidad = rs.getInt("cantidad");
	            String nombre_producto = rs.getString("nombre_producto");

	            ProductosOrdenDeProvision po = new ProductosOrdenDeProvision(id, nombre_producto, cantidad);
	            productodOrden.add(po);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los productos de la orden de provision");
	        e.printStackTrace();
	    }

	    return productodOrden;
	}
	
	@Override
	public void darDeBajaProductosOrdenDeProvision(ProductosOrdenDeProvision producto) {
		String sql = "DELETE FROM productosOrdenDeProvision WHERE id_ordenDeProvision = ? AND nombre_producto = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, producto.getId_ordenDeProvision());
            stmt.setString(2, producto.getNombre_producto());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja los productos de la orden de provision");
            e.printStackTrace();
        }
	}
	
	@Override
	public void darDeBajaOrden(int id) {
		String sql = "DELETE FROM ordenDeProvision WHERE id = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja la orden de provision");
            e.printStackTrace();
        }
	}
	
	@Override
	public boolean existeIdOrden(int id) {
		boolean existe = false;
        String sql = "SELECT COUNT(*) as count FROM ordenDeProvision WHERE id = ?";

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
	public boolean estadoSucursal(int sucursalId) {
        boolean estado = false;

        try (Connection connection = Conexion.getInstance().getConn()) {
            String query = "SELECT estado FROM sucursal WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, sucursalId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        estado = resultSet.getBoolean("estado");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estado;
    }
	
	@Override
	public boolean estadoRuta(int rutaId) {
        boolean estado = false;

        try (Connection connection = Conexion.getInstance().getConn()) {
            String query = "SELECT estado FROM ruta WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, rutaId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        estado = resultSet.getBoolean("estado");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estado;
    }
}
