package tp.services;

import java.util.List;
import tp.dao.ProductoRepository;
import tp.dao.ProductoRepositoryMySQL;
import tp.dao.SucursalRepository;
import tp.dao.SucursalRepositoryMySQL;
import tp.modelo.Producto;

public class ProductoService {

	
	private static ProductoService _INSTANCE;
	private ProductoRepository productoRepo;
	private SucursalRepository sucursalRepo = new SucursalRepositoryMySQL();
	
	public static ProductoService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new ProductoService();
			_INSTANCE.productoRepo = new ProductoRepositoryMySQL();
		}
		return _INSTANCE;
	}//si la instancia no existe la crea, pero solo una vez
	
	public Producto darDeAlta(Producto p) {
		
	    if (p.getNombre().length() > 20) {
	        throw new IllegalArgumentException("El nombre debe tener como máximo 20 caracteres.");
	    }

	    if (productoRepo.existeNombre(p.getNombre())) {
	        throw new IllegalArgumentException("Ya existe una sucursal con el nombre proporcionado.");
	    }
	    if (p.getDescripcion().length() > 50) {
	        throw new IllegalArgumentException("La descripcion debe tener como máximo 50 caracteres.");
	    }
	    
		return productoRepo.darDeAlta(p);
	}
	
	public void darDeBaja(String nombre) {
        // Verificar si el producto con el nombre proporcionado existe en la base de datos
        Producto producto = productoRepo.buscarPorNombre(nombre);
        if (producto == null) {
            throw new IllegalArgumentException("No existe un producto con el nombre proporcionado.");
        }
        this.sucursalRepo.eliminarProductosOrdenDeProvisionAsociados(nombre);
        // Llamar a la función darDeBaja(Producto p) para eliminar el producto
        productoRepo.darDeBaja(producto);
	}
	
	public Producto editar(Producto p) {
		return productoRepo.editar(p);
	}
	
	public Producto buscarPorNombre(String nombre) {
		return productoRepo.buscarPorNombre(nombre);
	}
	
	public List<Producto> buscarPorPrecio(double precio) {
		return productoRepo.buscarPorPrecio(precio);
	}
	
	public List<Producto> buscarPorPeso(double peso) {
		return productoRepo.buscarPorPeso(peso);
	}
}