package tp.modelo;

import java.sql.Time;

public class Ruta {

	private int id;
	private int origen;
	private int destino;
	private Time tiempoDeTransito;
	private double capacidad;
	private boolean estado; //Es true para operativa y false para no opertiva
	
	public Ruta(int id, int origen, int destino, Time tiempoDeTransito, double capacidad, boolean estado) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.tiempoDeTransito = tiempoDeTransito;
		this.capacidad = capacidad;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public Time getTiempoDeTransito() {
		return tiempoDeTransito;
	}
	public void setTiempoDeTransito(Time tiempoDeTransito) {
		this.tiempoDeTransito = tiempoDeTransito;
	}
	public double getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
