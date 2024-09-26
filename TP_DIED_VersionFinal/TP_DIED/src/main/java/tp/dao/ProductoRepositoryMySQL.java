package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tp.modelo.Producto;

public class ProductoRepositoryMySQL implements ProductoRepository {


	@Override
	public void darDeBaja(Producto p) {
		String sql = "DELETE FROM productos WHERE nombre = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja el producto");
            e.printStackTrace();
        }
	}

	@Override
	public Producto editar(Producto p) {
		
		String sql = "UPDATE productos SET descripcion = ?, precio_unitario = ?, pesokg = ? WHERE nombre = ?";
	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, p.getDescripcion() );
	        stmt.setDouble(2, p.getPrecioUnitario());
	        stmt.setDouble(3,p.getPrecioKg());
	        stmt.setString(4, p.getNombre());
	        stmt.executeUpdate();

	        return p;

	    } catch (SQLException e) {
	        System.out.println("Error al editar el producto");
	        e.printStackTrace();
	        return null; // Si ocurre un error, devolvemos null para indicar que no se pudo editar.
	    }
	}

	@Override
	public Producto buscarPorNombre(String nombre) {
		Producto producto = null;
        String sql = "SELECT * FROM productos WHERE nombre = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los valores de la fila encontrada en la base de datos
                String descripcion = rs.getString("descripcion");
                double precioUnitario = rs.getDouble("precio_unitario");
                double pesoKg = rs.getDouble("pesoKg");

                // Crear un objeto Sucursal con los datos obtenidos de la base de datos
                producto = new Producto(nombre, descripcion, precioUnitario, pesoKg);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el producto por nombre");
            e.printStackTrace();
        }

        return producto;
	}

	@Override
	public List<Producto> buscarPorPrecio(double precio) {
		List<Producto> productos = new ArrayList<Producto>();
	    String sql = "SELECT * FROM productos WHERE precio_unitario = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setDouble(1, precio);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String nombre = rs.getString("nombre");
	            String descripcion = rs.getString("descripcion");
	            double peso = rs.getDouble("pesoKg");

	            // Crear un objeto Producto con los datos obtenidos de la base de datos
	            Producto producto = new Producto(nombre, descripcion, precio, peso);
	            productos.add(producto);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar productos por el precio unitario");
	        e.printStackTrace();
	    }

	    return productos;
	}

	@Override
	public List<Producto> buscarPorPeso(double peso) {
		List<Producto> productos = new ArrayList<Producto>();
	    String sql = "SELECT * FROM productos WHERE pesoKg = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setDouble(1, peso);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String nombre = rs.getString("nombre");
	            String descripcion = rs.getString("descripcion");
	            double precio = rs.getDouble("precio_unitario");

	            // Crear un objeto Producto con los datos obtenidos de la base de datos
	            Producto producto = new Producto(nombre, descripcion, precio, peso);
	            productos.add(producto);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar productos por el peso en kg");
	        e.printStackTrace();
	    }

	    return productos;
	}

	@Override
	public boolean existeNombre(String nombre) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) as count FROM productos WHERE nombre = ?";

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
	
	public Producto darDeAlta(Producto p) {
		String sql = "INSERT INTO productos (nombre, descripcion, precio_unitario, pesokg) VALUES (?, ?, ?, ?)";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	    	 stmt.setString(1, p.getNombre());
		     stmt.setString(2, p.getDescripcion());
		     stmt.setDouble(3, p.getPrecioUnitario());
	         stmt.setDouble(4, p.getPrecioKg());
 	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error al dar de alta el producto");
	        e.printStackTrace();
	    }

	    return p;
	}

}