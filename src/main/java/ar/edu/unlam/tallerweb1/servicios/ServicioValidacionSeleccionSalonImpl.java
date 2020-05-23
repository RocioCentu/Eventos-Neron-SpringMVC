package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;


@Service("servicioValidacionSeleccionSalon")
@Transactional
public class ServicioValidacionSeleccionSalonImpl implements ServicioValidacionSeleccionSalon {


	@Override
	public String validacionSeleccionSalon(Integer cantidad, String fecha) {
    	String mensajeFinal = "";
    	
    	
        if(cantidad==null) {
        	mensajeFinal = mensajeFinal + "Debe ingresar la cantidad de invitados" + "<br/>";
        }
        else {
            if(cantidad<=0) {
            	mensajeFinal = mensajeFinal + "La cantidad ingresada de invitados es incorrecta" + "<br/>";           
            }
            else if(cantidad>450){
            	mensajeFinal = mensajeFinal + "No disponemos salones para esa capacidad de invitados" + "<br/>";
            }
        }

        
        if(fecha==""){
        	mensajeFinal = mensajeFinal + "Debe ingresar una fecha" + "<br/>";
        }
        else {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaEvento = LocalDate.parse(fecha);

            if(fechaActual.compareTo(fechaEvento)>=0 ){
            	mensajeFinal = mensajeFinal + "Fecha invalida - La fecha seleccionada no puede ser anterior al día de hoy";
            }
        }
		return mensajeFinal;
	}
}