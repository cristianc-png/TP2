package tp.modelo;

import java.sql.Time;
import java.sql.Date;

public class OrdenDeProvision {
	private int id;
	private Date fecha;
	private int sucursalOrigen;
	private Time tiempoMaximo;
	private boolean pendiente;
	
	
	public OrdenDeProvision(int id, Date fecha, int sucursalOrigen, Time tiempoMaximo, boolean pendiente) {
		this.id = id;
		this.fecha = fecha;
		this.sucursalOrigen = sucursalOrigen;
		this.tiempoMaximo = tiempoMaximo;
		this.pendiente = pendiente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getSucursalOrigen() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(int sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}
	public Time getTiempoMaximo() {
		return tiempoMaximo;
	}
	public void setTiempoMaximo(Time tiempoMaximo) {
		this.tiempoMaximo = tiempoMaximo;
	}
	public boolean isPendiente() {
		return pendiente;
	}
	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	public boolean isEstado() {
		// TODO Auto-generated method stub
		return pendiente;
	}
}
