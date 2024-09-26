package tp.dao;

import tp.modelo.Stock;
import tp.modelo.Sucursal;

import java.util.List;

public interface StockRepository {
	public void actualizarStock(Stock s);
	public Stock buscarStock(int id, String nombre);
	public void darDeAltaProducto(Stock s);
	public void darDeBajaProducto(int id, String nombre);
	public List<Stock> verStock(Sucursal s);
	public List<Stock> verStock(String nombre);
}
