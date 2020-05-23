package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Reserva implements Comparable <Reserva> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    private LocalDate fecha;
    private String horario;
    private Integer cantidadDeInvitados;

    @ManyToOne
    private Salon salon;

    @ManyToOne
    private Usuario usuario;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reserva_menu", joinColumns = @JoinColumn(name = "idReserva"), inverseJoinColumns = @JoinColumn(name = "idMenu"))
	private List<Menu> menu;
	

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "reserva_extra", joinColumns = @JoinColumn(name = "idReserva"), inverseJoinColumns = @JoinColumn(name = "idExtra"))
	private List<Extra> extra;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "reserva_personal", joinColumns = @JoinColumn(name = "idReserva"), inverseJoinColumns = @JoinColumn(name = "idPersonal"))
	private List <Personal> personal;



    public Reserva() {
    }

    
	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getCantidadDeInvitados() {
		return cantidadDeInvitados;
	}

	public void setCantidadDeInvitados(Integer cantidadDeInvitados) {
		this.cantidadDeInvitados = cantidadDeInvitados;
	}
	
	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public List<Extra> getExtra() {
		return extra;
	}

	public void setExtra(List<Extra> extra) {
		this.extra = extra;
	}
	
	public List<Personal> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}


	@Override
	public int compareTo(Reserva o) {
		return this.fecha.compareTo(o.getFecha());
	}

}