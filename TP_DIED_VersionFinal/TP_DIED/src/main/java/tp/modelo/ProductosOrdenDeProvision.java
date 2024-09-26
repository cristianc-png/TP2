package tp.modelo;

public class ProductosOrdenDeProvision {
	private int id_ordenDeProvision;
    private String nombre_producto;
    private int cantidad;
    
    
	public ProductosOrdenDeProvision(int id_ordenDeProvision, String nombre_producto, int cantidad) {
		this.id_ordenDeProvision = id_ordenDeProvision;
		this.nombre_producto = nombre_producto;
		this.cantidad = cantidad;
	}
	public int getId_ordenDeProvision() {
		return id_ordenDeProvision;
	}
	public void setId_ordenDeProvision(int id_ordenDeProvision) {
		this.id_ordenDeProvision = id_ordenDeProvision;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
