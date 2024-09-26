package tp.dao;

import java.util.List;

import tp.modelo.Producto;

public interface ProductoRepository {

	public Producto darDeAlta(Producto p);
	public void darDeBaja(Producto p);
	public Producto editar(Producto p);
	public Producto buscarPorNombre(String nombre);
	public List<Producto> buscarPorPrecio(double precio);
	public List<Producto> buscarPorPeso(double peso);
	public boolean existeNombre(String nombre);
	
}
