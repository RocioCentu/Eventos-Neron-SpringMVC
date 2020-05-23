package ar.edu.unlam.tallerweb1.viewmodel;

import java.util.List;

public class RegistroReasignacionPersonalViewModel {
	private List<Long> idpersonal;
//	private Long[] idpersonal;

	private Long idreserva;
		
	


	public List<Long> getIdpersonal() {
		return idpersonal;
	}
	public void setIdpersonal(List<Long> idpersonal) {
		this.idpersonal = idpersonal;
	}
	public Long getIdreserva() {
		return idreserva;
	}
	public void setIdreserva(Long idreserva) {
		this.idreserva = idreserva;
	}
	
}