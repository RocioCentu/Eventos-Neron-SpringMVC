package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.*;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PersonalDao;
import ar.edu.unlam.tallerweb1.dao.RegistroMenuDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.CategoriaPersonal;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroReasignacionPersonalViewModel;

@Service("servicioPersonal")
@Transactional
public class ServicioPersonalImpl implements ServicioPersonal {

	@Inject
	private PersonalDao personalDao;
	
	@Inject
	private ServicioResumen servicioResumen;
	
	@Inject
	private ServicioSalon servicioSalon;
	
	@Inject
	private RegistroMenuDao registroMenuDao;

	

	public void setPersonalDao(PersonalDao personalDao) {
		this.personalDao = personalDao;
	}

	
	
	
	// Devuelve el listado de asistencia del personal ordenado por Id
	@Override
	public Map <Long, Integer> obtencionListadoDeAsistencias() {
		Map <Long, Integer> conteo = new HashMap();

		// Realizo una iteracion del listado general de asistencias de los empleados. Ese listado es
		// devuelto por (servicioPersonal.controlDeServiciosPrestados ---> Devuelve una coleccion List <Personal>)
		Iterator<Personal> p = controlDeServiciosPrestados().iterator();
		Personal personal;
		while (p.hasNext()) {
			personal=p.next();

			// De dicha iteracion, extraigo el Id de cada empleado y lo guardo en un Map llamado conteo.
			// Alli, guardo en la key el numero de Id y en el value guardo las veces que trabajo.
			// Dicho conteo lo realizo al comparar si el Id de extraido ya existe (si ya fue agregado) al Map,
			// de ya estar, sumo una unidad en el "value". De no estar el Id, es agregado al Map en la "key"
	        if(conteo.containsKey(personal.getIdPersonal())){
	        	conteo.put(personal.getIdPersonal(),conteo.get(personal.getIdPersonal())+1);
	         }
	         else{
	            conteo.put(personal.getIdPersonal(),1);
	         }
		}

		// Se completa el listrado agregando los Id que aun no tienen ningun registro de asistencia en la BD.
		// Esto puede ocurrir cuando se ingresa a algun Personal nuevo, y no tuvo ningun evento asignado aun
		List<Personal> TodoElPersonal = personalDao.listadoDelPersonal();

		Iterator <Personal> tep = TodoElPersonal.iterator();
		Personal todos;
		while (tep.hasNext()) {
			todos=tep.next();
			 	if(conteo.containsKey(todos.getIdPersonal())){
		         }
		         else{
		            conteo.put(todos.getIdPersonal(),0);
		         }
		}
		return conteo;
	}

//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Obtengo un listado general de la asistencia (eventos cubiertos por cada personal)
	@Override
	public List<Personal> controlDeServiciosPrestados () {
		List<Reserva> lista = new ArrayList();
		lista=(personalDao.traerReservas());
		
		// Paso la lista a una coleccion Set para pisar los dupicados que me trajo la consulta por haber varias relaciones ManyToMany en la clase Reserva
		Set<Reserva> listaSinDuplicados = new HashSet();
		listaSinDuplicados.addAll(lista);
				
		List<Personal> resultado = new ArrayList<>();
		
		// Itero la lista de Reserva y voy obteniendo la lista de Personal de cada Reserva
		for (Reserva l: listaSinDuplicados) {
			resultado.addAll(l.getPersonal());
		}

		return resultado;
	}
	

//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Ordena el listado (Map) en forma ascendente
	@Override
	public Map OrdenaAscendentemente(Map unsortMap) {
		 List list = new LinkedList(unsortMap.entrySet());

		 //Para ordenar ascendentemente
		 Collections.sort(list, new Comparator() {
		 public int compare(Object o1, Object o2) {
		 return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
		 }
		 });

		 //Agrega la lista ordenana dentro del Map
		 Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();

		 for (Iterator it = list.iterator(); it.hasNext();) {
			 Map.Entry<Long, Integer> entry = (Map.Entry<Long, Integer>)it.next();
			 sortedMap.put(entry.getKey(), entry.getValue());
		 }

		 return sortedMap;
	}
		

//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Ordena el listado (Map) en forma ascendente
	@Override
	public Map OrdenaAscendentementePersonal(Map unsortMap) {
		 List list = new LinkedList(unsortMap.entrySet());

		 //Para ordenar ascendentemente
		 Collections.sort(list, new Comparator() {
		 public int compare(Object o1, Object o2) {
		 return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
		 }
		 });

		 //Agrega la lista ordenana dentro del Map
		 Map<Personal, Integer> sortedMap = new LinkedHashMap<Personal, Integer>();

		 for (Iterator it = list.iterator(); it.hasNext();) {
			 Map.Entry<Personal, Integer> entry = (Map.Entry<Personal, Integer>)it.next();
			 sortedMap.put(entry.getKey(), entry.getValue());
		 }

		 return sortedMap;
	}

//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Calcula el personal de acuerdo a la cantidad total de invitados al evento
	@Override
	public List<Integer> calcularPersonal(Integer cantidadDeInvitados) {
		List<Integer> cantidadDePersonal = new ArrayList();

		Integer encargado = (cantidadDeInvitados * 1)/100;
		cantidadDePersonal.add(encargado);
		Integer chef = (cantidadDeInvitados * 1)/100;
		cantidadDePersonal.add(chef);
		Integer cocinero = (cantidadDeInvitados * 3)/100;
		cantidadDePersonal.add(cocinero);
		Integer ayudanteCocina = (cantidadDeInvitados * 6)/100;
		cantidadDePersonal.add(ayudanteCocina);
		Integer mozo = (cantidadDeInvitados * 10)/100;
		cantidadDePersonal.add(mozo);
		Integer lavaplatos = (cantidadDeInvitados * 3)/100;
		cantidadDePersonal.add(lavaplatos);

		return cantidadDePersonal;
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Genera un listado con los sueldos del personal
		@Override
		public List <Double> consultarSueldoPersonal() {
			List<CategoriaPersonal> listadoPersonal = personalDao.listadoDeCargosDelPersonal();
			List<Double> sueldos = new ArrayList();
			
			Iterator <CategoriaPersonal> lp = listadoPersonal.iterator();
			CategoriaPersonal todos;
			while (lp.hasNext()) {
				todos=lp.next();
				sueldos.add(todos.getSueldo());
			}

			return sueldos;
		}
	
	
//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Genera un listado con la cantidad necesaria de personal para cubrir el evento
	@Override
	public List <Long> asignarPersonalNecesario(List <Integer> personalNecesario, Map <Long,Integer> conteoOrdenadoAscendentementePorAsistencia) {
		List <Long> personalDelEvento = new ArrayList <Long> ();

		// Itero las entradas (representadas por key/value) del Map
		Iterator entries = conteoOrdenadoAscendentementePorAsistencia.entrySet().iterator();

		// Se usa el for, para ir pasando por las 6 categorias de empleados
//		for(int i=0; i<6; i++) {
			int encargado=1, chef=1, cocinero=1, ayudanteCocina=1, mozo=1, lavaplatos=1;  // Con estas variables, nos aseguramos de tomar solo la cantidad necesaria de personal para la categoria
//			Long categoria=1L;  // Con esta variable vamos cambiando la seleccion de la categoria en el bucle WHILE


			while(entries.hasNext()) {

				Map.Entry <Long,Integer> entry = (Map.Entry) entries.next();

				// Obtengo el Id atraves del key
				Long key = (Long)entry.getKey();

				Long valor = personalDao.buscarPersonalPorId(key).getCategoriaPersonal().getId();
				Integer categoria = (int) (long) valor;

				switch (categoria) {
				 case 1:
						if(encargado<=personalNecesario.get(0)) {
							encargado = encargado+1;
							personalDelEvento.add(key);
						}
						break;
				 case 2:
						if(chef<=personalNecesario.get(1)) {
							chef = chef+1;
							personalDelEvento.add(key);
						}
						break;
				 case 3:
						if(cocinero<=personalNecesario.get(2)) {
							cocinero = cocinero+1;
							personalDelEvento.add(key);
						}
						break;
				 case 4:
						if(ayudanteCocina<=personalNecesario.get(3)) {
							ayudanteCocina = ayudanteCocina+1;
							personalDelEvento.add(key);
						}
						break;
				 case 5:
						if(mozo<=personalNecesario.get(4)) {
							mozo = mozo+1;
							personalDelEvento.add(key);
						}
						break;
				 case 6:
						if(lavaplatos<=personalNecesario.get(5)) {
							lavaplatos = lavaplatos+1;
							personalDelEvento.add(key);
						}
						break;
				 }
			}
 
		return personalDelEvento;
	}

//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Obtengo un listado del personal asignado al evento
	@Override
	public List<Personal> listadoPersonalAsignado(List<Long> listado) {
		List<Personal> listadoPersonalDelEvento = new ArrayList <Personal> ();

		// Recorro la coleccion recibida y voy agregando a "listadoPersonalDelEvento" (coleccion List)
		// los objetos del tipo Personal, que fueron buscados por su Id.
		// Se genera una coleccion List <Personal> con el personal asignado a cubrir el evento
		for(Long personal : listado) {
			listadoPersonalDelEvento.add(personalDao.buscarPersonalPorId(personal));
		}

		return listadoPersonalDelEvento;
	}

//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Envia al DAO el listado para ser persistido en la BD
	@Override
	public void persisteElListadoDePersonalAsignado (Reserva reserva) {

		personalDao.ingresarReserva(reserva);
	}


//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Obtencion del listado de cargos del personal
	@Override
	public List<CategoriaPersonal> consultaCargosDelPersonal () {
		List<CategoriaPersonal> listadoPersonalDelEvento = new ArrayList <CategoriaPersonal> ();
		listadoPersonalDelEvento = personalDao.listadoDeCargosDelPersonal();

		return listadoPersonalDelEvento;
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	// Obtengo un listado general de la asistencia (eventos cubiertos por cada personal)
	@Override
	public List<Personal> listadoPersonalAsignado (Long idReserva) {	
		Reserva reserva = servicioResumen.buscarDatos(idReserva);

		return reserva.getPersonal();
	}


//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------

	@Override
	public void asignaPersonalAlEvento(Long idReserva) {	

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Asigna personal al evento contratado de forma automatica. Lo hace equilibrando las veces trabajadas  /////////////////
		// por cada empleado, asegurando de esta forma que no haya disparidad en la asistencia a los eventos ////////////////////
		// Se realiza el siguiente proceso (a grandes rasgos):   ////////////////////////////////////////////////////////////////
		// 1- Obtencion del listado general de asistencias       ////////////////////////////////////////////////////////////////
		// 2- Ordenamiento por Id realizando un conteo de las asistencias     ///////////////////////////////////////////////////
		// 3- Listar en forma ascendente por asistencia         /////////////////////////////////////////////////////////////////
		// 4- Tomar los "n" primeros registros de acuerdo a la cantidad de personal requerido   /////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Reserva reservaPersonalAsignado = registroMenuDao.traerReserva (idReserva);
		
		// Obtengo el listado de asistencia ordenado por Id -- (paso 1 y 2)
		Map <Long, Integer> listadoAsistencia = new HashMap();
		listadoAsistencia = obtencionListadoDeAsistencias();

		// El Map luego es ordenado de forma ascendente considerando la asistencia del personal ("value" de la coleccion Map) -- (paso 3)
		Map <Long, Integer> conteoOrdenadoAscendentementePorAsistencia = OrdenaAscendentemente(listadoAsistencia);

		// Recibo la cantidad de personal necesario en cada categoria para cubrir el evento
		Integer cantidadDeInvitados = servicioSalon.cantidadDeInvitados(idReserva);
		List<Integer> personalNecesario = calcularPersonal(cantidadDeInvitados);

		// Genero el listado del personal a asignar de acuerdo a las necesidades del evento  -- (paso 4)
		List <Long> personalDelEvento = asignarPersonalNecesario(personalNecesario, conteoOrdenadoAscendentementePorAsistencia);

		// Obtengo el listado del personal asignado
		List<Personal> personalAsignado = listadoPersonalAsignado(personalDelEvento);
		
		reservaPersonalAsignado.setPersonal(personalAsignado);

		// Envio el listado para ser persistido
		persisteElListadoDePersonalAsignado (reservaPersonalAsignado);

	}

	
//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------
	
	// Determina la cantidad y la categoria del personal que no puede asistir a cubrir el evento
	@Override
	public List <Integer> determinaCategoriaYCantidadDelPersonalDadoDeBajaAUnEvento (List<Long> personalDadoDeBajaAlEvento) {	
		List<Integer> cantidadesAReasignar = new ArrayList();
		Integer encargado=0, chef=0, cocinero=0, ayudanteCocina=0, mozo=0, lavaplatos=0;
		

			for (int i=0; i<personalDadoDeBajaAlEvento.size(); i++) {	
				Long pddbae = personalDadoDeBajaAlEvento.get(i);
						
			if (pddbae!=null) {
			
			// busco al personal correspondiente al Id para determinar a que categoria pertenece
			Long valor = personalDao.buscarPersonalPorId(pddbae).getCategoriaPersonal().getId();
			Integer categoria = (int) (long) valor;

			switch (categoria) {
			 case 1:
					encargado++;
					break;
			 case 2:
					chef++;
					break;
			 case 3:
					cocinero++;
					break;
			 case 4:
					ayudanteCocina++;
					break;
			 case 5:
					mozo++;
					break;
			 case 6:
					lavaplatos++;
					break;
			}
			}
		}
			
		cantidadesAReasignar.add(encargado);
		cantidadesAReasignar.add(chef);
		cantidadesAReasignar.add(cocinero);
		cantidadesAReasignar.add(ayudanteCocina);
		cantidadesAReasignar.add(mozo);
		cantidadesAReasignar.add(lavaplatos);
	
		return cantidadesAReasignar;
	}
			
	
//--------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------
	
	// Reasignacion de personal para cubrir el evento
	@Override
	public List <Personal> reasingacionDelPersonalAUnEvento (List<Long> personalDadoDeBajaAlEvento, Long idReserva) {	
	//	Long idReserva = vmReasignaPersonal.getIdreserva();  // Obtengo el id de la reserva
		Reserva reservaPersonalAsignado = registroMenuDao.traerReserva (idReserva);  // Obtengo la reserva en la que se va a modificar el listado del personal a asistir al evento
	//	Long personalDadoDeBajaAlEvento [] = vmReasignaPersonal.getIdpersonal();  // Obtengo la lista de Ids del personal que hay que dar de baja al evento
	
		// Determino la cantidad y categoria del personal para dar de alta en la asistencia a cubrir el evento
		// La posicion indica la categoria y el valor la cantidad
		List<Integer> cantidadYCategoriaDePersonalASumar = determinaCategoriaYCantidadDelPersonalDadoDeBajaAUnEvento(personalDadoDeBajaAlEvento);
		
		// Obtengo el listado del personal que debe ser modificado (alta/baja de personal a asistir al evento), por medio del id de la reserva 
		List<Personal> listadoPersonal = listadoPersonalAsignado(idReserva);
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Asigna personal al evento contratado de forma automatica. Lo hace equilibrando las veces trabajadas  /////////////////
		// por cada empleado, asegurando de esta forma que no haya disparidad en la asistencia a los eventos ////////////////////
		// Se realiza el siguiente proceso (a grandes rasgos):   ////////////////////////////////////////////////////////////////
		// 1- Obtencion del listado general de asistencias       ////////////////////////////////////////////////////////////////
		// 2- Ordenamiento por Id realizando un conteo de las asistencias     ///////////////////////////////////////////////////
		// 3- Listar en forma ascendente por asistencia         /////////////////////////////////////////////////////////////////
		// 4- Tomar los "n" primeros registros de acuerdo a la cantidad de personal requerido   /////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
		// Obtengo el listado de asistencia ordenado por Id -- (paso 1 y 2)
		Map <Long, Integer> listadoAsistencia = new HashMap();
		listadoAsistencia = obtencionListadoDeAsistencias();

		// El Map luego es ordenado de forma ascendente considerando la asistencia del personal ("value" de la coleccion Map) -- (paso 3)
		Map <Long, Integer> conteoOrdenadoAscendentementePorAsistencia = OrdenaAscendentemente(listadoAsistencia);
		
		// Selecciono el personal (obtengo el ID) a asignar por categoria de acuerdo a las necesidades del evento  -- (paso 4)
		List <Long> personalDelEvento = asignarPersonalNecesario(cantidadYCategoriaDePersonalASumar, conteoOrdenadoAscendentementePorAsistencia);

		// Agrego al listado del personal, los reemplazantes necesarios en cada categoria 
		for(Long personal : personalDelEvento) {
			listadoPersonal.add(personalDao.buscarPersonalPorId(personal));
		}
		
		// Se da de baja al personal que no puede asistir al evento    
		List<Personal> personalABajar = new ArrayList();
		for(Long personal : personalDadoDeBajaAlEvento) {
			personalABajar.add(personalDao.buscarPersonalPorId(personal));
		}
		
		listadoPersonal.removeAll(personalABajar);
		
		// Se guarda el listado en la reserva
		reservaPersonalAsignado.setPersonal(listadoPersonal);

		// Envio de la reserva modificada para ser persistido
		persisteElListadoDePersonalAsignado (reservaPersonalAsignado);
		
		return listadoPersonal;
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	// Devuelve el listado de asistencia del personal
	@Override
	public Map <Personal, Integer> obtencionListadoDeAsistenciasPersonal() {
		Map <Personal, Integer> conteo = new HashMap();

		// Realizo una iteracion del listado general de asistencias de los empleados. Ese listado es
		// devuelto por (servicioPersonal.controlDeServiciosPrestados ---> Devuelve una coleccion List <Personal>)
		Iterator<Personal> p = controlDeServiciosPrestados().iterator();
		Personal personal;
		while (p.hasNext()) {
			personal=p.next();

			// De dicha iteracion, extraigo el Id de cada empleado y lo guardo en un Map llamado conteo.
			// Alli, guardo en la key el numero de Id y en el value guardo las veces que trabajo.
			// Dicho conteo lo realizo al comparar si el Id de extraido ya existe (si ya fue agregado) al Map,
			// de ya estar, sumo una unidad en el "value". De no estar el Id, es agregado al Map en la "key"
	        if(conteo.containsKey(personal)){
	        	conteo.put(personal,conteo.get(personal)+1);
	         }
	         else{
	            conteo.put(personal,1);
	         }
		}

		// Se completa el listrado agregando los Id que aun no tienen ningun registro de asistencia en la BD.
		// Esto puede ocurrir cuando se ingresa a algun Personal nuevo, y no tuvo ningun evento asignado aun
		List<Personal> TodoElPersonal = personalDao.listadoDelPersonal();

		Iterator <Personal> tep = TodoElPersonal.iterator();
		Personal todos;
		while (tep.hasNext()) {
			todos=tep.next();
			 	if(conteo.containsKey(todos)){
		         }
		         else{
		            conteo.put(todos,0);
		         }
		}
		return conteo;
	}
	
	

	
	
	//----------------nuevo----------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------------

		// Obtengo un listado general de la asistencia (eventos cubiertos por cada personal)
		@Override
		public List<Reserva> listadoDeReservas() {
			List<Reserva> lista = new ArrayList();
			lista=(personalDao.traerReservas());
		
			return lista;
		}	
		
		
			
		
		@Override
		public List<Personal> sinPersonal() {
	
			List<Personal> TodoElPersonal = personalDao.listadoDelPersonal();

			return TodoElPersonal;
		}
		
}