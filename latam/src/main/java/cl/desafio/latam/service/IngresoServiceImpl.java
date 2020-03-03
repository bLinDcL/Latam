package cl.desafio.latam.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.desafio.latam.dao.PersonaDao;
import cl.desafio.latam.entity.Persona;
import cl.desafio.latam.json.request.PersonaRequest;
import cl.desafio.latam.json.response.PersonaResponse;

@Service
public class IngresoServiceImpl implements IngresoService {

	private static final Logger log = LoggerFactory.getLogger(IngresoServiceImpl.class);
	
	@Autowired
	private PoemaService poemaService;
	
	@Autowired
	private PersonaDao personaDao;

	@SuppressWarnings("deprecation")
	@Override
	public PersonaResponse ingresoPersona(PersonaRequest personaReq) {
		log.info("[ INICIO - ingresoPersona ]");

		Persona persona = new Persona();
		String poema;

		persona.setNombre(personaReq.getNombre());
		persona.setApellido(personaReq.getApellido());
		
		Date fecha;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(personaReq.getFecha());
		} catch (ParseException e) {
			fecha = null;
			e.printStackTrace();
		}
		
		persona.setFecha(personaReq.getFecha());

		Period periodo = Period.between(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
		persona.setEdad(periodo.getYears());
		periodo = Period.between(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(periodo.plusYears(1).getYears()), LocalDate.now());
		
		log.info(persona.toString());
		
		if (fecha.getDate() == new Date().getDate()) {
			poema = poemaService.obtenerPoema();
			persona.setMensaje("FELICITACIONES ESTA DE CUMPLEAÑOS!!! \n \n \n " + poema);
		} else {
			String strMes = ((periodo.getMonths() * -1) == 1)?" mes y ":" meses y ";
			String strDia = ((periodo.getDays() * -1) == 1)?" día para su cumpleaños.":" días para su cumpleaños.";
			persona.setMensaje("NO ESTA DE CUMPLEAÑOS :c " + "faltan " + (periodo.getMonths() * -1)  + strMes + ( periodo.getDays() * -1 ) + strDia);
		}
		
		persona = personaDao.save(persona);
		
		PersonaResponse personaResponse = new PersonaResponse();
		personaResponse.setNombre(persona.getNombre());
		personaResponse.setApellido(persona.getApellido());
		personaResponse.setEdad(persona.getEdad());
		personaResponse.setFecha(persona.getFecha());
		personaResponse.setMensaje(persona.getMensaje());

		log.info("[ FIN - ingresoPersona ]");
		return personaResponse;
	}

	@Override
	public List<PersonaResponse> listaPersonas() {
		log.info("[ INICIO - listaPersonas ]");
		
		List<Persona> persona = new ArrayList<Persona>();
		
		persona = personaDao.findAll();
		
		List<PersonaResponse> personas = new ArrayList<PersonaResponse>();
		for (Persona persona2 : persona) {
			PersonaResponse personaRes = new PersonaResponse();
			personaRes.setNombre(persona2.getNombre());
			personaRes.setApellido(persona2.getApellido());
			personaRes.setEdad(persona2.getEdad());
			personaRes.setFecha(persona2.getFecha());
			personaRes.setMensaje(persona2.getMensaje());
			
			personas.add(personaRes);
		}
		
		log.info("[ FIN - listaPersonas ]");
		return personas;
	}

}
