package tp.services;

import java.util.List;

import tp.dao.TemaRepository;
import tp.dao.TemaRepositoryPostgres;
import tp.modelo.Tema;

public class TemaService {

	private TemaRepository temaRepo;
	
	private static TemaService _INSTANCE;
	
	public TemaService() {}
	
	public static TemaService getInstance() {
		if(_INSTANCE == null) {
			_INSTANCE = new TemaService();
			_INSTANCE.temaRepo = new TemaRepositoryPostgres();
		}
		return _INSTANCE;
	}//si la instancia no existe la crea, pero solo una vez
	
	public List<Tema> buscarTodos(){
		//aca deberia chequear seguridad, argumentos y pafinar
		return temaRepo.buscarTodos();
	}
	
	public Tema guardar(Tema t) throws Exception{
		if(t.getTema().length() > 25) {
			throw new Exception("Tema muy largo");
		}
		return temaRepo.guardar(t);
	}
}
