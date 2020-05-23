package ar.edu.unlam.tallerweb1.viewmodel;


public class RegistroIngresoMenuViewModel {

	private String descripcion;
	private Double precio;
	private Long tipoDeMenu;
	
		
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Long getTipoDeMenu() {
		return tipoDeMenu;
	}
	public void setTipoDeMenu(Long tipoDeMenu) {
		this.tipoDeMenu = tipoDeMenu;
	}


}