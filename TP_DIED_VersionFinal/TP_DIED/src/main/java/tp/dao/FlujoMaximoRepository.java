package tp.dao;

import java.util.List;
import java.util.Map;

public interface FlujoMaximoRepository {

	List<List<Integer>> encontrarCaminos(int sucursalDestino);
	public int encontrarSucursalCentro();
	//public List<Map<Integer, List<Integer>>> obtenerCapacidadMinima(List<List<Integer>> caminos) throws SQLException;
	List<Map<Integer, List<Integer>>> obtenerCapacidadMinima(List<List<Integer>> encontrarCaminos);

}
