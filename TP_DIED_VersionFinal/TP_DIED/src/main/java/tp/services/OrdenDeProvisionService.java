package tp.services;

import java.util.List;

import tp.dao.OrdenDeProvisionRepository;
import tp.dao.OrdenDeProvisionRepositoryMySQL;
import tp.modelo.OrdenDeProvision;

public class OrdenDeProvisionService {

	private static OrdenDeProvisionService _INSTANCE;
	private static OrdenDeProvisionRepository ordenRepo;
	
	public static OrdenDeProvisionService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new OrdenDeProvisionService();
			ordenRepo = new OrdenDeProvisionRepositoryMySQL();
		}
		return _INSTANCE;
	}//si la instancia no existe la crea, pero solo una vez
	
	public static List<OrdenDeProvision> buscarPorEstado(boolean estado) {
		return ordenRepo.buscarPorEstado(estado);
	}
	
	public OrdenDeProvision buscarOrden(int id) {
		return ordenRepo.buscarOrden(id);
	}
	
	public List<List<Integer>> caminos(int idSucursal) {
		//return ordenRepo.encontrarCaminos(idSucursal);
		//TOMAS
		return ordenRepo.condiciones(ordenRepo.encontrarCaminos(idSucursal));
	}
	public Integer caminos2(int idSucursal) {
		return ordenRepo.encontrarCaminos(idSucursal).size();
		//TOMAS
		//return ordenRepo.condiciones(ordenRepo.encontrarCaminos(idSucursal));
	}
	
	public List<String> listaString(List<Integer> lista) {
		return ordenRepo.obtenerNombresSucursalesPorIDs(lista);
	}
	
	public List<List<Integer>> caminosRutas(List<List<Integer>> caminos){
		return ordenRepo.buscarRutas(caminos);
	}
	
	public List<Integer> tiempoCaminos(List<List<Integer>> rutas) {
		return ordenRepo.calculateTimeSumInHours(rutas);
	}
	
	public boolean cambiar(int id) {
		return ordenRepo.cambiarEstado(id);
	}
}
