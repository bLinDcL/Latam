package cl.desafio.latam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cl.desafio.latam.entity.Poema;

@Service
public class PoemaServiceImpl implements PoemaService {
	
	@Value( "${service.poema}" )
	private String url;

	@Override
	public String obtenerPoema() {
		List<Poema> poemas = new ArrayList<Poema>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<Poema>> response = restTemplate.exchange(url,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Poema>>() {
					});
			poemas = response.getBody();
			
			if (!poemas.isEmpty()) {
				return poemas.get(0).getContent();
			} else {
				return "Error al obtener poema.";
			}
		} catch(HttpStatusCodeException e) {
			return "Error al obtener poema: " + e.getRawStatusCode() + e.getResponseBodyAsString();
		} catch(RestClientException e) {
			return "Error al obtener poema: " + e.getMessage();
	    }
	}

}
