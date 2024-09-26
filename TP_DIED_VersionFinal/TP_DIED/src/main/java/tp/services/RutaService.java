package tp.services;

import java.sql.Time;
import java.util.List;

import tp.dao.RutaRepository;
import tp.dao.RutaRepositoryMySQL;
import tp.dao.SucursalRepositoryMySQL;
import tp.modelo.Ruta;
import tp.modelo.Sucursal;

public class RutaService {
	
	private RutaRepository rutaRepo;
	//private SucursalRepository sucursalRepo;
	private static RutaService _INSTANCE;
	
	public static RutaService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new RutaService();
			_INSTANCE.rutaRepo = new RutaRepositoryMySQL();
		}
		return _INSTANCE;
	}//si la instancia no existe la crea, pero solo una vez

	public Ruta darDeAlta(Ruta r) {
		
	    // Verificar que los campos sean del tipo correcto
	    if (r.getId() < 0 || r.getCapacidad() < 0 || r.getTiempoDeTransito() == null) {
	        throw new IllegalArgumentException("Los campos Id, Capacidad y Tiempo de transito deben ser un numero entero positivo.");
	    }

	    if (!r.isEstado()) {
	        throw new IllegalArgumentException("Debe seleccionar un estado (Operativa o No operativa).");
	    }

	    /*if (r.getOrigen() == null || r.getDestino() == null) {
	        throw new IllegalArgumentException("Debe completar todos los campos.");
	    }*/

	    if (rutaRepo.existeId(r.getId())) {
	        throw new IllegalArgumentException("Ya existe una ruta con el ID proporcionado.");
	    }
	    SucursalRepositoryMySQL sucursalRepo = new SucursalRepositoryMySQL();
		if (sucursalRepo.buscarPorId(r.getOrigen()) == null || sucursalRepo.buscarPorId(r.getDestino()) == null) {
			throw new IllegalArgumentException("Una o ambas sucursales no existen en la base de datos.");
	    }

	    return rutaRepo.darDeAlta(r);
	}
	
	public void darDeBaja(int id) {
        Ruta ruta = rutaRepo.buscarPorId(id);
        if (ruta == null) {
            throw new IllegalArgumentException("No existe una ruta con el ID proporcionado.");
        }

        rutaRepo.darDeBaja(ruta);
	}
	
	public boolean verificarExistenciaSucursales(int origenId, int destinoId) {
		SucursalRepositoryMySQL sucursalRepo = new SucursalRepositoryMySQL();
	    Sucursal sucursalOrigen = sucursalRepo.buscarPorId(origenId);
	    Sucursal sucursalDestino = sucursalRepo.buscarPorId(destinoId);
	    return sucursalOrigen != null && sucursalDestino != null;
	}
	public Ruta editar(Ruta r) {
		return rutaRepo.editar(r);
	}
	
	public Ruta buscarPorId(int id) {
		return rutaRepo.buscarPorId(id);
	}
	
	public List<Ruta> buscarPorSucursalOrigen(int sucursalOrigen) {
	    return rutaRepo.buscarPorOrigen(sucursalOrigen);
	}
	public List<Ruta> buscarPorSucursalDestino(int sucursalDestino) {
	    return rutaRepo.buscarPorDestino(sucursalDestino);
	}
	public List<Ruta> buscarPorTiemporDeTransito(Time tiempoDeTransito) {
	    return rutaRepo.buscarPorTiempoDeTransito(tiempoDeTransito);
	}
	public List<Ruta> buscarPorCapacidad(double capacidad) {
	    return rutaRepo.buscarPorCapacidad(capacidad);
	}
	public List<Ruta> buscarPorEstado(boolean estado) {
		return rutaRepo.buscarPorEstado(estado);
	}
}
