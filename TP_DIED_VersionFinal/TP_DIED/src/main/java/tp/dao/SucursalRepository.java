package tp.dao;

import java.sql.Time;
import java.util.List;

import tp.modelo.OrdenDeProvision;
import tp.modelo.ProductosOrdenDeProvision;
import tp.modelo.Sucursal;

public interface SucursalRepository {
	
	public Sucursal darDeAlta(Sucursal s);
	public void darDeBaja(Sucursal s);
	public Sucursal editar(Sucursal s);
	public Sucursal buscarPorId(int id);
	public Sucursal buscarPorNombre(String nombre);
	public List<Sucursal> buscarPorHorarioApertura(Time apertura);
	public List<Sucursal> buscarPorHorarioCierre(Time cierre);
	public List<Sucursal> buscarPorEstado(boolean estado);
	public boolean existeId(int id);
	public boolean existeNombre(String nombre);
	public boolean existeIdOrden(int id);
	public void ordenDeProvision(OrdenDeProvision orden);
	public void agregarProductoOrden(ProductosOrdenDeProvision productoOrden);
	public void darDeBajaOrden(int id);
	public void darDeBajaProductosOrdenDeProvision(ProductosOrdenDeProvision producto);
	public List<ProductosOrdenDeProvision> obtenerProductosOrdenePorId(int id);
	public void eliminarProductosOrdenDeProvisionAsociados(String nombre);
	public List<OrdenDeProvision> obtenerOrdenesPorSucursalDestino(int sucursalDestino);
	public List<ProductosOrdenDeProvision> obtenerProductosOrdenPorNombre(String nombre);
	
	public boolean estadoSucursal(int sucursalId);
	public boolean estadoRuta(int rutaId);
}
