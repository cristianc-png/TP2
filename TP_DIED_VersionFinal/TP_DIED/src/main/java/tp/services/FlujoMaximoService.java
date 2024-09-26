package tp.services;

import java.util.List;
import java.util.Map;

import tp.dao.FlujoMaximoRepository;
import tp.dao.FlujoMaximoRepositoryMySQL;

public class FlujoMaximoService {

	private static FlujoMaximoService _INSTANCE;
	private FlujoMaximoRepository flujoMaxRepo;
	
	public static FlujoMaximoService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new FlujoMaximoService();
			_INSTANCE.flujoMaxRepo = new FlujoMaximoRepositoryMySQL();
		}
		return _INSTANCE;
	}
	public List<Map<Integer, List<Integer>>> caminos()  {
		List<List<Integer>> caminos = flujoMaxRepo.encontrarCaminos(flujoMaxRepo.encontrarSucursalCentro());
		List<Map<Integer, List<Integer>>> aux = flujoMaxRepo.obtenerCapacidadMinima(caminos);
		for (List<Integer> camino : caminos) {
		    System.out.println(camino);
		}
		for (Map<Integer, List<Integer>> resultadoCamino : aux) {
		    int capacidadMinima = resultadoCamino.keySet().iterator().next();
		    List<Integer> camino = resultadoCamino.get(capacidadMinima);

		    System.out.println("Capacidad mínima: " + capacidadMinima);
		    System.out.println("Camino: " + camino);
		}
		return aux;
	}
	
	
	
}
