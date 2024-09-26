package tp.modelo;

public class Producto {

	private String nombre;
	private String descripcion;
	private double precioUnitario;
	private double precioKg;
	
	public Producto(String nombre, String descripcion, double precioUnitario, double precioKg) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.precioKg = precioKg;
	}
	public Producto() {
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getPrecioKg() {
		return precioKg;
	}
	public void setPrecioKg(double precioKg) {
		this.precioKg = precioKg;
	}
	
	
}
