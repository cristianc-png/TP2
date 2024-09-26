package tp.dao;

import java.sql.Time;
import java.util.List;

//import java.util.List;

import tp.modelo.Ruta;

public interface RutaRepository {

	public Ruta darDeAlta(Ruta r);
	public void darDeBaja(Ruta r);
	public Ruta editar(Ruta r);
	public Ruta buscarPorId(int id);
	public List<Ruta> buscarPorOrigen(int sucursalOrigen);
	public List<Ruta> buscarPorDestino(int destino);
	public List<Ruta> buscarPorTiempoDeTransito(Time tiempo);
	public List<Ruta> buscarPorCapacidad(double capacidad);
	public List<Ruta> buscarPorEstado(boolean estado);
	public boolean existeId(int id);
}
