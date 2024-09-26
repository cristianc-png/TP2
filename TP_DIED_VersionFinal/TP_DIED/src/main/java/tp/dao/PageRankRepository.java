package tp.dao;

import java.util.List;

public interface PageRankRepository {
	public List<Integer> encontrarSucursales();
	public List<List<Integer>> encontrarCaminos(int sucursalDestino);
	public List<List<Integer>> encontrarCaminosHaciaPuerto(int sucursalDestino);
	public String buscarPorId(int id);
}
