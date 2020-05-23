package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;



@Service("servicioValidacionSeleccionMenu")
@Transactional
public class ServicioValidacionSeleccionMenuImpl implements ServicioValidacionSeleccionMenu {

	@Inject
	private RegistroPlatosMenuDao registroPlatosMenuDao;


	@Override
	public String validacionSeleccionMenu(RegistroMenuViewModel vm) {
		Integer fingerFood=0, entrada=0, principal=0, bebida=0, postre=0, mesaDulce=0;
		
		Integer minimoFingerFood=8, maximoFingerFood=14;
		Integer minimoEntrada=1, maximoEntrada=2;
		Integer minimoPrincipal=1, maximoPrincipal=2;
		Integer minimoBebida=4, maximoBebida=7;
		Integer minimoPostre=1, maximoPostre=2;
		Integer minimoMesaDulce=4, maximoMesaDulce=6;
		
		String mensajeFingerFood = "Para la seccion de Finger Food debe seleccionar un minimo de " + minimoFingerFood + " un máximo de " + maximoFingerFood +"<br/>";
		String mensajeEntrada = "Para la seccion de Entrada debe seleccionar un minimo de " + minimoEntrada + " un máximo de " + maximoEntrada +"<br/>";
		String mensajePrincipal = "Para la seccion de Plato Principal debe seleccionar un minimo de " + minimoPrincipal + " un máximo de " + maximoPrincipal +"<br/>";
		String mensajeBebida = "Para la seccion de Bebida debe seleccionar un minimo de " + minimoBebida + " un máximo de " + maximoBebida +"<br/>";
		String mensajePostre = "Para la seccion de Postre debe seleccionar un minimo de " + minimoPostre + " un máximo de " + maximoPostre +"<br/>";
		String mensajeMesaDulce = "Para la seccion de Mesa Dulce debe seleccionar un minimo de " + minimoMesaDulce + " un máximo de " + maximoMesaDulce +"<br/>";
		
		String mensajeErrorFingerFood = "Debe realizar la selección en la sección Finger Food" +"<br/>";
		String mensajeErrorEntrada = "Debe realizar la selección en la sección Entrada" +"<br/>";
		String mensajeErrorPrincipal = "Debe realizar la selección en la sección Plato Principal" +"<br/>";
		String mensajeErrorBebida = "Debe realizar la selección en la sección Bebida" +"<br/>";
		String mensajeErrorPostre = "Debe realizar la selección en la sección Postre" +"<br/>";
		String mensajeErrorMesaDulce = "Debe realizar la selección en la seccion Mesa Dulce" +"<br/>";
				
		String mensajeFinal = "";
		
		
		// Recorro la array que contiene lo seleccionado por el cliente
		for (int i=0; i<vm.getIdmenu().length; i++) {
     	Long idMenu = vm.getIdmenu()[i];
      	if(idMenu!=null) {
     		
      		// Menu menuBuscado = servicioBuscarMenuPorId.buscaMenuPorId(9L);
      		Integer idTipoMenu = (int) (long) registroPlatosMenuDao.traerMenuPorId(idMenu).getTipoDeMenu().getId();
      		// Se realiza un conteo de la cantidad de veces que aparece cada TipoDeMenu
      		switch (idTipoMenu) {
     		case 1:
      			fingerFood++;
					break;
      		case 2:
					entrada++;
					break;
      		case 3:
					principal++;
					break;
      		case 4:
					bebida++;
					break;
      		case 5:
					postre++;
					break;
     		case 6:
					mesaDulce++;
					break;
      		}
      	}
      }
      
      // Valido Finger Food
      if(fingerFood == 0) {
    	  mensajeFinal = mensajeFinal + mensajeErrorFingerFood;
      }
      else if (fingerFood < minimoFingerFood || fingerFood > maximoFingerFood){
    	  mensajeFinal = mensajeFinal + mensajeFingerFood;
      }
      
      // Valido Entrada
      if(entrada == 0) {
    	  mensajeFinal = mensajeFinal + mensajeErrorEntrada;
      }
      else if (entrada < minimoEntrada || entrada > maximoEntrada){
    	  mensajeFinal = mensajeFinal + mensajeEntrada;
      }

      // Valido Plato Principal
      if(principal == 0) {
    	  mensajeFinal = mensajeFinal + mensajeErrorPrincipal;
      }
      else if (principal < minimoPrincipal || principal > maximoPrincipal){
    	  mensajeFinal = mensajeFinal + mensajePrincipal;
      }

      // Valido Bebida
      if(bebida == 0) {
    	  mensajeFinal = mensajeFinal + mensajeErrorBebida;
      }
      else if (bebida < minimoBebida || bebida > maximoBebida){
    	  mensajeFinal = mensajeFinal + mensajeBebida;
      }

      // Valido Postre
      if(postre == 0) {
    	  mensajeFinal = mensajeFinal + mensajeErrorPostre;
      }
      else if (postre < minimoPostre || postre > maximoPostre){
    	  mensajeFinal = mensajeFinal + mensajePostre;
      }

      // Valido Mesa Dulce
      if(mesaDulce == 0) {
    	  mensajeFinal = mensajeFinal + mensajeErrorMesaDulce;
      }
      else if (mesaDulce < minimoMesaDulce || mesaDulce > maximoMesaDulce){
    	  mensajeFinal = mensajeFinal + mensajeMesaDulce;
      }
		
      
      return mensajeFinal;

	}
}