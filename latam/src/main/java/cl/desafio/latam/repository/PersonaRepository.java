package cl.desafio.latam.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cl.desafio.latam.entity.Persona;
import cl.desafio.latam.json.response.PersonaResponse;

@RepositoryRestResource(collectionResourceRel = "persona", path = "persona")
public interface PersonaRepository {
	
	List<Persona> findByLastName(@Param("nombre") String nombre);
	
	PersonaResponse createPersona(PersonaResponse persona);

}
