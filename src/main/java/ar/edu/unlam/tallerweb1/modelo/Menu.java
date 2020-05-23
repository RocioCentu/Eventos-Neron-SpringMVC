package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMenu;
    private Double puntaje;
	private String descripcion;
	private Double precio;

	//imagen para el card
	private String imagenCard;

	@ManyToOne
	private TipoDeMenu tipoDeMenu;

           
	@ManyToMany(mappedBy = "menu")
	private List<Reserva> reserva; 

	
	public Menu(){}

	public Double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

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

	public TipoDeMenu getTipoDeMenu() {
		return tipoDeMenu;
	}

	public void setTipoDeMenu(TipoDeMenu tipoDeMenu) {
		this.tipoDeMenu = tipoDeMenu;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}
	
	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}


	public String getImagenCard() {
		return imagenCard;
	}

	public void setImagenCard(String imagenCard) {
		this.imagenCard = imagenCard;
	}
}