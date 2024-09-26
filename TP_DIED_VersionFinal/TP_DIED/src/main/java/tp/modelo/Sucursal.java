package tp.modelo;

import java.sql.Time;

public class Sucursal {

	private int id;
	private String nombre;
	private Time horarioApertura;
	private Time horarioCierre;
	private boolean estado; //Es true para operativa y false para no opertiva
	
	
	public Sucursal(int id, String nombre, Time horarioApertura, Time horarioCierre, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Time getHorarioApertura() {
		return horarioApertura;
	}
	public void setHorarioApertura(Time horarioApertura) {
		this.horarioApertura = horarioApertura;
	}
	public Time getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(Time horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
