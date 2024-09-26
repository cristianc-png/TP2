package tp.dao;

import java.util.List;

import tp.modelo.Tema;

public interface TemaRepository {//el objetivo de esta inteface es actuar como repositorio
	//yo quiero que el usuario entre y vea la lista de temas que hay disponible
	//que los pueda buscar por un nombre o id
	//que pueda crear uno nuuevo, etc
	
	public List<Tema> buscarTodos();
	public Tema buscarPorId(Integer id);
	public Tema guardar(Tema t);
	public void borrar(Tema t);

}
