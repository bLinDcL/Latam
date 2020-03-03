package cl.desafio.latam.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.desafio.latam.entity.Persona;

public interface PersonaDao extends CrudRepository<Persona, Long> {

	@SuppressWarnings("unchecked")
	Persona save(Persona persona);
	
	List<Persona> findAll();
	
}
