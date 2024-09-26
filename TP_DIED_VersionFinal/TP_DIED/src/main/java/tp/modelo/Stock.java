package tp.modelo;

public class Stock {
	private int sucursal;
	private String producto;
	private int cantidad;
	
	public Stock(int sucursal, String producto, int cantidad) {
		this.sucursal = sucursal;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
