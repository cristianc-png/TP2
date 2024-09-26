package tp.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tp.modelo.Stock;
import tp.modelo.Sucursal;

public class StockRepositoryMySQL implements StockRepository {

	@Override
	public void actualizarStock(Stock s) {
		 String sql = "UPDATE stock SET cantidad = ? WHERE id_sucursal = ? AND nombre_producto = ?";

		    try (Connection conn = Conexion.getInstance().getConn();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        stmt.setInt(1, s.getCantidad());
		        stmt.setInt(2, s.getSucursal()); 
		        stmt.setString(3, s.getProducto());

		        stmt.executeUpdate();

		    } catch (SQLException e) {
		        System.out.println("Error al editar el stock");
		        e.printStackTrace();
		    }
	}
	
	@Override
	public Stock buscarStock(int id, String nombre) {
		Stock stock = null;
        String sql = "SELECT * FROM stock WHERE id_sucursal = ? AND nombre_producto = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener los valores de la fila encontrada en la base de datos
                int cantidad = rs.getInt("cantidad");

                stock = new Stock(id, nombre, cantidad);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la sucursal por ID");
            e.printStackTrace();
        }

        return stock;
	}
	
	@Override
	public void darDeBajaProducto(int id, String nombre) {
		String sql = "DELETE FROM stock WHERE id_sucursal = ? AND nombre_producto = ?";

        try (Connection conn = Conexion.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja el producto");
            e.printStackTrace();
        }
	}
	
	@Override
	public void darDeAltaProducto(Stock s) {
		String sql = "INSERT INTO stock (id_sucursal, nombre_producto, cantidad) VALUES (?, ?, ?)";

		try (Connection conn = Conexion.getInstance().getConn();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        stmt.setInt(1, s.getSucursal());
		        stmt.setString(2, s.getProducto());
		        stmt.setInt(3, s.getCantidad());
		        stmt.executeUpdate();

		    } catch (SQLException e) {
		        System.out.println("Error al dar de alta la sucursal");
		        e.printStackTrace();
		    }
	}

	@Override
	public List<Stock> verStock(Sucursal s){
		List<Stock> productos = new ArrayList<>();
		int id_sucursal = s.getId();
	    String sql = "SELECT * FROM stock WHERE id_sucursal = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id_sucursal);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String nombre_producto = rs.getString("nombre_producto");
	            int cantidad = rs.getInt("cantidad");
	    
	            Stock stock = new Stock(id_sucursal, nombre_producto, cantidad);
	            productos.add(stock);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error");
	        e.printStackTrace();
	    }

	    return productos;
	}
	
	@Override
	public List<Stock> verStock(String nombre){
		List<Stock> productos = new ArrayList<>();
	    String sql = "SELECT * FROM stock WHERE nombre_producto = ?";

	    try (Connection conn = Conexion.getInstance().getConn();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, nombre);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id_sucursal = rs.getInt("id_sucursal");
	            int cantidad = rs.getInt("cantidad");
	    
	            Stock stock = new Stock(id_sucursal, nombre, cantidad);
	            productos.add(stock);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error");
	        e.printStackTrace();
	    }

	    return productos;
	}
}
