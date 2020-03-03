package cl.desafio.latam.service;

import java.util.List;

import cl.desafio.latam.json.request.PersonaRequest;
import cl.desafio.latam.json.response.PersonaResponse;

public interface IngresoService {
	
	public PersonaResponse ingresoPersona(PersonaRequest personaReq);
	
	public List<PersonaResponse> listaPersonas();

}
