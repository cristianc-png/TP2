package tp.dao;

import java.util.List;

import tp.modelo.OrdenDeProvision;

public interface OrdenDeProvisionRepository {

	List<OrdenDeProvision> buscarPorEstado(boolean estado);
	public OrdenDeProvision buscarOrden(int id);
	public List<List<Integer>> encontrarCaminos(int sucursalDestino);
	public List<String> obtenerNombresSucursalesPorIDs(List<Integer> ids);
	//prueba
	public List<List<Integer>> buscarRutas(List<List<Integer>> listasDeIdsSucursales);
	public List<Integer> calculateTimeSumInHours(List<List<Integer>> routeIdsLists);
	public List<List<Integer>> condiciones (List<List<Integer>> cumplen);
	public boolean cambiarEstado(int id);
}
