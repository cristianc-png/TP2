package tp.services;

import java.util.List;
import java.util.ArrayList;

import tp.dao.PageRankRepository;
import tp.dao.PageRankRepositoryMySQL;

public class PageRankService {
	private static PageRankService _INSTANCE;
	private PageRankRepository pageRankRepo;
	
	public static PageRankService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new PageRankService();
			_INSTANCE.pageRankRepo = new PageRankRepositoryMySQL();
		}
		return _INSTANCE;
	}

	public List<Integer> buscarSucursales() {
		return pageRankRepo.encontrarSucursales();
	}
	
	public int caminos(int idSucursal) {
		//List<List<Integer>> aux = pageRankRepo.encontrarCaminosHaciaPuerto(idSucursal);
		List<List<Integer>> aux = pageRankRepo.encontrarCaminos(idSucursal);
		System.out.println(aux);
		return aux.size();
	}
	
	public List<String> nombreSucursales(List<Integer> sucursales){
		List<String> sucursalesN = new ArrayList<>();
		for(int i=0; i<sucursales.size(); i++) {
			sucursalesN.add(this.pageRankRepo.buscarPorId(sucursales.get(i)));
		}
		return sucursalesN;
	}
}
