package tp.services;

import java.sql.Time;
import java.util.List;

import tp.dao.SucursalRepository;
import tp.dao.SucursalRepositoryMySQL;
import tp.dao.StockRepository;
import tp.dao.StockRepositoryMySQL;
import tp.modelo.OrdenDeProvision;
import tp.modelo.ProductosOrdenDeProvision;
import tp.modelo.Stock;
import tp.modelo.Sucursal;

public class SucursalService {

	private SucursalRepository sucursalRepo;
	private StockRepository stockRepo = new StockRepositoryMySQL();
	
	private static SucursalService _INSTANCE;
	
	public static SucursalService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new SucursalService();
			_INSTANCE.sucursalRepo = new SucursalRepositoryMySQL();
		}
		return _INSTANCE;
	}//si la instancia no existe la crea, pero solo una vez
	
	public Sucursal darDeAlta(Sucursal s) {
		if (s.getId() <= 0) {
	        throw new IllegalArgumentException("El ID debe ser un número entero positivo.");
	    }

	    if (s.getNombre().length() > 20) {
	        throw new IllegalArgumentException("El nombre debe tener como máximo 20 caracteres.");
	    }

	    if (sucursalRepo.existeId(s.getId())) {
	        throw new IllegalArgumentException("Ya existe una sucursal con el ID proporcionado.");
	    }

	    if (sucursalRepo.existeNombre(s.getNombre())) {
	        throw new IllegalArgumentException("Ya existe una sucursal con el nombre proporcionado.");
	    }
		return sucursalRepo.darDeAlta(s);
	}
	public void darDeBaja(int id) {
		// Verificar si la sucursal con el ID proporcionado existe en la base de datos
        Sucursal sucursal = sucursalRepo.buscarPorId(id);
        if (sucursal == null) {
            throw new IllegalArgumentException("No existe una sucursal con el ID proporcionado.");
        }

        // Llamar a la función darDeBaja de SucursalRepository para eliminar la sucursal
        sucursalRepo.darDeBaja(sucursal);
	}
	public void darDeBaja(String nombre) {
        // Verificar si la sucursal con el nombre proporcionado existe en la base de datos
        Sucursal sucursal = sucursalRepo.buscarPorNombre(nombre);
        if (sucursal == null) {
            throw new IllegalArgumentException("No existe una sucursal con el nombre proporcionado.");
        }

        // Llamar a la función darDeBaja(Sucursal s) para eliminar la sucursal
        sucursalRepo.darDeBaja(sucursal);
	}
	public Sucursal editar(Sucursal s) {
		return sucursalRepo.editar(s);
	}
	public Sucursal buscarPorId(int id) {
		return sucursalRepo.buscarPorId(id);
	}
	public Sucursal buscarPorNombre(String nombre) {
		return sucursalRepo.buscarPorNombre(nombre);
	}
	public List<Sucursal> buscarPorHorarioApertura(Time apertura) {
		return sucursalRepo.buscarPorHorarioApertura(apertura);
	}
	public List<Sucursal> buscarPorHorarioCierre(Time cierre) {
		return sucursalRepo.buscarPorHorarioCierre(cierre);
	}
	public List<Sucursal> buscarPorEstado(boolean estado) {
		return sucursalRepo.buscarPorEstado(estado);
	}

	public void actualizarStock(Stock s) {
		stockRepo.actualizarStock(s);
	}
	public Stock obtenerStock(int id, String nombre) {
		Stock stock = stockRepo.buscarStock(id, nombre);
		if(stock == null) {
			throw new IllegalArgumentException("No existe ese producto en la sucursal ingresada.");
		}
		return stock;
	}
	public List<Stock> verStock(Sucursal s) {
		return stockRepo.verStock(s) ;
	}
	public void darDeAltaProducto(Stock stock) {
		stockRepo.darDeAltaProducto(stock);
	}
	public void darDeBajaProducto(int id, String nombre) {
		Stock stock = stockRepo.buscarStock(id, nombre);
		if(stock == null) {
			throw new IllegalArgumentException("No existe ese producto en la sucursal ingresada.");
		}
		stockRepo.darDeBajaProducto(id, nombre);
	}
	
	public void ordenDeProvision(OrdenDeProvision orden) {
		if (orden.getId() <= 0) {
	        throw new IllegalArgumentException("El ID debe ser un número entero positivo.");
	    }

	    if (sucursalRepo.existeIdOrden(orden.getId())) {
	        throw new IllegalArgumentException("Ya existe una orden de provision con el ID proporcionado.");
	    }
	    
		sucursalRepo.ordenDeProvision(orden);
	}
	
	public void agregarProductoOrden(ProductosOrdenDeProvision productoOrden) {	    
		sucursalRepo.agregarProductoOrden(productoOrden);
	}
	
	public boolean estadoSucursal(int id) {
		return sucursalRepo.estadoSucursal(id);
	}
	
	public boolean estadoRuta(int id) {
		return sucursalRepo.estadoRuta(id);
	}
}
