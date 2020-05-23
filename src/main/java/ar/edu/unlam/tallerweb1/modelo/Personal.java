package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonal;

	@ManyToOne
	private CategoriaPersonal categoriaPersonal;
	
	private String nombre;
	private String apellido;

	@ManyToMany(mappedBy = "personal")
	private List<Reserva> reserva;

	//	private Date fechaDeIngreso;
	
	public Long getIdPersonal() {
		return idPersonal;
	}
	public void setIdPersonal(Long idPersonal) {
		this.idPersonal = idPersonal;
	}
	
	public CategoriaPersonal getCategoriaPersonal() {
		return categoriaPersonal;
	}
	public void setCategoriaPersonal(CategoriaPersonal categoriaPersonal) {
		this.categoriaPersonal = categoriaPersonal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public List<Reserva> getReserva() {
		return reserva;
	}
	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}	

//	public Date getFechaDeIngreso() {
//		return fechaDeIngreso;
//	}
//	public void setFechaDeIngreso(Date fechaDeIngreso) {
//		this.fechaDeIngreso = fechaDeIngreso;
//	}
	
} 
